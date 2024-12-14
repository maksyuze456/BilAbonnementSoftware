package com.example.bilabonnement.Controller;
import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.LejeaftaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.util.JpaMetamodel;
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


    @GetMapping("/create/{stelnummer}")
    public String createLejeaftaleForm(Model model, @PathVariable("stelnummer") String stelnummer) {
        Lejeaftale lejeaftale = new Lejeaftale();
        lejeaftale.setStelnummer(stelnummer);
        model.addAttribute("lejeaftale", lejeaftale);
        return "home/lejeaftale/createlejeaftale";
    }
    @GetMapping("/create/chooseCar/{bilStatus}")
    public String chooseCar(Model model, @PathVariable("bilStatus")String bilStatus){
        model.addAttribute("carList", bilService.fetchAllCarsByStatus(bilStatus));
        return "home/lejeaftale/chooseCar";
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
    @GetMapping("/view/{lejeaftale_id}")
    public String viewLejeaftale(@PathVariable("lejeaftale_id") int lejeaftale_id, Model model) {

        model.addAttribute("lejeaftale", lejeaftaleService.findLejeaftaleById(lejeaftale_id));

        return "home/lejeaftale/viewLejeaftale";
    }

}
