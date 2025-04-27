package example.dao;


import example.Model.Pet;
import example.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PetDaoImpl implements Dao{
    @Override
    public Object save(Object entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(entity);
        t.commit();
        session.close();
        return entity;
    }

    @Override
    public void deleteById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Object entity = session.get(Pet.class, id);
        session.delete(entity);
        t.commit();
        session.close();
    }

    @Override
    public void deleteByEntity(Object entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(entity);
        t.commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.createQuery("DELETE FROM Pet").executeUpdate();
        t.commit();
        session.close();
    }

    @Override
    public Object update(Object entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        t.commit();
        session.close();
        return entity;
    }

    @Override
    public Object getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Object entity = session.get(Pet.class, id);
        session.close();
        return entity;
    }

    @Override
    public List getAll() {
        List<Pet> pets = (List<Pet>) HibernateUtil.getSessionFactory().openSession().createQuery("FROM Pet").list();
        return pets;
    }
}
