package garden.database.dao;

import garden.database.entity.FlowersArrangement;

import java.util.List;

public interface FlowersArrangementDAO {

    public List<FlowersArrangement> findAll();

    public FlowersArrangement findById(int id);

    public void save(FlowersArrangement flowersArrangement);

    public void deleteById(int id);

}
