package com.example.bilabonnement.Controller;
import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Service.LejeaftaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/lejeaftale")

@Controller
public class LejeaftaleController {

    @Autowired
    LejeaftaleService lejeaftaleService;


    // Henter og tilføjer lejeaftaler til modellen som bruges på HTML-siden
    @GetMapping("/dataregForsiden")
    public String index(Model model) {
        model.addAttribute("lejeaftaler", lejeaftaleService.fetchAllLejeaftale());
        return "lejeaftale/lejeaftaleForside";
    }

    // Henter og tilføjer lejeaftaler baseret på deres status til modellen som bruges på HTML-siden
    @GetMapping("{lejeaftaleStatus}")
    public String lejeaftaleByStatus(@PathVariable String lejeaftaleStatus, Model model) {
        model.addAttribute("lejeaftaler", lejeaftaleService.fetchAllLejeaftalerByStatus(lejeaftaleStatus));
        model.addAttribute("antalLejeaftaler", lejeaftaleService.getAntalLejeaftalerByStatus(lejeaftaleStatus));
        return "lejeaftale/dataregForsiden";
    }

    // Viser formular til at oprette en ny lejeaftale med tidligere valgt kunde nr og bil stelnummer som gemmes i url parametre hen ad vejen
    @GetMapping("/create/{kunde_nr}/{stelnummer}")
    public String createLejeaftaleForm(Model model, @PathVariable("stelnummer") String stelnummer, @PathVariable int kunde_nr) {
        Lejeaftale lejeaftale = new Lejeaftale();
        lejeaftale.setStelnummer(stelnummer);
        lejeaftale.setKunde_nr(kunde_nr);
        model.addAttribute("lejeaftale", lejeaftale);
        return "lejeaftale/createlejeaftale";
    }

    // Viser en liste af tilgængelige biler, hvor kunden kan vælge en bil
    // Efter tidligere valgt kunde, som dens kunde_nr er i url parameter
    // Ved valgt bil, bliver der tilføjet stelnummer til url parametre
    @GetMapping("/create/{kunde_nr}/chooseCar")
    public String chooseCar(Model model){
        model.addAttribute("carList", lejeaftaleService.fetchAllCarsByStatus("tilgængelig"));
        return "lejeaftale/chooseCar";
    }

    // Viser en liste af kunder, hvor en kunde kan vælges til en ny lejeaftale
    // Ved valgt kunde, bliver der sendt kunde_nr til url parameter
    @GetMapping("/create/chooseCustomer")
    public String chooseCustomer(Model model){
        model.addAttribute("kundeList", lejeaftaleService.fetchAllKunder());
        return "lejeaftale/chooseCustomer";
    }

    // Post metode efter end oprettelse af lejeaftalen i formular
    @PostMapping("/create")
    public String createLejeaftale(@ModelAttribute Lejeaftale l) {
        lejeaftaleService.addLejeaftale(l);
        return "redirect:/lejeaftale/dataregForsiden";
    }

    // Opdaterer en lejeaftale med nye oplysninger
    @GetMapping("/update/{lejeaftale_id}")
    public String updateLejeaftaleForm(@PathVariable("lejeaftale_id") int lejeaftale_id, Model model) {
        Lejeaftale lejeaftale = lejeaftaleService.findLejeaftaleById(lejeaftale_id);

        model.addAttribute("lejeaftale",lejeaftale);
        return "lejeaftale/updateLejeaftale";
    }
    @PostMapping("/update")
    public String updateLejeaftale(@ModelAttribute Lejeaftale lejeaftale){
        lejeaftaleService.updateLejeaftale(lejeaftale);
        return "redirect:/lejeaftale/dataregForsiden";
    }


    // Viser detaljer om en specifik lejeaftale baseret på dens ID
    @GetMapping("/view/{lejeaftale_id}")
    public String viewLejeaftale(@PathVariable("lejeaftale_id") int lejeaftale_id, Model model) {

        model.addAttribute("lejeaftale", lejeaftaleService.findLejeaftaleById(lejeaftale_id));

        return "lejeaftale/viewLejeaftale";
    }

}
