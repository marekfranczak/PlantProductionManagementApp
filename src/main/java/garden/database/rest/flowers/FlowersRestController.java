package garden.database.rest.flowers;

import garden.database.entity.Flowers;
import garden.database.rest.exceptions.DataNotFoundException;
import garden.database.service.FlowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that allows you to work with flowers data using REST API.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@RestController
@RequestMapping("/api")
public class FlowersRestController {

    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    @Autowired
    private FlowersService flowersService;

    /**
     * Method that passes the list of flowers from database.
     * @return List of flowers from database.
     */
    @GetMapping("/flowers")
    public List<Flowers> getFlowers(){
        List<Flowers> flowers = new ArrayList<>();
        flowers.addAll(flowersService.findAll());
        return flowers;
    }

    /**
     * Method that passes a specific object from the Flowers table.
     * @param flowerId Number of flower that we want to find
     * @return Flower object from database.
     */
    @GetMapping("/flowers/{flowerId}")
    public Flowers getFlower(@PathVariable int flowerId){
        Flowers flower = flowersService.findById(flowerId);
        if(flower == null)
            throw new DataNotFoundException("Flower id not found - "+flowerId);
        return flower;
    }

    /**
     * Method that allows you to save object in the Flowers table.
     * @param flower Flower object to be saved.
     * @return Object that was saved.
     */
    @PostMapping("/flowers")
    public Flowers addFlower(@RequestBody Flowers flower){
        flower.setId(0);
        flowersService.save(flower);
        return flower;
    }

    /**
     * Method that allows you to update object in the Flowers table.
     * @param flower Flower object to be updated.
     * @return Object that was updated.
     */
    @PutMapping("/flowers")
    public Flowers updateFlowers(@RequestBody Flowers flower){
        flowersService.save(flower);
        return flower;
    }

    /**
     * Method that allows you to delete object from the Flowers table.
     * @param flowerId Number of flower that we want to delete.
     * @return Confirmation that object was deleted from database.
     */
    @DeleteMapping("/flowers/{flowerId}")
    public String deleteFlower(@PathVariable int flowerId){
        Flowers tempFlower = flowersService.findById(flowerId);
        if(tempFlower == null)
            throw new DataNotFoundException("Flower id not found - "+flowerId);
        flowersService.deleteById(flowerId);
        return "Delete flower id: "+flowerId;
    }
}
