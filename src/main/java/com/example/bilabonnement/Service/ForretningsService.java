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

    public List<Map<String, Object>> fetchAllCarsByStatusWithPrice(String bilStatus) {
        return forretningsRepository.fetchAllCarsByStatusWithPrice(bilStatus);
    }
    public int getTotalIncomeByCarStatus(String bilStatus) {
        List<Map<String, Object>> cars = fetchAllCarsByStatusWithPrice(bilStatus);
        int totalIncome = 0;
        for(Map<String,Object> map: cars) {
            totalIncome = addPricesTogether(totalIncome, (Integer) map.get("leasingPrice"));
        }
        return totalIncome;
    }
    public int addPricesTogether(int totalSoFar, int priceToAdd) {
        return totalSoFar + priceToAdd;
    }
    public int getTotalCarsByStatus(String bilStatus) {
        List<Map<String, Object>> cars = fetchAllCarsByStatusWithPrice(bilStatus);
        int totalAmount = 0;
        for(Map<String,Object> map: cars) {
            totalAmount ++;
        }
        return totalAmount;
    }

    public Map<String, Object> fetchCarByStelnummerWithLeasingPrice(String stelnummer) {
        return forretningsRepository.fetchCarByStelnummerWithLeasingPrice(stelnummer);
    }

    public String getImgByStelnummer(String stelnummer) {
        Map<String, Object> carImage = forretningsRepository.fetchImgByStelnummer(stelnummer);
        try {
            return (String) carImage.get("url");
        } catch(NullPointerException e) {
            return "";
        }
    }

    public Bil fetchCarByStelnummer(String stelnummer) {
        return forretningsRepository.fetchCarObjByStelnummer(stelnummer);
    }

    public void updateCar(Bil car) {
        forretningsRepository.updateCar(car);
    }

    public void addCar(Bil car){
        forretningsRepository.createCar(car);
    }
}
