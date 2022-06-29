package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Repositories.CalfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/calf")
public class CalfController {

    private CalfRepository calfRepository;

    @Autowired
    public CalfController(CalfRepository calfRepository){
        this.calfRepository = calfRepository;
    }

    @GetMapping
    public String addingCalf(Model model){
        Calf calf = new Calf();
        model.addAttribute("calf",calf);
        return "calf";
    }
    @GetMapping("/all")
    public String allCalves(Model model){
        List<Calf> calfList = calfRepository.getAllCalves();
        model.addAttribute("calves",calfList);

        return "calfAll";
    }

    @PostMapping("/add") //todo:add the calf
    public String newCalf(Calf calf){
        
        Calf returnedCalf = this.calfRepository.save(calf);
        return "calfAdded";
    }
}
