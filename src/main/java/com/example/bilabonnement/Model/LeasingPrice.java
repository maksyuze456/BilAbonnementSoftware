package com.example.bilabonnement.Model;

public class LeasingPrice {
    private int leasingPriceId;
    private String stelnummer;
    private int leasingPrice;

    public LeasingPrice() {
    }

    public LeasingPrice(int leasingPriceId, String stelnummer, int leasingPrice) {
        this.leasingPriceId = leasingPriceId;
        this.stelnummer = stelnummer;
        this.leasingPrice = leasingPrice;
    }

    public int getLeasingPriceId() {
        return leasingPriceId;
    }

    public void setLeasingPriceId(int leasingPriceId) {
        this.leasingPriceId = leasingPriceId;
    }


    public int getLeasingPrice() {
        return leasingPrice;
    }

    public void setLeasingPrice(int leasingPrice) {
        this.leasingPrice = leasingPrice;
    }

    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }
}
