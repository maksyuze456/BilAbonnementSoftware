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

    // Forside for skade&udbedring
    @GetMapping("/forside")
    public String forside(Model model) {

        return "skade/skadeForside";
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("rapporter", rapportService.fetchAll());
        model.addAttribute("antalRapporter", rapportService.getAntalRapporter());
        return "skade/skadeudbedring";
    }

    // Ved oprettelsen af rapporten vælger man en bil som er udlejet der skal tilbageleveres
    // Der tilføjes en liste over biler som har en status "udlejet" og det hentes fra databasen i gennem service klassen
    @GetMapping("/create/chooseCar")
    public String chooseCar(Model model){
        model.addAttribute("carList", rapportService.fetchAllCarsByStatus("udlejet"));
        return "skade/chooseUdlejet";
    }
    // Efter valgt bil, bliver dens stelnummer gemt i url til senere brug
    // Der sendes en ny oprettet rapport til modellen
    // I rapporten sættes der stelnummer som er gemt i url
    // Lejeaftle id bliver fundet ved at finde en lejeaftale som er aktiv med det samme stelnummer
    // Efter går man videre til opret leje aftale formular
    @GetMapping("/create/{stelnummer}")
    public String create(@PathVariable("stelnummer") String stelnummer, Model model) {
        Rapport rapport = new Rapport();
        rapport.setStelnummer(stelnummer);
        rapport.setLejeaftale_id(rapportService.getCorrectLejeaftaleId("Aktiv", stelnummer));
        model.addAttribute("rapport", rapport);
        return "skade/createRapport";
    }

    // Post metode for lejeaftale som tilføjer den til databasen
    @PostMapping("/create")
    public String create(@ModelAttribute Rapport rapport) {

        rapportService.addRapport(rapport);
        return "redirect:/rapporter/";
    }

    // Viser den enkelte rapport efter id
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

    // Sletter en skade rapport
    @PostMapping("/deleteOne/{rapportID}")
    public String deleteOne(@PathVariable("rapportID") int rapportID) {
        rapportService.deleteRapport(rapportID);
        return "redirect:/rapporter/";
    }

    // Sender brugeren til opdaterings side, hvor rapporten efter id kan blive redigeret
    @GetMapping("update/{rapportID}")
    public String updateOne(@PathVariable("rapportID") int id, Model model) {
        model.addAttribute("rapport", rapportService.findRapportById(id));
        return "skade/updateRapport";
    }

    // Post metode for opdatering af rapport
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

    // Post metode for tilføjelsen af skader til relevant rapport
    @PostMapping("/add/{rapportID}")
    public String add(@PathVariable int rapportID, @ModelAttribute Skade skade) {
        skade.setRapportID(rapportID);
        skadeService.addSkade(skade);
        return "redirect:/rapporter/" + rapportID;
    }

    // Post metode for sletning af skade efter id
    @PostMapping("/delete/{skadeID}")
    public String deleteSkade(@PathVariable int skadeID) {
        Skade skade = skadeService.findSkadeById(skadeID);
        if (skade != null) {
            skadeService.deleteSkadeById(skadeID);
        }
        return "redirect:/rapporter/" + (skade != null ? skade.getRapportID() : "");
    }

    // Sender brugeren til opdaterings side for den enkelte skade
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

    // Post metode for opdateret skade
    @PostMapping("/updateSkade")
    public String updateSkade(@ModelAttribute Skade skade) {
        skadeService.updateSkade(skade);
        return "redirect:/rapporter/" + skade.getRapportID();
    }

    // Post metode der tilføjer skade rapporten til databasen
    // Samt opdaterer lejeaftalen til afsluttet, og bilen bliver sat til tilbageleveret
   @PostMapping("/createAndUpdateStatus")
    public String createAndUpdateStatus(
            @ModelAttribute Rapport rapport,
            @RequestParam("bilStatus") String bilStatus) {

        // Opret rapport
        rapportService.addRapport(rapport);

        // Afslut lejeaftale
        rapportService.afslutLejeaftale(rapport.getLejeaftale_id(), "Afsluttet");

        // Opdater bilens status
        rapportService.opdaterBilStatus(rapport.getStelnummer(), bilStatus);

        return "redirect:/rapporter/";
    }

}
