package garden.database.service;

import garden.database.dao.PassportsDAO;
import garden.database.entity.Passports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class that is responsible for carrying out transactions with the database. Class implements PassportsService interface.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Service
public class PassportsServiceImp implements PassportsService{

    /**
     * Object that responsible for connection with database.
     */
    @Autowired
    private PassportsDAO passportsDAO;

    /**
     * Implementation of 'findAll' method.
     * @return List of passports from database.
     * @see PassportsService
     */
    @Override
    @Transactional
    public List<Passports> findAll() {
        return passportsDAO.findAll();
    }

    /**
     * Implementation of 'findById' method.
     * @param id The number of the object we want to find.
     * @return Passport object from database.
     * @see PassportsService
     */
    @Override
    @Transactional
    public Passports findById(int id) {
        return passportsDAO.findById(id);
    }

    /**
     * Implementation of 'save' method.
     * @param passports Passport object to be saved.
     * @see PassportsService
     */
    @Override
    @Transactional
    public void save(Passports passports) {
        passportsDAO.save(passports);
    }

    /**
     * Implementation of 'deleteById' method.
     * @param id The number of the object we want to delete.
     * @see PassportsService
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        passportsDAO.deleteById(id);
    }
}
