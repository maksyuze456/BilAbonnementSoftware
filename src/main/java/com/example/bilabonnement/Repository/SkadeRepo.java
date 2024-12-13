// SkadeRepo.java (Repository)
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
        String sql = "INSERT INTO skader (rapportid, skadebeskrivelse, kategori, pris) VALUES (?, ?, ?, ?)";
        template.update(sql, skade.getRapportID(), skade.getSkadebeskrivelse(), skade.getKategori(), skade.getPris());
    }

    public List<Skade> findSkaderByRapportID(int rapportID) {
        String sql = "SELECT * FROM skader WHERE rapportid = ?";
        RowMapper<Skade> rowMapper = new BeanPropertyRowMapper<>(Skade.class);
        return template.query(sql, rowMapper, rapportID);
    }
    public void updateSkade(Skade skade) {
        String sql = "UPDATE skader SET skadebeskrivelse = ?, kategori = ?, pris = ? WHERE skadeid = ?";
        template.update(sql, skade.getSkadebeskrivelse(), skade.getKategori(), skade.getPris(), skade.getSkadeID());
    }

    public void deleteSkadeById(int skadeID) {
        String sql = "DELETE FROM skader WHERE skadeid = ?";
        template.update(sql, skadeID);
    }

    public double getTotalPriceByRapportID(int rapportID) {
        String sql = "SELECT COALESCE(SUM(pris), 0) FROM skader WHERE rapportid = ?";
        return template.queryForObject(sql, Double.class, rapportID);
    }
    public Skade findSkadeById(int skadeID) {
        String sql = "SELECT * FROM skader WHERE skadeid = ?";
        RowMapper<Skade> rowMapper = new BeanPropertyRowMapper<>(Skade.class);
        return template.queryForObject(sql, rowMapper, skadeID);
    }
}

