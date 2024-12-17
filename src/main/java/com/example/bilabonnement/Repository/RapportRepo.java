package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Rapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RapportRepo {
    @Autowired
    JdbcTemplate template;

    public List<Rapport> fetchAll(){
        String sql = "SELECT * FROM rapport";
        RowMapper<Rapport>rowMapper = new BeanPropertyRowMapper<>(Rapport.class);
        return template.query(sql,rowMapper);
    }

    public void addRapport(Rapport r){
        String sql = "INSERT INTO rapport(stelnummer, beskrivelse, oprettetdato, lejeaftale_id) VAlues(?,?,?,?)";
        template.update(sql,r.getStelnummer(), r.getBeskrivelse(),r.getOprettetDato(), r.getLejeaftale_id());
    }
    public List<Bil> fetchAllCarsByStatus(String carStatus) {
        String sql = "SELECT * from bil where bilStatus = ?";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper, carStatus);
    }
    public int getCorrectLejeaftaleId(String lejeaftaleStatus,String stelnummer) {
        String sql = "select lejeaftale_id from lejeaftale where stelnummer = ? AND lejeaftaleStatus = ?;";
        Map<String, Object> map = template.queryForMap(sql, stelnummer, lejeaftaleStatus);
        return (int) map.get("lejeaftale_id");
    }

    public Rapport findRapportByID(int rapportID){
        String sql ="SELECT * FROM rapport WHERE rapportID = ?";
        RowMapper<Rapport> rowMapper = new BeanPropertyRowMapper<>(Rapport.class);
        Rapport r = template.queryForObject(sql, rowMapper, rapportID);
        return r;
    }

    public Boolean deleteRapport(int rapportID){
        String sql = "DELETE FROM rapport WHERE rapportID = ?";
        return template.update(sql, rapportID) > 0;
    }
    public void updateRapport(Rapport r) {
        String sql = "UPDATE rapport SET beskrivelse = ?, oprettetDato = ?, stelnummer = ? WHERE rapportID = ?";
        template.update(sql, r.getBeskrivelse(), r.getOprettetDato(), r.getStelnummer(), r.getRapportID());
    }

    public void afslutLejeaftale(int lejeaftaleId, String afsluttet) {
        String sql = "update lejeaftale set lejeaftaleStatus = ? where lejeaftale_id = ?";
        template.update(sql, afsluttet, lejeaftaleId);
    }

    public void opdaterBilStatus(String stelnummer, String bilStatus) {
        String sql = "update bil set bilStatus = ? where stelnummer = ?";
        template.update(sql, bilStatus, stelnummer);
    }
}
