package garden.database.controller;


import garden.database.entity.Passports;
import garden.database.entity.Shops;
import garden.database.service.PassportsService;
import garden.database.service.ShopsService;
import garden.database.service.ShopsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/passports")
public class PassportsController {

    private PassportsService passportsService;
    private ShopsService shopsService;

    @Autowired
    public PassportsController(PassportsService passportsService, ShopsService shopsService){
        this.passportsService = passportsService;
        this.shopsService = shopsService;
    }

    @GetMapping("/list")
    public String passportsList(Model model){

        List<Passports> passports = passportsService.findAll();
        List<String> shops = new ArrayList<>();
        Map<Passports, String> passportsShopNameMap = new HashMap<>();
        for(Passports passport : passports) {
            try{
                passportsShopNameMap.put(passport, passport.getShop().getName());
            } catch (NullPointerException e) {
                passportsShopNameMap.put(passport, "Brak sklepu");
            }
        }

        model.addAttribute("passports", passportsShopNameMap);
        //model.addAttribute("shops", shops);
        return "passport-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Passports passport = new Passports();
        Shops shop = new Shops();
        //model.addAttribute("shop", shop);
        model.addAttribute("passport", passport);
        return "passports-form";
    }

    @PostMapping("/save")
    public String savePassport(@ModelAttribute("passport") Passports passport){

        Shops shop = shopsService.findByName(passport.getShop().getName());
        passport.setShop(shop);
        passportsService.save(passport);

        return "redirect:/passports/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("passportId") int id, Model model){

        Passports passport = passportsService.findById(id);
        model.addAttribute("passport", passport);

        return "passports-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("passportId") int id, Model model){

        passportsService.deleteById(id);
        return "redirect:/passports/list";
    }
}
