package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Repository.BilRepository;
import com.example.bilabonnement.Repository.LejeaftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bilabonnement.Repository.LejeaftaleRepository.*;

@Service
public class LejeaftaleService {

    @Autowired
    LejeaftaleRepository lejeaftaleRepository;
@Autowired
   private BilService bilService;

    public LejeaftaleService(LejeaftaleRepository lejeaftaleRepository) {
        this.lejeaftaleRepository = lejeaftaleRepository;
    }

    public List<Lejeaftale> fetchAllLejeaftale() {
        return lejeaftaleRepository.fetchAllLejeaftale();
    }

    public void addLejeaftale(Lejeaftale l) {
        lejeaftaleRepository.addLejeaftale(l);
    }

    public Lejeaftale findLejeaftaleById(int lejeaftale_id) {
        return lejeaftaleRepository.findLejeaftaleById(lejeaftale_id);
    }

    public Boolean deleteLejeaftale(int lejeaftale_id) {
        return lejeaftaleRepository.deleteLejeaftale(lejeaftale_id);
    }

    public void updateLejeaftale(Lejeaftale l) {
        lejeaftaleRepository.updateLejeaftale(l);
    }

















    public void afslutLejeaftale(int lejeaftale_Id, String lejeaftaleStatus) {
        // Opdater lejeaftalens status
        lejeaftaleRepository.opdaterLejeaftaleStatus(lejeaftale_Id, lejeaftaleStatus);

        // Hent stelnummer baseret på lejeaftale_ID
        String stelnummer = findStelnummerByLejeaftaleId(lejeaftale_Id);

        // Opdater bilens status til "utilgængelig"
        bilService.opdaterBilStatus(stelnummer, "utilgængelig");
    }

    public String findStelnummerByLejeaftaleId(int lejeaftale_Id) {
        // Hent stelnummer via repository
        return lejeaftaleRepository.findStelnummerByLejeaftaleId(lejeaftale_Id);
    }

    }
