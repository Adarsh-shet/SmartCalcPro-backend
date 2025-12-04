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
        Period period = Period.between(birth, LocalDate.now());
        return period.getYears() + " years, " + period.getMonths() + " months, " + period.getDays() + " days";
    }

    @GetMapping("/experience")
    public String calculateExperience(@RequestParam String startDate) {
        LocalDate start = LocalDate.parse(startDate);
        Period period = Period.between(start, LocalDate.now());
        return period.getYears() + " years, " + period.getMonths() + " months";
    }

    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) { return a + b; }

    @GetMapping("/sub")
    public double subtract(@RequestParam double a, @RequestParam double b) { return a - b; }

    @GetMapping("/mul")
    public double multiply(@RequestParam double a, @RequestParam double b) { return a * b; }

    @GetMapping("/div")
    public double divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) return 0;
        return a / b;
    }
}