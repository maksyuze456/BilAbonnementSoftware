package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.LeasingPrice;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Table(
        name = "bil",
        catalog = "gruppe8af2"
)
@Repository
public class ForretningsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Tager alle biler ud af databasen efter hvilken status bilerne har
    public List<Bil> fetchAllCarsByStatus(String carStatus) {
        String sql = "SELECT * from bil where bilStatus = ?";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return jdbcTemplate.query(sql, rowMapper, carStatus);

    }
    
    // Tager en enkel bil ud af databasen efter hvilken stelnummer der bliver sendt i parametrene
    public Bil fetchCarByStelnummer(String stelnummer) {
        String sql = "SELECT * from bil where stelnummer = ?";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, stelnummer);

    }

    // Tager url for billedet af bilen efter hvilken stelnummer
    // Hvis ingen billede til den specifikke bil ikke eksister, returner den null.
    public Map<String, Object> fetchImgByStelnummer(String stelnummer) {
        String sql = "select url from carsImages where stelnummer = ?";
        try {
            return jdbcTemplate.queryForMap(sql, stelnummer);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Opretter en bil i databasen
    public void createCar(Bil car) {
        String sql = "INSERT INTO bil(stelnummer,mærke,model,brandstof,odometer, bilStatus)VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,car.getStelnummer(), car.getMærke(),car.getModel(),car.getBrandstof(),car.getOdometer(),car.getBilStatus());
    }

    // Opdaterer en bil i databasen
    public void updateCar(Bil car){
        String sql = "update bil set mærke = ?, model = ?, brandstof = ?, odometer = ? where bil.stelnummer = ?;";
        jdbcTemplate.update(sql, car.getMærke(), car.getModel(), car.getBrandstof(), car.getOdometer(), car.getStelnummer());
    }
}
