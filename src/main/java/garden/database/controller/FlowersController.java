package garden.database.controller;

import garden.database.entity.Flowers;
import garden.database.service.FlowersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flowers")
public class FlowersController {

    private FlowersService flowersService;

    @Autowired
    public FlowersController(FlowersService flowersService){
        this.flowersService = flowersService;
    }

    @GetMapping("/list")
    public String flowerList(Model model){

        List<Flowers> flowers = flowersService.findAll();
        model.addAttribute("flowers", flowers);
        return "flowers-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Flowers flower = new Flowers();
        model.addAttribute("flower", flower);
        return "flowers-form";
    }

    @PostMapping("/save")
    public String saveFlower(@Valid @ModelAttribute("flower") Flowers flower, BindingResult flowerError, Model model){

        if(flowerError.hasErrors()) {
            model.addAttribute("flower", flower);
            return "flowers-form";
        }

        if(flowersService.findByName(flower.getNameLA()) != null)
        {
            return "redirect:/flowers/list";
        }

        flowersService.save(flower);

        return "redirect:/flowers/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("flowerId") int id, Model model){

        Flowers flower = flowersService.findById(id);
        model.addAttribute("flower", flower);

        return "flowers-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("flowerId") int id, Model model){

        flowersService.deleteById(id);
        return "redirect:/flowers/list";
    }

}
