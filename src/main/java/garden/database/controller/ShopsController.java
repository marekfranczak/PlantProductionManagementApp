package garden.database.controller;

import garden.database.entity.Shops;
import garden.database.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shops")
public class ShopsController {

    private ShopsService shopsService;

    @Autowired
    public ShopsController(ShopsService shopsService){
        this.shopsService = shopsService;
    }

    @GetMapping("/list")
    public String shopsList(Model model){

        List<Shops> shops = shopsService.findAll();
        model.addAttribute("shops", shops);
        return "shops-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Shops shop = new Shops();
        model.addAttribute("shop", shop);
        return "shops-form";
    }

    @PostMapping("/save")
    public String saveShop(@ModelAttribute("shop") Shops shop){
        System.out.println("ShopsController: "+shop.toString());
        shopsService.save(shop);

        return "redirect:/shops/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("shopId") int id, Model model){

        Shops shop = shopsService.findById(id);
        model.addAttribute("shop", shop);

        return "shops-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("shopId") int id, Model model){

        shopsService.deleteById(id);
        return "redirect:/shops/list";
    }
}
