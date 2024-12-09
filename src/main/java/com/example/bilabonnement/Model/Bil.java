package com.example.bilabonnement.Model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bil")
public class Bil {
    @Id
    private String stelnummer;

    private String mærke;

    private String model;

    private String brandstof;

    private int odometer;

    private String afhentningsted;
public Bil(){}

public Bil(String stelnummer, String mærke, String model, String brandstof, int odometer,String afhentningsted) {
    this.stelnummer = stelnummer;
    this.mærke = mærke;
    this.model = model;
    this.brandstof = brandstof;
    this.odometer = odometer;
    this.afhentningsted=afhentningsted;
}



    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }

    public String getMærke() {
        return mærke;
    }

    public void setMærke(String mærke) {
        this.mærke = mærke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrandstof() {
        return brandstof;
    }

    public void setBrandstof(String brandstof) {
        this.brandstof = brandstof;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }


public String getAfhentningsted() {
    return afhentningsted;
}

public void setAfhentningsted(String afhentningsted) {
    this.afhentningsted = afhentningsted;
}

}