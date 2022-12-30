package garden.database.dao;

import garden.database.entity.Shops;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopsDAOImp implements ShopsDAO{

    //using Hibernate

    private EntityManager entityManager;

    @Autowired
    public ShopsDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Shops> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Shops> query = currentSession.createQuery("from Shops", Shops.class);

        List<Shops> shops = query.getResultList();

        return shops;
    }

    @Override
    public Shops findById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);
        Shops shop = currentSession.get(Shops.class, id);

        return shop;
    }

    @Override
    public void save(Shops shop) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(shop);

    }

    @Override
    public void deleteById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("delete from Shops where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    public Shops findByName(String name) {

        //add exception jakarta.persistence.NoResultException: No result found for query [from Shops where name=:shop]

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
