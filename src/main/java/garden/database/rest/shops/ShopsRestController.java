package garden.database.rest.shops;

import garden.database.entity.Shops;
import garden.database.rest.exceptions.DataNotFoundException;
import garden.database.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class that allows you to work with shops data using REST API.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@RestController
@RequestMapping("/api")
public class ShopsRestController {

    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    @Autowired
    private ShopsService shopsService;

    /**
     * Method that passes the list of shops from database.
     * @return List of shops from database.
     */
    @GetMapping("/shops")
    public List<Shops> getShops(){
        return shopsService.findAll();
    }

    /**
     * Method that passes a specific object from the Shops table.
     * @param shopId Number of shop that we want to find
     * @return Shop object from database.
     */
    @GetMapping("/shops/{shopId}")
    public Shops getShops(@PathVariable int shopId){
        Shops tempShop = shopsService.findById(shopId);
        if(tempShop == null)
            throw new DataNotFoundException("Shop not found id: "+shopId);
        return tempShop;
    }

    /**
     * Method that allows you to save object in the Shops table.
     * @param shop Shop object to be saved.
     * @return Object that was saved.
     */
    @PostMapping("/shops")
    public Shops addShop(@RequestBody Shops shop){
        shop.setId(0);
        shopsService.save(shop);
        return shop;
    }

    /**
     * Method that allows you to update object in the Shops table.
     * @param shop Shop object to be updated.
     * @return Object that was updated.
     */
    @PutMapping("/shops")
    public Shops updateShop(@RequestBody Shops shop){

        shopsService.save(shop);
        return shop;
    }

    /**
     * Method that allows you to delete object from the Shops table.
     * @param shopId Number of shop that we want to delete.
     * @return Confirmation that object was deleted from database.
     */
    @DeleteMapping("/shops/{passportId}")
    public String deleteShop(@PathVariable int shopId){
        Shops tempShop = shopsService.findById(shopId);
        if(tempShop == null)
            throw new DataNotFoundException("Passport not found id: "+shopId);
        shopsService.deleteById(shopId);
        return "Delete Passport Id: "+shopId;
    }
}
