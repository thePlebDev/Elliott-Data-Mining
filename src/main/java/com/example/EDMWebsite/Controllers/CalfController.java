package com.example.EDMWebsite.Controllers;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Repositories.CalfRepository;
import com.example.EDMWebsite.Repositories.UserRepository;
import com.example.EDMWebsite.Services.CalfService;
import com.example.EDMWebsite.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/calf")
public class CalfController {

    private CalfService calfService;
    private UserService userService;

    @Autowired
    public CalfController(CalfService calfService,UserService userService){
        this.calfService = calfService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addingCalf(Model model){
        Calf calf = new Calf();
        model.addAttribute("calf",calf);
        System.out.println("CALF/ADD CONTROLLER");
        return "calf";
    }

    @PostMapping("/add")
    public String saveCalf(Calf calf, Authentication auth, RedirectAttributes model){
        String username = auth.getName();
        String tagNumber = this.userService.saveCalfToUser(calf,username);
        String textForUI = "Calf " + tagNumber + " added";
        model.addFlashAttribute("tagNumber",textForUI);


        return "redirect:/calf/add";

    }

}
