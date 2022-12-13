package garden.database.service;

import garden.database.entity.Shops;

import java.util.List;

public interface ShopsService {

    public List<Shops> findAll();

    public Shops findById(int id);

    public void save(Shops shop);

    public void deleteById(int id);

}
