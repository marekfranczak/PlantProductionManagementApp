package garden.database.controller;


import garden.database.entity.Flowers;
import garden.database.entity.Passports;
import garden.database.entity.Shops;
import garden.database.pdfgenerator.PdfGenerator;
import garden.database.service.FlowersService;
import garden.database.service.PassportsService;
import garden.database.service.ShopsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/passports")
public class PassportsController {

    private PassportsService passportsService;
    private ShopsService shopsService;
    private FlowersService flowersService;

    @Autowired
    public PassportsController(PassportsService passportsService, ShopsService shopsService, FlowersService flowersService){
        this.passportsService = passportsService;
        this.shopsService = shopsService;
        this.flowersService = flowersService;
    }

    @GetMapping("/list")
    public String passportsList(Model model){

        List<Passports> passports = passportsService.findAll();
        Map<Passports, String> passportsShopNameMap = new HashMap<>();
        for(Passports passport : passports) {
            try{
                passportsShopNameMap.put(passport, passport.getShop().getName());
            } catch (NullPointerException e) {
                passportsShopNameMap.put(passport, "Brak sklepu");
            }
        }

        model.addAttribute("passports", passportsShopNameMap);
        return "passport-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Passports passport = new Passports();
        model.addAttribute("passport", passport);
        return "passports-form";
    }

    @PostMapping("/save")
    public String savePassport(@Valid @ModelAttribute("passport") Passports passport, BindingResult passportError, Model model){

        Passports passportZero = passportsService.findById(passport.getId());

        if(passportError.hasErrors()) {
            if(passportZero != null){
            passport.setFlowers(passportZero.getFlowers());
            model.addAttribute("passport", passport);
            model.addAttribute("flowers", passport.getFlowers());
            } else {
                model.addAttribute("passport", passport);
            }
            return "passports-form";
        }

        if(!(passport.getShop().getName().isEmpty())) {
            Shops shop = shopsService.findByName(passport.getShop().getName());
            if(shop != null)
                passport.setShop(shop);
            else
                passport.setShop(null);
        }else{
            passport.setShop(null);
        }

        if(passportZero != null) {
            passportZero.setData(passport.getData());
            passportZero.setShop(passport.getShop());
            passportsService.save(passportZero);
            return "redirect:/passports/list";
        } else {
            passportsService.save(passport);
            return "redirect:/passports/list";
        }


    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("passportId") int id, Model model){

        Passports passport = passportsService.findById(id);
        model.addAttribute("passport", passport);
        model.addAttribute("flowers", passport.getFlowers());

        return "passports-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("passportId") int id, Model model){

        passportsService.deleteById(id);
        return "redirect:/passports/list";
    }

    @GetMapping("/showFormForLink")
    public String passportFlowerLink(@RequestParam("passportId") int id, Model model){
        Passports passport = passportsService.findById(id);
        Set<Flowers> flowers = passport.getFlowers();

        Flowers flower = new Flowers();

        model.addAttribute("passport", passport);
        model.addAttribute("flowers", flowers);
        model.addAttribute("flower", flower);

        return "passport-flowers-link";
    }

    @GetMapping("/generatePdfFile")
    public String generatePDF(@RequestParam("passportId") int id, Model model){

        System.out.println("Generated .pdf file");
        Passports passport = passportsService.findById(id);
        Set<Flowers> flowers = passport.getFlowers();
        Shops shop = passport.getShop();
        PdfGenerator pdfGenerator = new PdfGenerator(passport, shop, flowers);
        pdfGenerator.generatePassport();
        System.out.println(passport.toString());

        return "pdf-generator";
    }

}
