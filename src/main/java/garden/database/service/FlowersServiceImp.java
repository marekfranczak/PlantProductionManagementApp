package garden.database.service;

import garden.database.dao.FlowersDAO;
import garden.database.entity.Flowers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlowersServiceImp implements FlowersService{

    @Autowired
    private FlowersDAO flowersDAO;

    @Override
    @Transactional
    public List<Flowers> findAll() {
        return flowersDAO.findAll();
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
