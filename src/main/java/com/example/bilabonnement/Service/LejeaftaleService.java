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

    public List<Lejeaftale> fetchAllLejeaftale(){
        return lejeaftaleRepository.fetchAllLejeaftale();
    }
    public void addLejeaftale(Lejeaftale l){
        lejeaftaleRepository.addLejeaftale(l);

    }
    public Boolean deleteLejeaftaleById(int id){
        return lejeaftaleRepository.deleteLejeaftaleById(id);
    }
    public void updateLejeaftale(Lejeaftale l){
        lejeaftaleRepository.updateLejeaftale(l);
    }
}
