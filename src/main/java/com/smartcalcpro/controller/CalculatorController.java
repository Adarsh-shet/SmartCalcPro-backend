package com.smartcalcpro.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/api/calc")
@CrossOrigin(origins = "*")
public class CalculatorController {

    @GetMapping("/age")
    public int calculateAge(@RequestParam String dob) {
        LocalDate birth = LocalDate.parse(dob);
        return Period.between(birth, LocalDate.now()).getYears();
    }

    @GetMapping("/experience")
    public int calculateExperience(@RequestParam String startDate) {
        LocalDate start = LocalDate.parse(startDate);
        return Period.between(start, LocalDate.now()).getYears();
    }

    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/sub")
    public int subtract(@RequestParam int a, @RequestParam int b) {
        return a - b;
    }

    @GetMapping("/mul")
    public int multiply(@RequestParam int a, @RequestParam int b) {
        return a * b;
    }

    @GetMapping("/div")
    public double divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) return 0;
        return a / b;
    }
}
