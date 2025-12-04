package com.smartcalcpro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calc")
@CrossOrigin(origins = "*")
public class CalculatorController {

    // returns structured JSON { years: X, months: Y, days: Z, formatted: "X years Y months Z days" }
    @GetMapping("/age")
    public ResponseEntity<Map<String, Object>> calculateAge(@RequestParam String dob) {
        LocalDate birth = LocalDate.parse(dob);
        Period p = Period.between(birth, LocalDate.now());
        Map<String, Object> resp = new HashMap<>();
        resp.put("years", p.getYears());
        resp.put("months", p.getMonths());
        resp.put("days", p.getDays());
        resp.put("formatted", String.format("%d years %d months %d days", p.getYears(), p.getMonths(), p.getDays()));
        return ResponseEntity.ok(resp);
    }

    // experience between startDate and endDate (if endDate not provided -> now)
    @GetMapping("/experience")
    public ResponseEntity<Map<String, Object>> calculateExperience(@RequestParam String startDate,
                                                                   @RequestParam(required = false) String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = (endDate == null || endDate.isEmpty()) ? LocalDate.now() : LocalDate.parse(endDate);
        if (end.isBefore(start)) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "endDate must be after startDate");
            return ResponseEntity.badRequest().body(error);
        }
        Period p = Period.between(start, end);
        Map<String, Object> resp = new HashMap<>();
        resp.put("years", p.getYears());
        resp.put("months", p.getMonths());
        resp.put("days", p.getDays());
        resp.put("formatted", String.format("%d years %d months %d days", p.getYears(), p.getMonths(), p.getDays()));
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/add")
    public ResponseEntity<Double> add(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(a + b);
    }

    @GetMapping("/sub")
    public ResponseEntity<Double> subtract(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(a - b);
    }

    @GetMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(a * b);
    }

    @GetMapping("/div")
    public ResponseEntity<Double> divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) return ResponseEntity.ok(0.0);
        return ResponseEntity.ok(a / b);
    }

    // EMI endpoint: principal, annual rate (percent), months
    @GetMapping("/emi")
    public ResponseEntity<Map<String, Object>> calculateEMI(@RequestParam double principal,
                                                            @RequestParam double rate,
                                                            @RequestParam int months) {
        Map<String, Object> resp = new HashMap<>();
        double monthlyRate = rate / 12.0 / 100.0;
        double emi;
        if (monthlyRate == 0) {
            emi = principal / months;
        } else {
            double pow = Math.pow(1 + monthlyRate, months);
            emi = (principal * monthlyRate * pow) / (pow - 1);
        }
        double totalAmount = emi * months;
        double totalInterest = totalAmount - principal;
        resp.put("emi", Math.round(emi * 100.0) / 100.0);
        resp.put("totalAmount", Math.round(totalAmount * 100.0) / 100.0);
        resp.put("totalInterest", Math.round(totalInterest * 100.0) / 100.0);
        return ResponseEntity.ok(resp);
    }
}
