package com.example.bilabonnement.Service;

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

    public List<Map<String, Object>> fetchAllLeased() {
        return forretningsRepository.fetchAllLeased();
    }
    public int getTotalLeasedIncome() {
        List<Map<String, Object>> udlejedeCars = fetchAllLeased();
        int totalIncome = 0;
        for(Map<String,Object> map: udlejedeCars) {
            totalIncome += (Integer) map.get("leasingPrice");
        }
        return totalIncome;
    }
    public int getTotalLeasedCars() {
        List<Map<String, Object>> udlejedeCars = fetchAllLeased();
        int totalAmount = 0;
        for(Map<String,Object> map: udlejedeCars) {
            totalAmount ++;
        }
        return totalAmount;
    }
}
