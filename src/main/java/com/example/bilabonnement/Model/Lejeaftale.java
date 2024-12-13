package com.example.bilabonnement.Model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "lejeaftale")
public class Lejeaftale {
    @Id

    private int lejeaftale_id;
    private int kunde_nr;
    private String stelnummer;


    private Date start_dato;


    private Date slut_dato;
    private Double pris;
    private String afhentningsted;
    public Lejeaftale(){

    }
    public Lejeaftale(int lejeaftale_id, int kunde_nr, String stelnummer, Date start_dato, Date slut_dato, Double pris,String afhentningsted) {
        this.lejeaftale_id = lejeaftale_id;
        this.kunde_nr = kunde_nr;
        this.stelnummer = stelnummer;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.pris = pris;
        this.afhentningsted=afhentningsted;
    }

    public int getLejeaftale_id() {
        return lejeaftale_id;
    }

    public void setLejeaftale_id(int lejeaftale_id) {
        this.lejeaftale_id = lejeaftale_id;
    }

    public int getKunde_nr() {
        return kunde_nr;
    }

    public void setKunde_nr(int kunde_nr) {
        this.kunde_nr = kunde_nr;
    }

    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }

    public Date getStart_dato() {
        return start_dato;
    }

    public void setStart_dato(Date start_dato) {
        this.start_dato = start_dato;
    }

    public Date getSlut_dato() {
        return slut_dato;
    }

    public void setSlut_dato(Date slut_dato) {
        this.slut_dato = slut_dato;
    }

    public Double getPris() {
        return pris;
    }

    public void setPris(Double pris) {
        this.pris = pris;
    }

    public String getAfhentningsted() {
        return afhentningsted;
    }

    public void setAfhentningsted(String afhentningsted) {
        this.afhentningsted = afhentningsted;
    }
}
