package garden.database.controller;

import garden.database.entity.Flowers;
import garden.database.service.FlowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
