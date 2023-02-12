package garden.database.dao;

import garden.database.entity.Passports;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class that implements PassportsDAO interface.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Repository
public class PassportsDAOImp implements PassportsDAO {


    /**
     * The EntityManager API is used to create and remove persistent entity instances, to find entities by their primary key, and to query over entities.
     */
    private EntityManager entityManager;

    /**
     * Class constructor that pass entityManager to the object.
     * @param entityManager EntityManager that will be used by the class instance.
     */
    @Autowired
    public PassportsDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /**
     * Implementation of 'findAll' method.
     * @return List of passports from database.
     * @see PassportsDAO
     */
    @Override
    public List<Passports> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Passports> query = currentSession.createQuery("from Passports", Passports.class);
        return query.getResultList();
    }

    /**
     * Implementation of 'findById' method.
     * @param id The number of the object we want to save.
     * @return Passport object from database.
     * @see PassportsDAO
     */
    @Override
    public Passports findById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);
        Passports passport = currentSession.get(Passports.class, id);

        return passport;
    }

    /**
     * Implementation of 'save' method.
     * @param passports Passport object to be saved.
     * @see PassportsDAO
     */
    @Override
    public void save(Passports passports) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(passports);
    }

    /**
     * Implementation of 'deletedById' method.
     * @param id The number of the object we want to delete.
     * @see PassportsDAO
     */
    @Override
    public void deleteById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("delete from Passports where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
