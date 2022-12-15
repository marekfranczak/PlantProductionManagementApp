package garden.database.controller;

import garden.database.entity.Passports;
import garden.database.service.PassportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/passports")
public class PassportsController {

    private PassportsService passportsService;

    @Autowired
    public PassportsController(PassportsService passportsService){
        this.passportsService = passportsService;
    }

    @GetMapping("/list")
    public String passportsList(Model model){

        List<Passports> passports = passportsService.findAll();
        model.addAttribute("passports", passports);
        return "passport-list";
    }
}
