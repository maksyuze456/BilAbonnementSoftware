package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.LeasingPrice;
import com.example.bilabonnement.Service.ForretningsService;
import com.example.bilabonnement.Service.LeasingPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("forretningsudvikler")
@Controller
public class ForretningsController {
    @Autowired
    ForretningsService forretningsService;
    @Autowired
    LeasingPriceService leasingPriceService;

    @GetMapping("/{bilStatus}")
    public String forretningsudviklerHome(@PathVariable("bilStatus") String bilStatus, Model model) {
        model.addAttribute("carsList", forretningsService.fetchAllCarsByStatusWithPrice(bilStatus));
        model.addAttribute("totalIncome", forretningsService.getTotalIncomeByCarStatus(bilStatus));
        model.addAttribute("totalAmount", forretningsService.getTotalCarsByStatus(bilStatus));
        return "forretningsudvikler/forretningsudvikler";
    }
    @GetMapping("/view/{stelnummer}")
    public String viewBil(@PathVariable("stelnummer") String stelnummer, Model model) {


        model.addAttribute("carImageUrl", forretningsService.getImgByStelnummer(stelnummer));
        model.addAttribute("car", forretningsService.fetchCarByStelnummerWithLeasingPrice(stelnummer));

        return "forretningsudvikler/viewBil";
    }
    @GetMapping("edit_car/{stelnummer}")
    public String redigerBil(@PathVariable("stelnummer") String stelnummer, Model model){
        Bil car = forretningsService.fetchCarByStelnummer(stelnummer);
        LeasingPrice leasingPrice = leasingPriceService.fetchLeasingPriceByStelnummer(stelnummer);
        model.addAttribute("car", car);
        model.addAttribute("leasingPrice", leasingPrice);
        return "forretningsudvikler/redigerBil";
    }
    @PostMapping("/edit_car")
    public String redigerBil(@ModelAttribute("car") Bil car, @ModelAttribute("leasingPrice") LeasingPrice leasingPrice) {
        forretningsService.updateCar(car);
        leasingPriceService.updateLeasingPriceByStelnummer(car.getStelnummer(), leasingPrice);
        return "redirect:/forretningsudvikler/view/" + car.getStelnummer();
    }
    @GetMapping("/opret_bil")
    public String opretBil(Model model) {
        LeasingPrice leasingPrice = new LeasingPrice();
        Bil car = new Bil();
        model.addAttribute("car", car);
        model.addAttribute("leasingPrice", leasingPrice);
        return "forretningsudvikler/opretBil";
    }
    @PostMapping("/opret_bil")
    public String opretBil(@ModelAttribute("car")Bil car, @ModelAttribute("leasingPrice") LeasingPrice leasingPrice) {
        forretningsService.addCar(car);
        leasingPrice.setStelnummer(car.getStelnummer());
        leasingPriceService.addPrice(leasingPrice);
        return "redirect:/forretningsudvikler/view/" + car.getStelnummer();
    }
}
