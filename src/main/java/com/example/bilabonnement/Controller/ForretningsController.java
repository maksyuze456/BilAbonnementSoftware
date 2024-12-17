package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Service.ForretningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("forretningsudvikler")
@Controller
public class ForretningsController {
    @Autowired
    ForretningsService forretningsService;


    @GetMapping("/")
    public String forretningsudviklerHome() {

        return "forretningsudvikler/forretningsudviklerForside";
    }

    // Henter en liste af biler efter status, beregner total indkomst, og tæller antal biler
    // Sender det hele i model objectet som så bruges dynamisk på HTML-siden
    @GetMapping("/{bilStatus}")
    public String forretningsudviklerHome(@PathVariable("bilStatus") String bilStatus, Model model) {

        model.addAttribute("carsList", forretningsService.fetchAllCarsByStatus(bilStatus));
        model.addAttribute("totalIncome", forretningsService.getTotalIncomeByCarStatus(bilStatus));
        model.addAttribute("totalAmount", forretningsService.getTotalCarsByStatus(bilStatus));
        return "forretningsudvikler/forretningsudvikler";
    }

    // Finder en bil efter stelnummer og dens billede og sender det til modellen som bruges på HTML-siden
    @GetMapping("/view/{stelnummer}")
    public String viewBil(@PathVariable("stelnummer") String stelnummer, Model model) {

        model.addAttribute("carImageUrl", forretningsService.getImgByStelnummer(stelnummer));
        model.addAttribute("car", forretningsService.fetchCarByStelnummer(stelnummer));
        return "forretningsudvikler/viewBil";
    }

    // Finder en bil efter stelnummer og sender den til modellen som bruges på HTML-siden
    @GetMapping("edit_car/{stelnummer}")
    public String redigerBil(@PathVariable("stelnummer") String stelnummer, Model model){

        Bil car = forretningsService.fetchCarByStelnummer(stelnummer);
        model.addAttribute("car", car);
        return "forretningsudvikler/redigerBil";
    }

    // Opdaterer en bils data i databasen med værdier fra formen på redigerings side
    @PostMapping("/edit_car")
    public String redigerBil(@ModelAttribute("car") Bil car) {

        forretningsService.updateCar(car);
        return "redirect:/forretningsudvikler/view/" + car.getStelnummer();
    }

    // Opretter en ny bil ved at sende en tom Bil til modellen til som bruges på HTML-siden
    @GetMapping("/opret_bil")
    public String opretBil(Model model) {

        Bil car = new Bil();
        model.addAttribute("car", car);
        return "forretningsudvikler/opretBil";
    }

    // Kalder på metode i service for at tilføje en ny bil til databasen
    @PostMapping("/opret_bil")
    public String opretBil(@ModelAttribute("car")Bil car) {

        forretningsService.addCar(car);
        return "redirect:/forretningsudvikler/view/" + car.getStelnummer();
    }
}
