package example.Service;

import example.Model.Owner;
import example.dao.OwnerDaoImpl;
import java.util.List;

public class OwnerService {
    private OwnerDaoImpl ownersDao;

    public OwnerService(OwnerDaoImpl ownersDao) {
        this.ownersDao = ownersDao;
    }

    public void save(Owner owner) {
        ownersDao.save(owner);
    }

    public void deleteById(long id) {
        Owner owner = ownersDao.getById(id);
        if (owner != null) {
            ownersDao.delete(owner);
        }
    }

    public void delete(Owner owner) {
        ownersDao.delete(owner);
    }

    public void updateOwner(Owner owner) {
        ownersDao.update(owner);
    }

    public List<Owner> getAll() {
        return ownersDao.getAll();
    }

    public Owner getById(Long id) {
        return ownersDao.getById(id);
    }
}
