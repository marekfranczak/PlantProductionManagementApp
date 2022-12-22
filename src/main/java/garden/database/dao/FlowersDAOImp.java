package garden.database.dao;

import garden.database.entity.Flowers;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlowersDAOImp implements FlowersDAO{

    //1. Hibernate but I have problem with delete and update data
    //         I don't understand Hibernate and JPA fully <-WORK delete but UPDATE still NO

    //later check solution with SessionFactory not EntityManager

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

        currentSession.saveOrUpdate(flower);

    }

    @Override
    public void deleteById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("delete from Flowers where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
