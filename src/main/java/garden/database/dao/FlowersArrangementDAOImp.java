package garden.database.dao;

import garden.database.entity.FlowersArrangement;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlowersArrangementDAOImp implements FlowersArrangementDAO{

    private EntityManager entityManager;

    @Autowired
    public FlowersArrangementDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<FlowersArrangement> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<FlowersArrangement> query = currentSession.createQuery("from FlowersArrangement", FlowersArrangement.class);

        List<FlowersArrangement> flowersArrangements  = query.getResultList();

        return flowersArrangements;
    }

    @Override
    public FlowersArrangement findById(int id) {
        return null;
    }

    @Override
    public void save(FlowersArrangement flowersArrangement) {

    }

    @Override
    public void deleteById(int id) {

    }
}
