package garden.database.dao;

import garden.database.entity.Shops;
import jakarta.persistence.EntityManager;
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
        return null;
    }

    @Override
    public void save(Shops shop) {

    }

    @Override
    public void deleteById(int id) {

    }
}
