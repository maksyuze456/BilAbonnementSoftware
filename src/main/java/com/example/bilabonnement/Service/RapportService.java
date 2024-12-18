package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Rapport;
import com.example.bilabonnement.Repository.RapportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RapportService {
   @Autowired
    private RapportRepo rapportRepo;

    public List<Rapport> fetchAll(){
        return rapportRepo.fetchAll();
    }
    public void addRapport(Rapport r){rapportRepo.addRapport(r);}
    public Rapport findRapportById(int rapportID){
        return rapportRepo.findRapportByID(rapportID);
    }
    public Boolean deleteRapport(int rapportID){
        return rapportRepo.deleteRapport(rapportID);
    }
    public void updateRapport (Rapport r){rapportRepo.updateRapport(r);
    }
    public int getAntalRapporter(){
        List<Rapport> rapporter = fetchAll();
        int counter = 0;
        for(Rapport rapport: rapporter) {
            counter++;
        }
        return counter;
    }
    public int getCorrectLejeaftaleId(String lejeaftaleStatus, String stelnummer) {
        return rapportRepo.getCorrectLejeaftaleId(lejeaftaleStatus, stelnummer);
    }
    public List<Bil> fetchAllCarsByStatus(String bilStatus) {
        return rapportRepo.fetchAllCarsByStatus(bilStatus);
    }

    public void afslutLejeaftale(int lejeaftaleId, String afsluttet) {
        rapportRepo.afslutLejeaftale(lejeaftaleId, afsluttet);
    }

    public void opdaterBilStatus(String stelnummer, String bilStatus) {
        rapportRepo.opdaterBilStatus(stelnummer, bilStatus);
    }
}
