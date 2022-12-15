package garden.database.service;

import garden.database.dao.PassportsDAO;
import garden.database.entity.Passports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PassportsServiceImp implements PassportsService{

    @Autowired
    private PassportsDAO passportsDAO;

    @Override
    @Transactional
    public List<Passports> findAll() {
        return passportsDAO.findAll();
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
