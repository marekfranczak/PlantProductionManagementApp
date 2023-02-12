package garden.database.dao;

import garden.database.entity.Flowers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class that implements FlowerDAO interface.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Repository
public class FlowersDAOImp implements FlowersDAO{


    /**
     * The EntityManager API is used to create and remove persistent entity instances, to find entities by their primary key, and to query over entities.
     */
    private EntityManager entityManager;


    /**
     * Class constructor that pass entityManager to the object.
     * @param entityManager EntityManager that will be used by the class instance.
     */
    @Autowired
    public FlowersDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    /**
     * Implementation of 'findAll' method.
     * @return List of flowers from database.
     * @see FlowersDAO
     */
    @Override
    public List<Flowers> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Flowers> query = currentSession.createQuery("from Flowers", Flowers.class);
        List<Flowers> flowers = query.getResultList();

        return flowers;
    }

    /**
     * Implementation of 'findById' method.
     * @param id The number of the object we want to save.
     * @return Flower object from database.
     * @see FlowersDAO
     */
    @Override
    public Flowers findById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);
        Flowers flower = currentSession.get(Flowers.class, id);

        return flower;
    }

    /**
     * Implementation of 'save' method.
     * @param flower Flower object to be saved.
     * @see FlowersDAO
     */
    @Override
    public void save(Flowers flower) {

        Session currentSession = entityManager.unwrap(Session.class);

        if(flower != null)
            currentSession.saveOrUpdate(flower);

    }

    /**
     * Implementation of 'deletedById' method.
     * @param id The number of the object we want to delete.
     * @see FlowersDAO
     */
    @Override
    public void deleteById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("delete from Flowers where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    /**
     * Implementation of 'findByName' method.
     * @param name The name of the object we want to find.
     * @return Flower object from database.
     * @see FlowersDAO
     */
    @Override
    public Flowers findByName(String name) {
        try{
            Session currentSession = entityManager.unwrap(Session.class);

            Query<Flowers> query = currentSession.createQuery("from Flowers where nameLA=:flower", Flowers.class);
            query.setParameter("flower", name);
            Flowers flower = query.getSingleResult();

            return flower;
        } catch (NoResultException e){

            return null;
        }
    }
}
