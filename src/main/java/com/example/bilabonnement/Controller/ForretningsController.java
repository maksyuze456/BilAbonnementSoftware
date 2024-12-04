package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Service.ForretningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForretningsController {
    @Autowired
    ForretningsService forretningsService;

    @GetMapping("/forretningsudvikler")
    public String forretningsudviklerHome(Model model) {
        model.addAttribute("carsList", forretningsService.fetchAllLeased());
        model.addAttribute("totalIncome", forretningsService.getTotalLeasedIncome());
        model.addAttribute("totalAmount", forretningsService.getTotalLeasedCars());
        return "forretningsudvikler";
    }
}
