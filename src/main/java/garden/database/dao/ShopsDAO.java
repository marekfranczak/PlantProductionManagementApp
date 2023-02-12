package garden.database.dao;

import garden.database.entity.Shops;

import java.util.List;

/**
 * Abstract representation of the operations that can be performed by connecting to a database.
 * @author Marek Frańczak
 * @since 2.0.0
 */
public interface ShopsDAO {

    /**
     * Method that allows you to get all the data from the Shops table.
     * @return List of Shop from database.
     */
    public List<Shops> findAll();

    /**
     * Method that allows you to get a specific object from the Shops table.
     * @param id The number of the object we want to find.
     * @return Shop object from database.
     */
    public Shops findById(int id);

    /**
     * Method that allows you to save in the Shops table.
     * @param shop Shop object to be saved.
     */
    public void save(Shops shop);

    /**
     * Method that allows you to delete from the Shops table.
     * @param id The number of the object we want to delete.
     */
    public void deleteById(int id);

    /**
     * Method that allows you to search for a shop object based on its name.
     * @param name The name of the object we want to find.
     * @return Shop object from database.
     */
    public Shops findByName(String name);
}
