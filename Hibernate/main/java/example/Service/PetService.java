package example.Service;

import example.Model.Owner;
import example.Model.Pet;
import example.dao.PetDaoImpl;

import java.util.List;

public class PetService {
    private PetDaoImpl PetsDao = new PetDaoImpl();

    public PetService() {
    }

    public void saveOwner(Owner owner) {
        PetsDao.save(owner);
    }

    public void deleteByOwner(Owner owner) {
        PetsDao.deleteByEntity(owner);
    }

    public void updateOwner(Owner owner) {
        PetsDao.update(owner);
    }

    public List<Pet> getAllOwner() {
        return PetsDao.getAll();
    }

    public Pet getByID(int id) {
        return (Pet) PetsDao.getById(id);
    }
}

