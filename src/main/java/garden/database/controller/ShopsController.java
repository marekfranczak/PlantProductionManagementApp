package garden.database.controller;

import garden.database.entity.Shops;
import garden.database.service.ShopsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class responsible for mapping HTTP requests for shops.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Controller
@RequestMapping("/shops")
public class ShopsController {

    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    private ShopsService shopsService;

    /**
     * Class constructor passing the service class to the object.
     * @param shopsService Instance of the class responsible for handling transactions in the application.
     */
    @Autowired
    public ShopsController(ShopsService shopsService){
        this.shopsService = shopsService;
    }

    /**
     * Method that handles the request /list. Which displays the entire content of the database table.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/list")
    public String shopsList(Model model){

        List<Shops> shops = shopsService.findAll();
        model.addAttribute("shops", shops);
        return "shops-list";
    }

    /**
     * Method that handles the request /showFormForAdd. Which allows you to prepare data before pass to database table.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Shops shop = new Shops();
        model.addAttribute("shop", shop);
        return "shops-form";
    }

    /**
     * Method that handles the request /save. Which allows you to add data to a database table.
     * @param shop Flower object that will be pass to database.
     * @param shopError BindingResult interface that captures errors appearing in the form and displays them.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @PostMapping("/save")
    public String saveShop(@Valid @ModelAttribute("shop") Shops shop, BindingResult shopError, Model model){

        if(shopError.hasErrors()){
            model.addAttribute("shop", shop);
            return "shops-form";
        }

        shopsService.save(shop);

        return "redirect:/shops/list";
    }

    /**
     * Method that handles the request /showFormForUpdate. Which allows you to prepare data before update it.
     * @param id Number of data which will be displayed for modification.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("shopId") int id, Model model){

        Shops shop = shopsService.findById(id);
        model.addAttribute("shop", shop);

        return "shops-form";
    }

    /**
     * Method that handles the request /delete. Which allows you to delete data from database.
     * @param id Number of data which will be deleted from database.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("shopId") int id, Model model){

        shopsService.deleteById(id);
        return "redirect:/shops/list";
    }
}
