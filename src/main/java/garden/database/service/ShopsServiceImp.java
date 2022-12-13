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
