package com.example.bilabonnement.Controller;

import org.springframework.ui.Model;
import com.example.bilabonnement.Model.Rapport;
import com.example.bilabonnement.Model.Skade;
import com.example.bilabonnement.Service.RapportService;
import com.example.bilabonnement.Service.SkadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rapporter")
public class RapportController {

    @Autowired
    RapportService rapportService;

    @Autowired
    SkadeService skadeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("rapporter", rapportService.fetchAll());

        return "home/rapport";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rapport", new Rapport());
        return "home/createRapport";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Rapport rapport) {
        rapportService.addRapport(rapport);
        return "redirect:/rapporter/";
    }

    @GetMapping("/{id}")
    public String viewRapport(@PathVariable("id") int rapportID, Model model) {
        Rapport rapport = rapportService.findRapportByID(rapportID);
        if (rapport == null) {
            model.addAttribute("errorMessage", "Rapport ikke fundet.");
            return "error";
        }
        List<Skade> skader = skadeService.findSkaderByRapportID(rapportID);
        double totalPris = skadeService.TotalPriceByRapportID(rapportID);

        model.addAttribute("rapport", rapport);
        model.addAttribute("skader", skader);
        model.addAttribute("totalPris", totalPris);

        return "rapport";
    }

    @GetMapping("/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int rapportID) {
        rapportService.deleteRapport(rapportID);
        return "redirect:/rapporter";
    }

    @PostMapping("/{id}/addSkade")
    public String addSkade(@PathVariable("id") int rapportID, @ModelAttribute Skade skade) {
        skade.setRapportID(rapportID);
        skadeService.addSkade(skade);
        return "redirect:/rapporter/" + rapportID;
    }
}
