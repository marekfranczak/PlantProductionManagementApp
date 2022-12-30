package garden.database.controller;

import garden.database.entity.Flowers;
import garden.database.entity.Passports;
import garden.database.service.FlowersService;
import garden.database.service.PassportsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/passports_flowers_link")
public class PassportFlowersLink {

    //add exception:
    //4. org.springframework.expression.spel.SpelEvaluationException: EL1007E: Property or field 'name' cannot be found on null
    //          when shop is null app is crash
    //5. exception when i create new flower in passport-flowers-link window. Exception is from flower entity/controller.
    //          I need handle this exception


    private PassportsService passportsService;
    private FlowersService flowersService;

    @Autowired
    public PassportFlowersLink(PassportsService passportsService, FlowersService flowersService){
        this.passportsService = passportsService;
        this.flowersService = flowersService;
    }

    @PostMapping("/save")
    public String savePassport(@Valid @ModelAttribute("flower") Flowers thisFlower,
                               @RequestParam("passportId") int passportId,
                               BindingResult flowerError,
                               Model model)
    {
        if(flowerError.hasErrors()){
            System.out.println("Error!!!");
            Passports tempPassport = passportsService.findById(passportId);
            Set<Flowers> flowers = tempPassport.getFlowers();
            model.addAttribute("flowers", flowers);
            model.addAttribute("flower", new Flowers());
            model.addAttribute("passport", tempPassport);

            return "passport-flowers-link";
        }

        Passports passport = passportsService.findById(passportId);
        Flowers flower = flowersService.findByName(thisFlower.getNameLA());

        if(flower == null){
            //Exception is here...
            asad
            flowersService.save(thisFlower);
            passport.addFlower(thisFlower);
        } else
            passport.addFlower(flower);

        passportsService.save(passport);

        return "redirect:/passports/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("flowerId") int flowerId, @RequestParam("passportId") int passportId, Model model){

        Passports passport = passportsService.findById(passportId);
        Flowers flower = flowersService.findById(flowerId);

        passport.deleteFlower(flower);

        passportsService.save(passport);

        String redirect = "redirect:/passports/list";
        return redirect;
    }

}
