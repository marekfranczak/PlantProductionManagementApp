package garden.database.rest.shops;

import garden.database.entity.Shops;
import garden.database.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopsRestController {

    @Autowired
    private ShopsService shopsService;

    @GetMapping("/shops")
    public List<Shops> getShops(){
        return shopsService.findAll();
    }

    @GetMapping("/shops/{shopId}")
    public Shops getShops(@PathVariable int shopId){
        Shops tempShop = shopsService.findById(shopId);
        if(tempShop == null)
            throw new ShopNotFoundException("Shop not found id: "+shopId);
        return tempShop;
    }

    @PostMapping("/shops")
    public Shops addShop(@RequestBody Shops shop){
        shop.setId(0);
        shopsService.save(shop);
        return shop;
    }

    @PutMapping("/shops")
    public Shops updateShop(@RequestBody Shops shop){

        shopsService.save(shop);
        return shop;
    }

    @DeleteMapping("/shops/{passportId}")
    public String deleteShop(@PathVariable int shopId){
        Shops tempShop = shopsService.findById(shopId);
        if(tempShop == null)
            throw new ShopNotFoundException("Passport not found id: "+shopId);
        shopsService.deleteById(shopId);
        return "Delete Passport Id: "+shopId;
    }
}
