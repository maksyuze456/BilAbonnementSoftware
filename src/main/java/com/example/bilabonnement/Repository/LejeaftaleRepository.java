package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.Lejeaftale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LejeaftaleRepository {
    @Autowired
    JdbcTemplate template;

    // Henter alle lejeaftaler fra databasen
    public List<Lejeaftale> fetchAllLejeaftale () {
        String sql = "SELECT * FROM lejeaftale";
        RowMapper<Lejeaftale> rowMapper = new BeanPropertyRowMapper<>(Lejeaftale.class);
        return template.query(sql, rowMapper);
    }

    // Henter alle kunder fra databasen
    public List<Kunde> fetchAllKunder() {
        String sql = "select * from kunde";
        RowMapper<Kunde> rowMapper =  new BeanPropertyRowMapper<>(Kunde.class);
        return template.query(sql, rowMapper);
    }

    // Henter biler fra databasen efter bilens status
    public List<Bil> fetchAllCarsByStatus(String carStatus) {
        String sql = "SELECT * from bil where bilStatus = ?";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper, carStatus);
    }

    // Sætter en bils status til "Udlejet" og opdaterer bilens pris
    public void setBilUdlejet(String stelnummer, Double pris) {
        String sql = "UPDATE bil SET pris = ?, bilStatus = ? WHERE stelnummer = ?";
        template.update(sql, pris, "Udlejet", stelnummer);
    }

    // Henter alle lejeaftaler efter status
    public List<Lejeaftale> fetchAllLejeaftalerByStatus (String status) {
        String sql = "SELECT * FROM lejeaftale where lejeaftaleStatus = ?";
        RowMapper<Lejeaftale> rowMapper = new BeanPropertyRowMapper<>(Lejeaftale.class);
        return template.query(sql, rowMapper, status);
    }

    // Tilføjer en ny lejeaftale til databasen og sætter den tilknyttede bil som udlejet
    public void addLejeaftale(Lejeaftale l){
        String sql ="INSERT INTO lejeaftale(kunde_nr,stelnummer,start_dato,slut_dato,pris,afhentningsted) VALUES(?,?,?,?,?,?) ";
        template.update(sql,l.getKunde_nr(),l.getStelnummer(),l.getStart_dato(),l.getSlut_dato(),l.getPris(),l.getAfhentningsted());
        setBilUdlejet(l.getStelnummer(), l.getPris());
    }

    // Finder en lejeaftale efter dens ID og returnerer den
    // Hvis der ikke findes nogen, returneres null
    public Lejeaftale findLejeaftaleById(int lejeaftale_id) {
        String sql = "SELECT * FROM lejeaftale WHERE lejeaftale_id = ?";
        RowMapper<Lejeaftale> rowMapper = new BeanPropertyRowMapper<>(Lejeaftale.class);
        try {
            return template.queryForObject(sql, rowMapper, lejeaftale_id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No lejeaftale found for ID: " + lejeaftale_id);
            return null;
        }
    }

    // Opdaterer en eksisterende lejeaftale i databasen
    public void updateLejeaftale(Lejeaftale l){
        String sql = "UPDATE lejeaftale SET start_dato=?,slut_dato=?,pris=?,afhentningsted=? WHERE lejeaftale_id=? " ;
        template.update(sql,l.getStart_dato(),l.getSlut_dato(),l.getPris(),l.getAfhentningsted(),l.getLejeaftale_id());
    }
}
