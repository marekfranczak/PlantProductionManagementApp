package garden.database.service;

import garden.database.dao.FlowersDAO;
import garden.database.entity.Flowers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class that is responsible for carrying out transactions with the database. Class implements FlowersService interface.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Service
public class FlowersServiceImp implements FlowersService{

    /**
     * Object that responsible for connection with database.
     */
    @Autowired
    private FlowersDAO flowersDAO;

    /**
     * Implementation of 'findAll' method.
     * @return List of flowers from database.
     * @see FlowersService
     */
    @Override
    @Transactional
    public List<Flowers> findAll() {
        return flowersDAO.findAll();
    }

    /**
     * Implementation of 'findById' method.
     * @param id The number of the object we want to find.
     * @return Flower object from database.
     * @see FlowersService
     */
    @Override
    @Transactional
    public Flowers findById(int id) {
        return flowersDAO.findById(id);
    }

    /**
     * Implementation of 'save' method.
     * @param flower Flower object to be saved.
     * @see FlowersService
     */
    @Override
    @Transactional
    public void save(Flowers flower) {
        flowersDAO.save(flower);
    }

    /**
     * Implementation of 'deleteById' method.
     * @param id The number of the object we want to delete.
     * @see FlowersService
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        flowersDAO.deleteById(id);
    }

    /**
     * Implementation of 'findByName' method.
     * @param name The name of the object we want to find.
     * @return Flower object from database.
     * @see FlowersService
     */
    @Override
    @Transactional
    public Flowers findByName(String name) {
        return flowersDAO.findByName(name);
    }

}
