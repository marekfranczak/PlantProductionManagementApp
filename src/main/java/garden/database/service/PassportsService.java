package garden.database.service;

import garden.database.entity.Passports;

import java.util.List;

public interface PassportsService {

    public List<Passports> findAll();

    public Passports findById(int id);

    public void save(Passports passports);

    public void deleteById(int id);
}
