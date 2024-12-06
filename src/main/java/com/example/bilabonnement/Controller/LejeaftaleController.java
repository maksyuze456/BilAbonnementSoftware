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


    @GetMapping("/lejeaftale/create")
    public String createLejeaftaleForm(Model model) {
        model.addAttribute("lejeaftale",new Lejeaftale());
        return "home/lejeaftale/createlejeaftale";
    }


    @PostMapping("/lejeaftale/create")
    public String createLejeaftale(@ModelAttribute Lejeaftale l) {
        lejeaftaleService.addLejeaftale(l);
        return "redirect:/";
    }

    //ikke blevet testet med html og css
    @GetMapping("/lejeaftale/delete/{lejeaftale_id}")
    public String deletelejeaftale(@PathVariable("lejeaftale_id") int lejeaftale_id ) {
        boolean deleted = lejeaftaleService.deleteLejeaftale(lejeaftale_id);
        if(deleted) {
            return "redirect:/";
        } else{
            return "redirect:/error";
        }
    }


    @GetMapping("/lejeaftale/update/{lejeaftale_id}")
    public String updateLejeaftaleForm(@PathVariable("lejeaftale_id") int lejeaftale_id, Model model) {
        Lejeaftale lejeaftale=lejeaftaleService.findLejeaftaleById(lejeaftale_id);
        model.addAttribute("lejeaftale",lejeaftale);
        return "home/lejeaftale/updateLejeaftale";
    }
    @PostMapping("/lejeaftale/update")
    public String updateLejeaftale(@ModelAttribute Lejeaftale lejeaftale){
        lejeaftaleService.updateLejeaftale(lejeaftale);
        return "redirect:/";


    }

}
