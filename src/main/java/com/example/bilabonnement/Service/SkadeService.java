package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Skade;
import com.example.bilabonnement.Repository.SkadeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkadeService {

    private final SkadeRepo skadeRepo;

    public SkadeService(SkadeRepo skadeRepo) {
        this.skadeRepo = skadeRepo;
    }

    public void addSkade(Skade skade) {
        skadeRepo.addSkade(skade);
    }

    public List<Skade> findSkaderByRapportID(int rapportID) {return skadeRepo.findSkaderByRapportID(rapportID);}

    public void updateSkade(Skade skade) {
        skadeRepo.updateSkade(skade);
    }

    public void deleteSkadeById(int skadeID) {
        skadeRepo.deleteSkadeById(skadeID);
    }
    public void deleteAllSkaderByRapportId(int rapportID) {
        List<Skade> skader = findSkaderByRapportID(rapportID);
        for(Skade skade: skader) {
            deleteSkadeById(skade.getSkadeID());
        }
    }

    public double getTotalPriceByRapportID(int rapportID) {
        return skadeRepo.getTotalPriceByRapportID(rapportID);
    }
    public Skade findSkadeById(int skadeID) {return skadeRepo.findSkadeById(skadeID);}
}

