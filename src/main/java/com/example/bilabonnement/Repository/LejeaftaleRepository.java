package com.example.bilabonnement.Repository;

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

    public List<Lejeaftale> fetchAllLejeaftale() {
        String sql = "SELECT * FROM lejeaftale";
        RowMapper<Lejeaftale> rowMapper = new BeanPropertyRowMapper<>(Lejeaftale.class);
        return template.query(sql, rowMapper);
    }

    public void addLejeaftale(Lejeaftale l) {
        String sql = "INSERT INTO lejeaftale(lejeaftale_id, kunde_nr,stelnummer,start_dato,slut_dato,pris,afhentningsted) VALUES(?,?,?,?,?,?,?) ";
        template.update(sql, l.getLejeaftale_id(), l.getKunde_nr(), l.getStelnummer(), l.getStart_dato(), l.getSlut_dato(), l.getPris(), l.getAfhentningsted());
    }

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

    public Boolean deleteLejeaftale(int lejeaftale_id) {
        String sql = "DELETE FROM lejeaftale WHERE  lejeaftale_id =?";
        return template.update(sql, lejeaftale_id) > 0;

    }

    public void updateLejeaftale(Lejeaftale l) {
        String sql = "UPDATE lejeaftale SET start_dato=?,slut_dato=?,pris=?,afhentningsted=? WHERE lejeaftale_id=? ";
        template.update(sql, l.getStart_dato(), l.getSlut_dato(), l.getPris(), l.getAfhentningsted(), l.getLejeaftale_id());
    }











    public void opdaterLejeaftaleStatus(int lejeaftaleId, String status) {
        String sql = "UPDATE Lejeaftale SET lejeaftaleStatus = ? WHERE id = ?";
        template.update(sql, status, lejeaftaleId);
    }

    public String findStelnummerByLejeaftaleId(int lejeaftaleId) {
        String sql = "SELECT stelnummer FROM Lejeaftale WHERE id = ?";
        return template.queryForObject(sql, new Object[]{lejeaftaleId}, String.class);
    }






}
