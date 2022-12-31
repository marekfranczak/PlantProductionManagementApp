package garden.database.dao;

import garden.database.entity.Flowers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlowersDAOImp implements FlowersDAO{


    private EntityManager entityManager;


    @Autowired
    public FlowersDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Flowers> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Flowers> query = currentSession.createQuery("from Flowers", Flowers.class);
        List<Flowers> flowers = query.getResultList();

        return flowers;
    }

    @Override
    public Flowers findById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);
        Flowers flower = currentSession.get(Flowers.class, id);

        return flower;
    }

    @Override
    public void save(Flowers flower) {

        Session currentSession = entityManager.unwrap(Session.class);

        if(flower != null)
            currentSession.saveOrUpdate(flower);

    }

    @Override
    public void deleteById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("delete from Flowers where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

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
