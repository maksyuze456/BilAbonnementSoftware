package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Lejeaftale;
import com.example.bilabonnement.Repository.LejeaftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LejeaftaleService {

    @Autowired
    LejeaftaleRepository lejeaftaleRepository;
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
}