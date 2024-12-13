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


    @GetMapping("/{bilStatus}")
    public String forretningsudviklerHome(@PathVariable("bilStatus") String bilStatus, Model model) {
        model.addAttribute("carsList", forretningsService.fetchAllCarsByStatus(bilStatus));
        model.addAttribute("totalIncome", forretningsService.getTotalIncomeByCarStatus(bilStatus));
        model.addAttribute("totalAmount", forretningsService.getTotalCarsByStatus(bilStatus));
        return "forretningsudvikler/forretningsudvikler";
    }
    @GetMapping("/view/{stelnummer}")
    public String viewBil(@PathVariable("stelnummer") String stelnummer, Model model) {


        model.addAttribute("carImageUrl", forretningsService.getImgByStelnummer(stelnummer));
        model.addAttribute("car", forretningsService.fetchCarByStelnummer(stelnummer));

        return "forretningsudvikler/viewBil";
    }
    @GetMapping("edit_car/{stelnummer}")
    public String redigerBil(@PathVariable("stelnummer") String stelnummer, Model model){
        Bil car = forretningsService.fetchCarByStelnummer(stelnummer);
        model.addAttribute("car", car);
        return "forretningsudvikler/redigerBil";
    }
    @PostMapping("/edit_car")
    public String redigerBil(@ModelAttribute("car") Bil car) {
        forretningsService.updateCar(car);

        return "redirect:/forretningsudvikler/view/" + car.getStelnummer();
    }
    @GetMapping("/opret_bil")
    public String opretBil(Model model) {
        Bil car = new Bil();
        model.addAttribute("car", car);
        return "forretningsudvikler/opretBil";
    }
    @PostMapping("/opret_bil")
    public String opretBil(@ModelAttribute("car")Bil car) {
        forretningsService.addCar(car);
        return "redirect:/forretningsudvikler/view/" + car.getStelnummer();
    }
}
