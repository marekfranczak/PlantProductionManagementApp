package garden.database.dao;

import garden.database.entity.Flowers;

import java.util.List;

public interface FlowersDAO {

    public List<Flowers> findAll();

    public Flowers findById(int id);

    public void save(Flowers flower);

    public void deleteById(int id);
}
