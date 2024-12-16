package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Repository.BilRepository;
import com.example.bilabonnement.Repository.LejeaftaleRepository;
import jakarta.transaction.Transactional;
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
















    @Transactional
    public void afslutLejeaftale(int lejeaftale_id, String lejeaftaleStatus) {
        lejeaftaleRepository.opdaterLejeaftaleStatus(lejeaftale_id, lejeaftaleStatus);
        String stelnummer = findStelnummerByLejeaftaleId(lejeaftale_id);

        if (stelnummer != null) {

            bilService.opdaterBilStatus(stelnummer, "utilgængelig");
        } else {
            System.out.println("Ingen bil fundet for lejeaftale ID: " + lejeaftale_id);
        }
    }
    public String findStelnummerByLejeaftaleId(int lejeaftale_id) {

        return lejeaftaleRepository.findStelnummerByLejeaftaleId(lejeaftale_id);
    }

    }
