package garden.database.dao;

import garden.database.entity.Shops;

import java.util.List;

public interface ShopsDAO {

    public List<Shops> findAll();

    public Shops findById(int id);

    public void save(Shops shop);

    public void deleteById(int id);

    public Shops findByName(String name);
}
