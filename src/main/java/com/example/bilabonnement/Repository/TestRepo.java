package com.example.bilabonnement.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void opretTabel() {
        String sql = "CREATE TABLE Customer (CustomerID INT PRIMARY KEY)";
        jdbcTemplate.update(sql);
    }
}
