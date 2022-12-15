package garden.database.dao;

import garden.database.entity.Passports;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassportsDAOImp implements PassportsDAO {

    //using hibernate

    private EntityManager entityManager;

    @Autowired
    public PassportsDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Passports> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Passports> query = currentSession.createQuery("from Passports", Passports.class);
        return query.getResultList();
    }

    @Override
    public Passports findById(int id) {
        return null;
    }

    @Override
    public void save(Passports passports) {

    }

    @Override
    public void deleteById(int id) {

    }
}
