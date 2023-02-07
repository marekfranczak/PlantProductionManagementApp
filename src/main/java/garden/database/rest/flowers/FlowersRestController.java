package garden.database.rest.flowers;

import garden.database.entity.Flowers;
import garden.database.rest.exceptions.DataNotFoundException;
import garden.database.service.FlowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlowersRestController {

    @Autowired
    private FlowersService flowersService;

    @GetMapping("/flowers")
    public List<Flowers> getFlowers(){
        List<Flowers> flowers = new ArrayList<>();
        flowers.addAll(flowersService.findAll());
        System.out.println("Flowers: \n"+flowers);
        return flowers;
    }

    @GetMapping("/flowers/{flowerId}")
    public Flowers getFlower(@PathVariable int flowerId){
        Flowers flower = flowersService.findById(flowerId);
        if(flower == null)
            throw new DataNotFoundException("Flower id not found - "+flowerId);
        return flower;
    }

    @PostMapping("/flowers")
    public Flowers addFlower(@RequestBody Flowers flower){
        flower.setId(0);
        flowersService.save(flower);
        return flower;
    }

    @PutMapping("/flowers")
    public Flowers updateFlowers(@RequestBody Flowers flower){
        flowersService.save(flower);
        return flower;
    }

    @DeleteMapping("/flowers/{flowerId}")
    public String deleteFlower(@PathVariable int flowerId){
        Flowers tempFlower = flowersService.findById(flowerId);
        if(tempFlower == null)
            throw new DataNotFoundException("Flower id not found - "+flowerId);
        flowersService.deleteById(flowerId);
        return "Delete flower id: "+flowerId;
    }
}
