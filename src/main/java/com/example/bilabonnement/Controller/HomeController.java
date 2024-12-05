package com.example.bilabonnement.Controller;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.LejeaftaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @Autowired
    BilService bilService;
    @Autowired
    LejeaftaleService lejeaftaleService;

    @GetMapping("/")
    public String homePage() {
        return "home/forside";
    }
    @GetMapping("/dataregForsiden")
    public String index(Model model) {
        model.addAttribute("biler", bilService.fetchAllBil());
        model.addAttribute("lejeaftaler", lejeaftaleService.fetchAllLejeaftale());
        return "home/dataregForsiden";
    }

}
