package example.dao;

import example.Model.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OwnerDaoImpl {

    private final SessionFactory sessionFactory;

    public OwnerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Owner save(Owner owner) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(owner);
            tx.commit();
            return owner;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public Owner getById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Owner.class, id);
        } finally {
            session.close();
        }
    }

    public List<Owner> getAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<Owner> query = session.createQuery("FROM Owner", Owner.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    public void delete(Owner owner) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(owner);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public void update(Owner owner) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(owner);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
