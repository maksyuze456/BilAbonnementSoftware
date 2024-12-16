package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Repository.ForretningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ForretningsService {
    @Autowired
    ForretningsRepository forretningsRepository;

    // kalder på Repository metode for at få biler ud efter status
    public List<Bil> fetchAllCarsByStatus(String bilStatus) {
        return forretningsRepository.fetchAllCarsByStatus(bilStatus);
    }
    // Laver en udregning af bilernes leasing pris efter hvilken status bilen har
    public double getTotalIncomeByCarStatus(String bilStatus) {
        List<Bil> biler = fetchAllCarsByStatus(bilStatus);

        double totalIncome = 0;
        for(Bil bil: biler) {
            totalIncome = addPricesTogether(totalIncome, bil.getPris());
        }
        return totalIncome;
    }
    // simpel metode der tilføjer priser
    public double addPricesTogether(double totalSoFar, double priceToAdd) {
        return totalSoFar + priceToAdd;
    }

    // returnerer en int af antal biler efter status
    public int getTotalCarsByStatus(String bilStatus) {
        List<Bil> biler = fetchAllCarsByStatus(bilStatus);
        return biler.size();
    }

    // kalder på repository metode for den enkelte bil efter stelnummer
    public Bil fetchCarByStelnummer(String stelnummer) {
        return forretningsRepository.fetchCarByStelnummer(stelnummer);
    }

    // kalder på repository metode for den enkelte bils billede efter stelnummer
    // try catch prøver at returnere url String for billedet,
    // hvis det man fik ud af databasen af null, bliver der returneret en tom String.
    public String getImgByStelnummer(String stelnummer) {
        Map<String, Object> carImage = forretningsRepository.fetchImgByStelnummer(stelnummer);
        try {
            return (String) carImage.get("url");
        } catch(NullPointerException e) {
            return "";
        }
    }

    // kalder på repository metode der opdaterer en bil
    // tager en bil i parameter hvilkens værdier bliver brugt til tilføjelse i databasen
    public void updateCar(Bil car) {
        forretningsRepository.updateCar(car);
    }

    // kalder på repository metode der tilføjer en bil
    // tager en bil i parameter hvilkens værdier bliver brugt til tilføjelse i databasen
    public void addCar(Bil car){
        forretningsRepository.createCar(car);
    }
}
