package garden.database.service;

import garden.database.entity.Flowers;

import java.util.List;

/**
 * Interface that provides an abstraction responsible for the transaction with the database.
 * @author Marek Frańczak
 * @since 2.0.0
 */
public interface FlowersService {

    /**
     * Method that allows you to get all the data from the Flowers table.
     * @return List of flowers from database.
     */
    public List<Flowers> findAll();

    /**
     * Method that allows you to get a specific object from the Flowers table.
     * @param id The number of the object we want to find.
     * @return Flower object from database.
     */
    public Flowers findById(int id);

    /**
     * Method that allows you to save object in the Flowers table.
     * @param flower Flower object to be saved.
     */
    public void save(Flowers flower);

    /**
     * Method that allows you to delete object from the Flowers table.
     * @param id The number of the object we want to delete.
     */
    public void deleteById(int id);

    /**
     * Method that allows you to search for a flower object based on its name.
     * @param name The name of the object we want to find.
     * @return Flower object from database.
     */
    public Flowers findByName(String name);

}
