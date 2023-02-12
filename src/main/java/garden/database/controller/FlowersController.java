package garden.database.controller;

import garden.database.entity.Flowers;
import garden.database.service.FlowersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class responsible for mapping HTTP requests for Flower data.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Controller
@RequestMapping("/flowers")
public class FlowersController {

    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    private FlowersService flowersService;

    /**
     * Class constructor passing the service class to the object.
     * @param flowersService Instance of the class responsible for handling transactions in the application.
     */
    @Autowired
    public FlowersController(FlowersService flowersService){
        this.flowersService = flowersService;
    }

    /**
     * Method that handles the request /list. Which displays the entire content of the database table.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/list")
    public String flowerList(Model model){

        List<Flowers> flowers = flowersService.findAll();
        model.addAttribute("flowers", flowers);
        return "flowers-list";
    }

    /**
     * Method that handles the request /showFormForAdd. Which allows you to prepare data before pass to database table.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Flowers flower = new Flowers();
        model.addAttribute("flower", flower);
        return "flowers-form";
    }

    /**
     * Method that handles the request /save. Which allows you to add data to a database table.
     * @param flower Flower object that will be pass to database.
     * @param flowerError BindingResult interface that captures errors appearing in the form and displays them.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @PostMapping("/save")
    public String saveFlower(@Valid @ModelAttribute("flower") Flowers flower, BindingResult flowerError, Model model){

        if(flowerError.hasErrors()) {
            model.addAttribute("flower", flower);
            return "flowers-form";
        }

        if(flowersService.findByName(flower.getNameLA()) != null)
        {
            return "redirect:/flowers/list";
        }

        flowersService.save(flower);

        return "redirect:/flowers/list";
    }

    /**
     * Method that handles the request /showFormForUpdate. Which allows you to prepare data before update it.
     * @param id Number of data which will be displayed for modification.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("flowerId") int id, Model model){

        Flowers flower = flowersService.findById(id);
        model.addAttribute("flower", flower);

        return "flowers-form";
    }

    /**
     * Method that handles the request /delete. Which allows you to delete data from database.
     * @param id Number of data which will be deleted from database.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("flowerId") int id, Model model){

        flowersService.deleteById(id);
        return "redirect:/flowers/list";
    }

}
