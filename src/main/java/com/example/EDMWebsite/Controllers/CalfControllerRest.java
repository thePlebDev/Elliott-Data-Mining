package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Services.CalfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calf/rest")
public class CalfControllerRest {

    private CalfService calfService;

    @Autowired
    public CalfControllerRest(CalfService calfService){
        this.calfService = calfService;
    }

//    @PostMapping
//    public Calf saveCalf(Calf calf){
//        System.out.println("THIS IS THE CALF BELOW");
//        System.out.println(calf.getTagNumber());
//        System.out.println(calf.getSex());
//        System.out.println(calf.getDetails());
//        System.out.println(calf.getWeight());
//        return this.calfService.save(calf);
//    }
}
