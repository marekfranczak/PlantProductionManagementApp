package garden.database.service;

import garden.database.dao.FlowersArrangementDAO;
import garden.database.dao.FlowersDAO;
import garden.database.entity.FlowersArrangement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
public class FlowersArrangementServiceImp implements FlowersArrangementService{

   // @Autowired
    public FlowersArrangementDAO flowersArrangementDAO;

    @Override
//    @Transactional
    public List<FlowersArrangement> findAll() {
        return flowersArrangementDAO.findAll();
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
