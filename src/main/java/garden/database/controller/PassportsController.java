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

/**
 * Class responsible for mapping HTTP requests for passports.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Controller
@RequestMapping("/passports")
public class PassportsController {

    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    private PassportsService passportsService;
    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    private ShopsService shopsService;
    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    private FlowersService flowersService;

    /**
     * Class constructor passing the service class to the object.
     * @param passportsService Instance of the class responsible for handling transactions in the application.
     * @param shopsService Instance of the class responsible for handling transactions in the application.
     * @param flowersService Instance of the class responsible for handling transactions in the application.
     */
    @Autowired
    public PassportsController(PassportsService passportsService, ShopsService shopsService, FlowersService flowersService){
        this.passportsService = passportsService;
        this.shopsService = shopsService;
        this.flowersService = flowersService;
    }

    /**
     * Method that handles the request /list. Data from passport table with linked data will be pass to model.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
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

    /**
     * Method that handles the request /showFormForAdd. Which allows you to prepare data before pass to database table.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Passports passport = new Passports();
        model.addAttribute("passport", passport);
        return "passports-form";
    }

    /**
     * Method that handles the request /save. Which allows you to add data to a database table.
     * @param passport Passport object that will be pass to database.
     * @param passportError BindingResult interface that captures errors appearing in the form and displays them.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
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
        } else {
            passportsService.save(passport);
        }
        return "redirect:/passports/list";
    }

    /**
     * Method that handles the request /showFormForUpdate. Which allows you to prepare data before update it.
     * @param id Number of data which will be displayed for modification.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("passportId") int id, Model model){

        Passports passport = passportsService.findById(id);
        model.addAttribute("passport", passport);
        model.addAttribute("flowers", passport.getFlowers());

        return "passports-form";
    }

    /**
     * Method that handles the request /delete. Which allows you to delete data from database.
     * @param id Number of data which will be deleted from database.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("passportId") int id, Model model){

        passportsService.deleteById(id);
        return "redirect:/passports/list";
    }

    /**
     * Method that handles the request /showFormForLink. Which allows you to link passport data with flowers.
     * @param id Number of passport that will be linked with flowers.
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
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

    /**
     * Method that handles the request /generatePdfFile. Which allows you to generate .pdf file with passport data.
     * @param id Passport number from which the .pdf file will be generated
     * @param model Current model that will be used to contain data.
     * @return Name of website layouts file.
     */
    @GetMapping("/generatePdfFile")
    public String generatePDF(@RequestParam("passportId") int id, Model model){

        System.out.println("Generated .pdf file");
        Passports passport = passportsService.findById(id);
        Set<Flowers> flowers = passport.getFlowers();
        Shops shop = passport.getShop();
        PdfGenerator pdfGenerator = new PdfGenerator(passport, shop, flowers);
        pdfGenerator.generatePassport();

        return "pdf-generator";
    }

}
