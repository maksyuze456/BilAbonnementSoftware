package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
@Autowired
    TestRepo testRepo;
    @GetMapping("/")
        public void test() {
        testRepo.opretTabel();
    }
}
