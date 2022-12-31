package garden.database.controller;

import garden.database.entity.Flowers;
import garden.database.entity.Passports;
import garden.database.service.FlowersService;
import garden.database.service.PassportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passports_flowers_link")
public class PassportFlowersLink {

    private PassportsService passportsService;
    private FlowersService flowersService;

    @Autowired
    public PassportFlowersLink(PassportsService passportsService, FlowersService flowersService){
        this.passportsService = passportsService;
        this.flowersService = flowersService;
    }

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

    @GetMapping("/delete")
    public String delete(@RequestParam("flowerId") int flowerId, @RequestParam("passportId") int passportId){

        Passports passport = passportsService.findById(passportId);
        Flowers flower = flowersService.findById(flowerId);

        passport.deleteFlower(flower);

        passportsService.save(passport);

        return "redirect:/passports/list";
    }

}
