package com.example.bilabonnement.Service;

import com.example.bilabonnement.Repository.ForretningsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ForretningsServiceTest {

    ForretningsService forretningsService;

    @Test
    public void shouldReturnCorrectCumulativeInt() {
        forretningsService = new ForretningsService();
        double totalSoFar = 0;

        for(int i = 0; i < 3; i++) {
            totalSoFar = forretningsService.addPricesTogether(totalSoFar, 1);
        }
        assertEquals(3, totalSoFar);
    }

}