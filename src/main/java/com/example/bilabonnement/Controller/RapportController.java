package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Service.LejeaftaleService;
import org.springframework.ui.Model;
import com.example.bilabonnement.Model.Rapport;
import com.example.bilabonnement.Model.Skade;
import com.example.bilabonnement.Service.RapportService;
import com.example.bilabonnement.Service.SkadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.bilabonnement.Service.LejeaftaleService.*;

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

        return "skade/skadeudbedring";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rapport", new Rapport());
        return "skade/createRapport";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Rapport rapport) {

        rapportService.addRapport(rapport);
        return "redirect:/rapporter/";
    }

    @GetMapping("/{id}")
    public String viewRapport(@PathVariable("id") int rapportID, Model model) {
        Rapport rapport = rapportService.findRapportById(rapportID);
        if (rapport == null) {
            model.addAttribute("errorMessage", "Rapport ikke fundet.");
            return "error";
        }
        List<Skade> skader = skadeService.findSkaderByRapportID(rapportID);


        model.addAttribute("rapport", rapport);
        model.addAttribute("skader", skader);


        return "skade/rapport";
    }

    @PostMapping("/deleteOne/{rapportID}")
    public String deleteOne(@PathVariable("rapportID") int rapportID) {
        rapportService.deleteRapport(rapportID);
        return "redirect:/rapporter/";
    }

    @GetMapping("update/{rapportID}")
    public String updateOne(@PathVariable("rapportID") int id, Model model) {
        model.addAttribute("rapport", rapportService.findRapportById(id));
        return "skade/updateRapport";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Rapport r) {
        rapportService.updateRapport(r);
        return "redirect:/rapporter/";
    }

    //skade tilføjelser

    @GetMapping("/add/{rapportID}")
    public String add(@PathVariable int rapportID, Model model) {
        model.addAttribute("rapport", rapportService.findRapportById(rapportID));
        model.addAttribute("skade", new Skade());
        return "skade/createSkade";
    }

    @PostMapping("/add/{rapportID}")
    public String add(@PathVariable int rapportID, @ModelAttribute Skade skade) {
        skade.setRapportID(rapportID);
        skadeService.addSkade(skade);
        return "redirect:/rapporter/" + rapportID;
    }

    @PostMapping("/delete/{skadeID}")
    public String deleteSkade(@PathVariable int skadeID) {
        Skade skade = skadeService.findSkadeById(skadeID);
        if (skade != null) {
            skadeService.deleteSkadeById(skadeID);
        }
        return "redirect:/rapporter/" + (skade != null ? skade.getRapportID() : "");
    }
    @GetMapping("/updateSkade/{skadeID}")
    public String updateSkade(@PathVariable int skadeID, Model model) {
        Skade skade = skadeService.findSkadeById(skadeID);
        if (skade == null) {
            model.addAttribute("errorMessage", "Skade ikke fundet.");
            return "error";
        }
        model.addAttribute("skade", skade);
        return "skade/updateSkade";
    }
    @PostMapping("/updateSkade")
    public String updateSkade(@ModelAttribute Skade skade) {
        skadeService.updateSkade(skade);
        return "redirect:/rapporter/" + skade.getRapportID();
    }


   @PostMapping("/createAndUpdateStatus")
    public String createAndUpdateStatus(
            @ModelAttribute Rapport rapport,
            @RequestParam("lejeaftaleId") int lejeaftale_id,
            @RequestParam("bilStatus") String bilStatus) {


        // Opret rapport
        rapport.setLejeaftale_id(lejeaftale_id);
        rapportService.addRapport(rapport);

        // Afslut lejeaftale
        rapportService.afslutLejeaftale(lejeaftale_id, "Afsluttet");

        // Opdater bilens status
        rapportService.opdaterBilStatus(rapport.getStelnummer(), bilStatus);

        return "redirect:/rapporter/";
    }

}
