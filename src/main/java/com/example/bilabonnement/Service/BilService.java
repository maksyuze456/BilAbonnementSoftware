package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilService {
    @Autowired
    BilRepository bilRepository;
    public List<Bil>fetchAll(){
        return bilRepository.fetchAll();
    }
    public void addBil(Bil b){
        bilRepository.addBil(b);
    }

}

