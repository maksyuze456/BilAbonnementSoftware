package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.LejeaftaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    BilService bilService;
    @Autowired
    LejeaftaleService lejeaftaleService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("biler", bilService.fetchAllBil());
        model.addAttribute("lejeaftaler", lejeaftaleService.fetchAllLejeaftale());
        return "home/index";
    }

    @GetMapping("/bil/create")
    public String createBil() {
        return "home/createBil";
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
    public String updateBil(@PathVariable ("stelnummer") String stelnummer,Model model){
        model.addAttribute("bil",bilService.findBilByStelNummer(stelnummer));
        return "home/updateBil";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Bil bil){
        bilService.updateBil(bil);
        return "redirect:/";
    }

    //lejeaftale
    @GetMapping("/lejeaftale/create")
    public String createLejeaftale() {
        return "home/createlejeaftale";
    }

    @PostMapping("/lejeaftale/create")
    public String createLejeaftale(@ModelAttribute Lejeaftale l) {
        lejeaftaleService.addLejeaftale(l);
        return "redirect:/";
    }

    @GetMapping("/lejeaftale/delete/{id}")
    public String deletelejeaftale(@PathVariable("id") int id) {
        boolean deleted = lejeaftaleService.deleteLejeaftale(id);
        if(deleted) {
            return "redirect:/";
        } else{
            return "redirect:/";
        }
    }

    @GetMapping("/lejeaftale/update/{lejeaftal_id}")
    public String updateLejeaftale(@PathVariable("lejeaftal_id") int lejeaftale_id, Model model) {
        model.addAttribute("lejeaftale_id", lejeaftaleService.findLejeaftaleById(lejeaftale_id));
        return "home/updateLejeaftale";
    }
     @PostMapping("/lejeaftale/update")
     public String updateLejeaftale(@ModelAttribute Lejeaftale lejeaftale){
         lejeaftaleService.uupdateejeaftale(lejeaftale);
         return "redirect/";

        }
    }
