package garden.database.controller;

import garden.database.entity.Shops;
import garden.database.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shops")
public class ShopsController {

    @Autowired
    ShopsService shopsService;

    @GetMapping("/list")
    public String shopsList(Model model){

        List<Shops> shops = shopsService.findAll();
        model.addAttribute("shops", shops);
        return "shops-list";

    }
}
