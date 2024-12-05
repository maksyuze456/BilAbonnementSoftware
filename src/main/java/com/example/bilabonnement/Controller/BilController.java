package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BilController {
    @Autowired
    BilService bilService;

    @GetMapping("/bil/create")
    public String createBil(Model model) {
        model.addAttribute("bil", new Bil());
        return "home/bil/createBil";
    }

    @PostMapping("/bil/create")
    public String createBil(@ModelAttribute Bil b) {
        bilService.addBil(b);
        return "redirect:/";
    }

    @GetMapping("/deleteBil/{stelnummer}")
    public String deleteBil(@PathVariable("stelnummer") String stelnummer) {
        boolean deleted = bilService.deleteBil(stelnummer);
        if (deleted) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/updateBil/{stelnummer}")
    public String updateBil(@PathVariable("stelnummer") String stelnummer, Model model) {
        model.addAttribute("bil", bilService.findBilByStelNummer(stelnummer));
        return "home/bil/updateBil";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Bil bil) {
        bilService.updateBil(bil);
        return "redirect:/";
    }


}
