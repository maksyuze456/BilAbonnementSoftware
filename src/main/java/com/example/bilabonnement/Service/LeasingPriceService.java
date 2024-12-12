package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.LeasingPrice;
import com.example.bilabonnement.Repository.LeasingPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeasingPriceService {

    @Autowired
    LeasingPriceRepository leasingPriceRepository;

    public void addPrice(LeasingPrice leasingPrice) {
        leasingPriceRepository.addPrice(leasingPrice);
    }
    public void updateLeasingPriceByStelnummer(String stelnummer, LeasingPrice leasingPrice){
        leasingPriceRepository.updateLeasingPriceByStelnummer(stelnummer, leasingPrice);
    }
    public LeasingPrice fetchLeasingPriceByStelnummer(String stelnummer) {
        return leasingPriceRepository.fetchLeasingPriceByStelnummer(stelnummer);
    }

}
