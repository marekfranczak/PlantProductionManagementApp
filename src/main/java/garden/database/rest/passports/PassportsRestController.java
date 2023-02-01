package garden.database.rest.passports;

import garden.database.entity.Passports;
import garden.database.service.PassportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PassportsRestController {

    @Autowired
    private PassportsService passportsService;

    @GetMapping("/passports")
    public List<Passports> getPassports(){
        return passportsService.findAll();
    }

    @GetMapping("/passports/{passportId}")
    public Passports getPassport(@PathVariable int passportId){
        Passports tempPass = passportsService.findById(passportId);
        if(tempPass == null)
            throw new PassportNotFoundException("Passport not found id: "+passportId);
        return tempPass;
    }

    @PostMapping("/passports")
    public Passports addPassport(@RequestBody Passports passport){
        passport.setId(0);
        passportsService.save(passport);
        return passport;
    }

    @PutMapping("/passports")
    public Passports updatePassport(@RequestBody Passports passport){

        passportsService.save(passport);
        return passport;
    }

    @DeleteMapping("/passports/{passportId}")
    public String deletePassport(@PathVariable int passportId){
        Passports tempPass = passportsService.findById(passportId);
        if(tempPass == null)
            throw new PassportNotFoundException("Passport not found id: "+passportId);
        passportsService.deleteById(passportId);
        return "Delete Passport Id: "+passportId;
    }
}
