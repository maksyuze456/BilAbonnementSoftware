package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.LeasingPrice;
import com.example.bilabonnement.Repository.ForretningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ForretningsService {
    @Autowired
    ForretningsRepository forretningsRepository;

    public List<Bil> fetchAllCarsByStatus(String bilStatus) {
        return forretningsRepository.fetchAllCarsByStatus(bilStatus);
    }
    public double getTotalIncomeByCarStatus(String bilStatus) {
        List<Bil> biler = fetchAllCarsByStatus(bilStatus);

        double totalIncome = 0;
        for(Bil bil: biler) {
            totalIncome = addPricesTogether(totalIncome, bil.getPris());
        }
        return totalIncome;
    }
    public double addPricesTogether(double totalSoFar, double priceToAdd) {
        return totalSoFar + priceToAdd;
    }
    public int getTotalCarsByStatus(String bilStatus) {
        List<Bil> biler = fetchAllCarsByStatus(bilStatus);
        int totalAmount = 0;
        for(Bil bil: biler) {
            totalAmount ++;
        }
        return totalAmount;
    }

    public Bil fetchCarByStelnummer(String stelnummer) {
        return forretningsRepository.fetchCarByStelnummer(stelnummer);
    }

    public String getImgByStelnummer(String stelnummer) {
        Map<String, Object> carImage = forretningsRepository.fetchImgByStelnummer(stelnummer);
        try {
            return (String) carImage.get("url");
        } catch(NullPointerException e) {
            return "";
        }
    }

    public void updateCar(Bil car) {
        forretningsRepository.updateCar(car);
    }

    public void addCar(Bil car){
        forretningsRepository.createCar(car);
    }
}
