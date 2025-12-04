package com.smartcalcpro.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/api/calc")
@CrossOrigin(origins = "*")
public class CalculatorController {

    /** AGE CALCULATOR **/
    @GetMapping("/age")
    public AgeResponse calculateAge(@RequestParam String dob) {
        LocalDate birth = LocalDate.parse(dob);
        LocalDate today = LocalDate.now();

        Period p = Period.between(birth, today);

        return new AgeResponse(p.getYears(), p.getMonths(), p.getDays());
    }

    /** EXPERIENCE CALCULATOR **/
    @GetMapping("/experience")
    public AgeResponse calculateExperience(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        Period p = Period.between(start, end);

        return new AgeResponse(p.getYears(), p.getMonths(), p.getDays());
    }

    /** GENERAL OPERATIONS **/
    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) {
        return a + b;
    }

    @GetMapping("/sub")
    public double subtract(@RequestParam double a, @RequestParam double b) {
        return a - b;
    }

    @GetMapping("/mul")
    public double multiply(@RequestParam double a, @RequestParam double b) {
        return a * b;
    }

    @GetMapping("/div")
    public double divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) return 0;
        return a / b;
    }

    /** EMI CALCULATOR **/
    @GetMapping("/emi")
    public double calculateEmi(
            @RequestParam double principal,
            @RequestParam double rate,
            @RequestParam double months) {

        double monthlyRate = rate / (12 * 100);
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
    }
}

/** COMMON RESPONSE FOR AGE + EXPERIENCE **/
class AgeResponse {
    public int years;
    public int months;
    public int days;

    public AgeResponse(int years, int months, int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }
}
