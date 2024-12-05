package com.example.bilabonnement.Controller;
import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.LejeaftaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LejeaftaleController {

    @Autowired
    BilService bilService;
    @Autowired
    LejeaftaleService lejeaftaleService;


    @GetMapping("/lejeaftale/create")
    public String createLejeaftale(Model moel) {
        moel.addAttribute("lejeaftale",new Lejeaftale());
        return "home/lejeaftale/createlejeaftale";
    }


    @PostMapping("/lejeaftale/create")
    public String createLejeaftale(@ModelAttribute Lejeaftale l) {
        lejeaftaleService.addLejeaftale(l);
        return "redirect:/";
    }

    //ikke blevet testet med html og css
    @GetMapping("/lejeaftale/delete/{id}")
    public String deletelejeaftale(@PathVariable("id") int id) {
        boolean deleted = lejeaftaleService.deleteLejeaftale(id);
        if(deleted) {
            return "redirect:/";
        } else{
            return "redirect:/";
        }
    }


    @GetMapping("/lejeaftale/update/{lejeaftal_id}")
    public String updateLejeaftale(@PathVariable("lejeaftal_id") int lejeaftale_id, Model model) {
        model.addAttribute("lejeaftale_id", lejeaftaleService.findLejeaftaleById(lejeaftale_id));
        return "home/lejeaftale/updateLejeaftale";
    }
    @PostMapping("/lejeaftale/update")
    public String updateLejeaftale(@ModelAttribute Lejeaftale lejeaftale){
        lejeaftaleService.updateejeaftale(lejeaftale);
        return "redirect/";


    }

}
