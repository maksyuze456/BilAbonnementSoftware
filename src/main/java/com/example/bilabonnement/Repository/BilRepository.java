package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.jdbc.core.JdbcOperationsExtensionsKt.query;

@Repository
public class BilRepository {
    @Autowired
    JdbcTemplate template;

    public List<Bil> fetchAll(){
        String sql="SELECT * FROM bil";
        RowMapper <Bil> rowMapper= new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql,rowMapper);
    }
    public void addBil(Bil bil){

        String sql = "INSERT INTO bil(stelnummer,mærke,model,brandstof,odometer,status)VALUES(?,?,?,?,?,?)";
        template.update(sql,bil.getStelnummer(),bil.getMærke(),bil.getModel(),bil.getBrandstof(),bil.getOdometer(),bil.getStatus().toString());
    }

}
