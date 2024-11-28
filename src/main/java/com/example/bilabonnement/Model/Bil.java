package com.example.bilabonnement.Model;
import jakarta.persistence.*;


@Entity
@Table(name="bil")
public class Bil {
    @Id
    @Column(name="stelnummer",length =17,nullable =false)
    private String stelnummer;

    @Column (name="mærke",nullable=false)
    private String mærke;

    @Column(name="model",nullable=false)
    private String model;

    @Column(name="brandstof")
    private String brandstof;

    @Column(name="odometer")
    private int odometer;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;


} //mangler getter og sætter
