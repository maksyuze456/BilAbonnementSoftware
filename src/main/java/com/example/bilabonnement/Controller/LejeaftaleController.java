package com.example.bilabonnement.Controller;
import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.LejeaftaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/lejeaftale")

@Controller
public class LejeaftaleController {

    @Autowired
    BilService bilService;
    @Autowired
    LejeaftaleService lejeaftaleService;


    @GetMapping("/dataregForsiden")
    public String index(Model model) {
        model.addAttribute("biler", bilService.fetchAllBil());
        model.addAttribute("lejeaftaler", lejeaftaleService.fetchAllLejeaftale());
        return "home/dataregForsiden";
    }


    @GetMapping("/create")
    public String createLejeaftaleForm() {
        return "home/lejeaftale/createlejeaftale";
    }


    @PostMapping("/create")
    public String createLejeaftale(@ModelAttribute Lejeaftale l) {
        lejeaftaleService.addLejeaftale(l);
        return "redirect:/lejeaftale/dataregForsiden";
    }

    //ikke blevet testet med html og css
    @GetMapping("/delete/{lejeaftale_id}")
    public String deletelejeaftale(@PathVariable("lejeaftale_id") int lejeaftale_id ) {
        boolean deleted = lejeaftaleService.deleteLejeaftale(lejeaftale_id);
        if(deleted) {
            return "redirect:/lejeaftale/dataregForsiden";
        } else{
            return "redirect:/lejeaftale/dataregForsiden";
        }
    }


    @GetMapping("/update/{lejeaftale_id}")
    public String updateLejeaftaleForm(@PathVariable("lejeaftale_id") int lejeaftale_id, Model model) {
        Lejeaftale lejeaftale = lejeaftaleService.findLejeaftaleById(lejeaftale_id);

        model.addAttribute("lejeaftale",lejeaftale);
        return "home/lejeaftale/updateLejeaftale";
    }
    @PostMapping("/update")
    public String updateLejeaftale(@ModelAttribute Lejeaftale lejeaftale){
        lejeaftaleService.updateLejeaftale(lejeaftale);
        return "redirect:/lejeaftale/dataregForsiden";


    }

}
