package com.example.bilabonnement.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "skader")
public class Skade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skadeID;

    private int rapportID;
    private String skadebeskrivelse;
    private String kategori;
    private double pris;

    public Skade() {

    }

    public Skade(int skadeID, int rapportID, String skadebeskrivelse, String kategori, double pris) {
        this.skadeID = skadeID;
        this.rapportID = rapportID;
        this.skadebeskrivelse = skadebeskrivelse;
        this.kategori = kategori;
        this.pris = pris;
    }

    public int getSkadeID() {
        return skadeID;
    }

    public void setSkadeID(int skadeID) {
        this.skadeID = skadeID;
    }

    public int getRapportID() {
        return rapportID;
    }

    public void setRapportID(int rapportID) {
        this.rapportID = rapportID;
    }

    public String getSkadebeskrivelse() {
        return skadebeskrivelse;
    }

    public void setSkadebeskrivelse(String skadebeskrivelse) {
        this.skadebeskrivelse = skadebeskrivelse;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
}