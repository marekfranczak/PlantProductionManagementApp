package garden.database.service;

import garden.database.entity.Flowers;

import java.util.List;

public interface FlowersService {

    public List<Flowers> findAll();

    public Flowers findById(int id);

    public void save(Flowers flower);

    public void deleteById(int id);

}
