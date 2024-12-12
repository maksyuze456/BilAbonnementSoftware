package com.example.bilabonnement.Model;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "rapport")
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rapportID;
        private String beskrivelse;
        private Date oprettetDato;
        private String stelnummer;

        public Rapport(){}


    public Rapport(int rapportID, String beskrivelse, Date oprettetDato, String stelnummer){
            this.rapportID = rapportID;
            this.beskrivelse = beskrivelse;
            this.oprettetDato = oprettetDato;
            this.stelnummer = stelnummer;
        }
    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }


        public int getRapportID() {
            return rapportID;
        }

        public void setRapportID(int rapportID) {
            this.rapportID = rapportID;
        }

        public String getBeskrivelse() {
            return beskrivelse;
        }

        public void setBeskrivelse(String beskrivelse) {
            this.beskrivelse = beskrivelse;
        }

        public Date getOprettetDato() {
            return oprettetDato;
        }

        public void setOprettetDato(Date oprettetDato) {
            this.oprettetDato = oprettetDato;
        }

    }

