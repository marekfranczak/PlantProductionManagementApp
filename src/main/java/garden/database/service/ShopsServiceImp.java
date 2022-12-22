package garden.database.service;

import garden.database.dao.ShopsDAO;
import garden.database.entity.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopsServiceImp implements ShopsService{

    @Autowired
    ShopsDAO shopsDAO;

    @Override
    @Transactional
    public List<Shops> findAll() {
        return shopsDAO.findAll();
    }

    @Override
    @Transactional
    public Shops findById(int id) {
        return shopsDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Shops shop) {
        shopsDAO.save(shop);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        shopsDAO.deleteById(id);
    }

    @Override
    public Shops findByName(String name) {
        return shopsDAO.findByName(name);
    }
}
