package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.LeasingPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LeasingPriceRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;



    public void addPrice(LeasingPrice leasingPrice) {
        String sql = "insert into leasingprices( stelnummer, leasingPrice)" +
                "values(?, ?)";
        jdbcTemplate.update(sql, leasingPrice.getStelnummer(), leasingPrice.getLeasingPrice());

    }
    public void updateLeasingPriceByStelnummer(String stelnummer, LeasingPrice leasingPrice){
        String sql = "update leasingPrices set leasingPrice = ? where leasingPrices.stelnummer = ?;";
        jdbcTemplate.update(sql, leasingPrice.getLeasingPrice(), stelnummer);
    }
    public LeasingPrice fetchLeasingPriceByStelnummer(String stelnummer) {
        String sql = "SELECT * FROM leasingPrices where leasingPrices.stelnummer = ?";
        RowMapper<LeasingPrice> rowMapper = new BeanPropertyRowMapper<>(LeasingPrice.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, stelnummer);
    }

}
