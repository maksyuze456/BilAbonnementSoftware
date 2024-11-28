package com.example.bilabonnement.Model;

public class Bil {


    private String stelnummer;

    private String mærke;

    private String model;

    private String brandstof;

    private int odometer;

    private Status status;
    public enum Status {
        AKTIV,
        UNAKTIV
    }

    public String getStelnummer(){
        return stelnummer;
    }

    public void setStelnummer(String stelnummer){
        this.stelnummer=stelnummer;
    }

    public String getMærke(){
        return mærke;
    }
    public void setMærke(String mærke){
        this.mærke=mærke;
    }
    public String getModel(){
        return model;
    }

    public void  setModel(String model){
        this.model=model;
    }

    public String getBrandstof(){
        return brandstof;
    }

    public void setBrandstof(String brandstof){
        this.brandstof=brandstof;
    }

    public int getOdometer(){
        return odometer;
    }

    public void setOdometer(int odometer){
        this.odometer=odometer;
    }

    public Status getStatus(){
        return status;

    }
    public void setStatus(Status status){
        this.status=status;
    }
}

