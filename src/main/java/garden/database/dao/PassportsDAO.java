package garden.database.dao;

import garden.database.entity.Passports;

import java.util.List;

/**
 * Abstract representation of the operations that can be performed by connecting to a database.
 * @author Marek Frańczak
 * @since 2.0.0
 */
public interface PassportsDAO {

    /**
     * Method that allows you to get all the data from the Passports table.
     * @return List of passport from database.
     */
    public List<Passports> findAll();

    /**
     * Method that allows you to get a specific object from the Passports table.
     * @param id The number of the object we want to find.
     * @return Passport object from database.
     */
    public Passports findById(int id);

    /**
     * Method that allows you to save in the Passports table.
     * @param passports Passport object to be saved.
     */
    public void save(Passports passports);

    /**
     * Method that allows you to delete from the Passports table.
     * @param id The number of the object we want to delete.
     */
    public void deleteById(int id);

}
