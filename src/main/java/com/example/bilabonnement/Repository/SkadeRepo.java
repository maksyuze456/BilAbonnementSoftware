package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Skade;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkadeRepo {

    private final JdbcTemplate template;

    public SkadeRepo(JdbcTemplate template) {
        this.template = template;
    }
    public void addSkade(Skade skade) {
        String sql = "INSERT INTO skade (rapportID, pris) VALUES (?, ?)";
        template.update(sql, skade.getRapportID(), skade.getPris());
    }


    public List<Skade> findSkaderByRapportID(int rapportID) {
        String sql = "SELECT * FROM skade WHERE rapportID = ?";
        RowMapper<Skade> rowMapper = new BeanPropertyRowMapper<>(Skade.class);
        return template.query(sql, rowMapper, rapportID);
    }

    public double TotalPriceByRapportID(int rapportID) {
        String sql = "SELECT COALESCE(SUM(pris), 0) FROM skade WHERE rapportID = ?";
        return template.queryForObject(sql, Double.class, rapportID);
    }
}
