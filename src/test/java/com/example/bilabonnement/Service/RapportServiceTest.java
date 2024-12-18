package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Rapport;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RapportServiceTest {

    RapportService rapportService;
    @Test
    void getAntalRapporter() {
        rapportService = new RapportService();
        List<Rapport> rapporter = Arrays.asList(
                new Rapport(),
                new Rapport(),
                new Rapport(),
                new Rapport()
        );
        int total = rapportService.getAntalRapporter(rapporter);
        assertEquals(4, total);
    }
}