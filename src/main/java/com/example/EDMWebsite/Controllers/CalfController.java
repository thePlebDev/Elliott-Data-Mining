package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.Calf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calf")
public class CalfController {

    @GetMapping
    public String addingCalf(Model model){
        Calf calf = new Calf();
        model.addAttribute("calf",calf);
        return "calf";
    }
    @GetMapping("/all")
    public String allCalves(Model model){

        return "calfAll";
    }

    @PostMapping("/add") //todo:add the calf
    public String newCalf(Calf calf){
        System.out.println("THIS IS THE CALF BELOW");
        System.out.println(calf.getTagNumber());
        System.out.println(calf.getSex());
        System.out.println(calf.getDetails());
        System.out.println(calf.getWeight());
        return "calfAdded";
    }
}
