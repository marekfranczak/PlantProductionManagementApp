package garden.database.dao;

import garden.database.entity.Flowers;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlowersDAOImp implements FlowersDAO{

    //using Hibernate
    private EntityManager entityManager;

    @Autowired
    public FlowersDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Flowers> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Flowers> query = currentSession.createQuery("from Flowers", Flowers.class);

        List<Flowers> flowers  = query.getResultList();

        return flowers;
    }

    @Override
    public Flowers findById(int id) {
        return null;
    }

    @Override
    public void save(Flowers flower) {

    }

    @Override
    public void deleteById(int id) {

    }
}
