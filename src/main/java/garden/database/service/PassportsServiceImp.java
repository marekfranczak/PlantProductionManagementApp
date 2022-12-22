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
    @Transactional
    public Passports findById(int id) {
        return passportsDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Passports passports) {
        passportsDAO.save(passports);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        passportsDAO.deleteById(id);
    }
}
