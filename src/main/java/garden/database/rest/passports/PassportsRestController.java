package garden.database.rest.passports;

import garden.database.entity.Passports;
import garden.database.rest.exceptions.DataNotFoundException;
import garden.database.service.PassportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class that allows you to work with passports data using REST API.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@RestController
@RequestMapping("/api")
public class PassportsRestController {

    /**
     * Instance of the class responsible for handling transactions in the application.
     */
    @Autowired
    private PassportsService passportsService;

    /**
     * Method that passes the list of passports from database.
     * @return List of passports from database.
     */
    @GetMapping("/passports")
    public List<Passports> getPassports(){
        return passportsService.findAll();
    }

    /**
     * Method that passes a specific object from the Passports table.
     * @param passportId Number of passport that we want to find
     * @return Passport object from database.
     */
    @GetMapping("/passports/{passportId}")
    public Passports getPassport(@PathVariable int passportId){
        Passports tempPass = passportsService.findById(passportId);
        if(tempPass == null)
            throw new DataNotFoundException("Passport not found id: "+passportId);
        return tempPass;
    }

    /**
     * Method that allows you to save object in the Passports table.
     * @param passport Passport object to be saved.
     * @return Object that was saved.
     */
    @PostMapping("/passports")
    public Passports addPassport(@RequestBody Passports passport){
        passport.setId(0);
        passportsService.save(passport);
        return passport;
    }

    /**
     * Method that allows you to update object in the Passports table.
     * @param passport Passport object to be updated.
     * @return Object that was updated.
     */
    @PutMapping("/passports")
    public Passports updatePassport(@RequestBody Passports passport){

        passportsService.save(passport);
        return passport;
    }

    /**
     * Method that allows you to delete object from the Passports table.
     * @param passportId Number of passport that we want to delete.
     * @return Confirmation that object was deleted from database.
     */
    @DeleteMapping("/passports/{passportId}")
    public String deletePassport(@PathVariable int passportId){
        Passports tempPass = passportsService.findById(passportId);
        if(tempPass == null)
            throw new DataNotFoundException("Passport not found id: "+passportId);
        passportsService.deleteById(passportId);
        return "Delete Passport Id: "+passportId;
    }
}
