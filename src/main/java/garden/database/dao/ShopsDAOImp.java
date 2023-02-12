package garden.database.dao;

import garden.database.entity.Shops;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class that implements ShopsDAO interface.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Repository
public class ShopsDAOImp implements ShopsDAO{



    /**
     * The EntityManager API is used to create and remove persistent entity instances, to find entities by their primary key, and to query over entities.
     */
    private EntityManager entityManager;

    /**
     * Class constructor that pass entityManager to the object.
     * @param entityManager EntityManager that will be used by the class instance.
     */
    @Autowired
    public ShopsDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /**
     * Implementation of 'findAll' method.
     * @return List of shops from database.
     * @see ShopsDAO
     */
    @Override
    public List<Shops> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Shops> query = currentSession.createQuery("from Shops", Shops.class);

        List<Shops> shops = query.getResultList();

        return shops;
    }

    /**
     * Implementation of 'findById' method.
     * @param id The number of the object we want to save.
     * @return Shop object from database.
     * @see ShopsDAO
     */
    @Override
    public Shops findById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);
        Shops shop = currentSession.get(Shops.class, id);

        return shop;
    }

    /**
     * Implementation of 'save' method.
     * @param shop Shop object to be saved.
     * @see ShopsDAO
     */
    @Override
    public void save(Shops shop) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(shop);

    }

    /**
     * Implementation of 'deletedById' method.
     * @param id The number of the object we want to delete.
     * @see ShopsDAO
     */
    @Override
    public void deleteById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("delete from Shops where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    /**
     * Implementation of 'findByName' method.
     * @param name The name of the object we want to find.
     * @return Shop object from database.
     * @see ShopsDAO
     */
    @Override
    public Shops findByName(String name) {

        try{
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Shops> query = currentSession.createQuery("from Shops where name=:shop", Shops.class);
        query.setParameter("shop", name);
        Shops shop = query.getSingleResult();
            return shop;
        } catch (NoResultException e){
            return null;
        }

    }
}
