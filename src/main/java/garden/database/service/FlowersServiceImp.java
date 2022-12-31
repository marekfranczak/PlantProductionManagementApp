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
    @Transactional
    public Flowers findById(int id) {
        return flowersDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Flowers flower) {
        flowersDAO.save(flower);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        flowersDAO.deleteById(id);
    }

    @Override
    @Transactional
    public Flowers findByName(String name) {
        return flowersDAO.findByName(name);
    }

}
