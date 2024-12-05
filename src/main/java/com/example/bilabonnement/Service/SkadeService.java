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


        public List<Skade> findSkaderByRapportID(int rapportID) {
            return skadeRepo.findSkaderByRapportID(rapportID);
        }

        public double TotalPriceByRapportID(int rapportID) {
            return skadeRepo.TotalPriceByRapportID(rapportID);
        }
}
