package com.smartcalcpro.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/api/calc")
@CrossOrigin(origins = "*")
public class CalculatorController {

    @GetMapping("/age")
    public String calculateAge(@RequestParam String dob) {
        LocalDate birth = LocalDate.parse(dob);
        Period p = Period.between(birth, LocalDate.now());
        return p.getYears() + " years " + p.getMonths() + " months " + p.getDays() + " days";
    }

    @GetMapping("/experience")
    public String calculateExperience(
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate
    ) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = (endDate == null || endDate.isEmpty()) ? LocalDate.now() : LocalDate.parse(endDate);

        Period p = Period.between(start, end);
        return p.getYears() + " years " + p.getMonths() + " months " + p.getDays() + " days";
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