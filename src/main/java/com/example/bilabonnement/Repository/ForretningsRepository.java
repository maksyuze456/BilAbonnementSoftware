package com.example.bilabonnement.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ForretningsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> fetchAllLeased() {
        String sql = "SELECT bil.stelnummer, bil.mærke, bil.model, bil.bilStatus, leasingPrices.leasingPrice FROM bil\n" +
                "\tinner join leasingprices\n" +
                "\ton leasingprices.leasingCarStelnummer = bil.stelnummer\n" +
                "    where bil.bilStatus = \"Udlejet\";";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        return results;
    }

}
