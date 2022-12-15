package garden.database.dao;

import garden.database.entity.Passports;

import java.util.List;

public interface PassportsDAO {

    public List<Passports> findAll();

    public Passports findById(int id);

    public void save(Passports passports);

    public void deleteById(int id);

}
