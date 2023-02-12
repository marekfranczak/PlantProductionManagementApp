package garden.database.controller;

import garden.database.entity.Flowers;
import garden.database.entity.Passports;
import garden.database.service.FlowersService;
import garden.database.service.PassportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Class responsible for mapping HTTP requests for passport and flower data.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Controller
@RequestMapping("/passports_flowers_link")
public class PassportFlowersLink {

    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    private PassportsService passportsService;
    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    private FlowersService flowersService;

    /**
     * Class constructor passing the service class to the object.
     * @param flowersService Instance of the class responsible for handling transactions in the application.
     * @param passportsService Instance of the class responsible for handling transactions in the application.
     */
    @Autowired
    public PassportFlowersLink(PassportsService passportsService, FlowersService flowersService){
        this.passportsService = passportsService;
        this.flowersService = flowersService;
    }

    /**
     * Method that handles the request /save. Which allows you to link current passport with current flower.
     * @param thisFlower data whose links to the passport are currently being edited.
     * @param passportId Passport number that is currently being edited.
     * @return Name of website layouts file.
     */
    @PostMapping("/save")
    public String savePassport(@ModelAttribute("flower") Flowers thisFlower,
                               @RequestParam("passportId") int passportId)
    {
        Passports passport = passportsService.findById(passportId);
        Flowers flower = flowersService.findByName(thisFlower.getNameLA());
        if(flower == null){
            if(thisFlower.getNameLA().isBlank())
                return "redirect:/passports/list";
            flowersService.save(thisFlower);
            passport.addFlower(thisFlower);
        } else
            passport.addFlower(flower);

        passportsService.save(passport);

        return "redirect:/passports/list";
    }

    /**
     * Method that handles the request /delete. Which deleted link between current passport with current flower.
     * @param flowerId Passport number that is currently being deleting.
     * @param passportId Passport number that is currently being deleting.
     * @return Name of website layouts file.
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("flowerId") int flowerId, @RequestParam("passportId") int passportId){

        Passports passport = passportsService.findById(passportId);
        Flowers flower = flowersService.findById(flowerId);

        passport.deleteFlower(flower);

        passportsService.save(passport);

        return "redirect:/passports/list";
    }

}
