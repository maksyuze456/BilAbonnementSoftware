package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BilRepository {
    @Autowired
    JdbcTemplate template;

    public List<Bil> fetchAllBil() {
        String sql = "SELECT * FROM bil";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }

    public void addBil(Bil bil) {

        String sql = "INSERT INTO bil(stelnummer,mærke,model,brandstof,odometer)VALUES(?,?,?,?,?)";
        template.update(sql, bil.getStelnummer(), bil.getMærke(), bil.getModel(), bil.getBrandstof(), bil.getOdometer());
    }

    public Bil findBilByStelNummer(String stelnummer) {
        String sql = "SELECT * FROM bil WHERE stelnummer=?";

        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        Bil b = template.queryForObject(sql, rowMapper, stelnummer);
        return b;


    }


    public Boolean deleteBil(String stelnummer) {
        String sql = "DELETE FROM bil WHERE stelnummer=?";
        return template.update(sql, stelnummer) > 0;

    }

    public void updateBil(Bil b) {
        String sql = "UPDATE bil SET mærke=?,model=?,brandstof=?,odometer=? WHERE stelnummer=? ";
        template.update(sql, b.getMærke(), b.getModel(), b.getBrandstof(), b.getOdometer(), b.getStelnummer());
    }






    public void opdaterStatus(String stelnummer, String nyStatus) {
        String sql = "UPDATE Bil SET bilStatus = ? WHERE stelnummer = ?";
        template.update(sql, nyStatus, stelnummer);
    }
}