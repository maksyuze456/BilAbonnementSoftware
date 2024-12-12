package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.LeasingPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ForretningsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> fetchAllCarsByStatusWithPrice(String carStatus) {
        String sql = "SELECT bil.stelnummer, bil.mærke, bil.model, bil.brandstof, bil.bilStatus, leasingPrices.leasingPrice FROM bil " +
                     "left join leasingprices " +
                     "on leasingprices.stelnummer = bil.stelnummer " +
                     "where bil.bilStatus = ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, carStatus);
        return results;

    }
    public Map<String, Object> fetchCarByStelnummerWithLeasingPrice(String stelnummer) {
        String sql = "SELECT bil.stelnummer, bil.mærke, bil.model, bil.brandstof, bil.odometer, bil.bilStatus, leasingPrices.leasingPrice FROM bil\n" +
                "\t left join leasingprices\n" +
                "\ton leasingprices.stelnummer = bil.stelnummer\n" +
                "    where bil.stelnummer = ?;";
        Map<String, Object> results = jdbcTemplate.queryForMap(sql, stelnummer);
        return results;

    }

    public Bil fetchCarObjByStelnummer(String stelnummer) {
        String sql = "SELECT * FROM bil where bil.stelnummer = ?";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, stelnummer);
    }

    public Map<String, Object> fetchImgByStelnummer(String stelnummer) {
        String sql = "select url from carsImages where stelnummer = ?";
        try {
            return jdbcTemplate.queryForMap(sql, stelnummer);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void createCar(Bil car) {
        String sql = "INSERT INTO bil(stelnummer,mærke,model,brandstof,odometer, bilStatus)VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,car.getStelnummer(), car.getMærke(),car.getModel(),car.getBrandstof(),car.getOdometer(),car.getBilStatus());
    }

    public void updateCar(Bil car){
        String sql = "update bil set mærke = ?, model = ?, brandstof = ?, odometer = ?, bilStatus = ? where bil.stelnummer = ?;";
        jdbcTemplate.update(sql, car.getMærke(), car.getModel(), car.getBrandstof(), car.getOdometer(), car.getBilStatus(), car.getStelnummer());
    }
}
