package garden.database.service;

import garden.database.dao.ShopsDAO;
import garden.database.entity.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class that is responsible for carrying out transactions with the database. Class implements ShopsService interface.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Service
public class ShopsServiceImp implements ShopsService{

    /**
     * Object that responsible for connection with database.
     */
    @Autowired
    ShopsDAO shopsDAO;

    /**
     * Implementation of 'findAll' method.
     * @return List of shops from database.
     * @see ShopsService
     */
    @Override
    @Transactional
    public List<Shops> findAll() {
        return shopsDAO.findAll();
    }

    /**
     * Implementation of 'findById' method.
     * @param id The number of the object we want to find.
     * @return Shop object from database.
     * @see ShopsService
     */
    @Override
    @Transactional
    public Shops findById(int id) {
        return shopsDAO.findById(id);
    }

    /**
     * Implementation of 'save' method.
     * @param shop Shop object to be saved.
     * @see ShopsService
     */
    @Override
    @Transactional
    public void save(Shops shop) {
        shopsDAO.save(shop);
    }

    /**
     * Implementation of 'deleteById' method.
     * @param id The number of the object we want to delete.
     * @see ShopsService
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        shopsDAO.deleteById(id);
    }

    /**
     * Implementation of 'findByName' method.
     * @param name The name of the object we want to find.
     * @return Shop object from database.
     * @see ShopsService
     */
    @Override
    public Shops findByName(String name) {
        return shopsDAO.findByName(name);
    }
}
