package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Repository.LejeaftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LejeaftaleService {

    @Autowired
    LejeaftaleRepository lejeaftaleRepository;

    // Henter alle lejeaftaler fra repository
    public List<Lejeaftale> fetchAllLejeaftale() {
        return lejeaftaleRepository.fetchAllLejeaftale();
    }

    // Henter alle kunder fra repository
    public List<Kunde> fetchAllKunder() {
        return lejeaftaleRepository.fetchAllKunder();
    }

    // Henter biler fra repository baseret på bilens status
    public List<Bil> fetchAllCarsByStatus(String bilStatus) {
        return lejeaftaleRepository.fetchAllCarsByStatus(bilStatus);
    }

    public int getAntalLejeaftalerByStatus(String status) {
        List<Lejeaftale> lejeaftaler = fetchAllLejeaftalerByStatus(status);
        int counter = 0;
        for(Lejeaftale lejeaftale: lejeaftaler) {
            counter++;
        }
        return counter;
    }

    // Henter alle lejeaftaler efter deres status fra repository
    public List<Lejeaftale> fetchAllLejeaftalerByStatus(String lejeaftaleStatus) {
        return lejeaftaleRepository.fetchAllLejeaftalerByStatus(lejeaftaleStatus);
    }

    // Tilføjer en ny lejeaftale ved at kalde på repository
    public void addLejeaftale(Lejeaftale l) {
        lejeaftaleRepository.addLejeaftale(l);
    }

    // Finder en specifik lejeaftale baseret på ID ved at kalde repository
    public Lejeaftale findLejeaftaleById(int lejeaftale_id) {
        return lejeaftaleRepository.findLejeaftaleById(lejeaftale_id);
    }

    // Opdaterer en eksisterende lejeaftale ved at kalde repository
    public void updateLejeaftale(Lejeaftale l) {
        lejeaftaleRepository.updateLejeaftale(l);
    }
}