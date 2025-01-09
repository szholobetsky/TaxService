package com.example.tax.taxservice.controller;

import com.example.tax.taxservice.model.CountryTaxHistory;
import com.example.tax.taxservice.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tax")
public class TaxRestController {

    private final TaxService taxService;

    @Autowired
    public TaxRestController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping("/adjusted/country/{countryCode}/type/{typeCode}")
    public Double calculateTax(
            @PathVariable String countryCode,
            @PathVariable String typeCode,
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        Double adjustedAmount;
        if (date != null) {
            adjustedAmount = taxService.calculateAdjustedValueToDate(countryCode, typeCode, date, amount);}
        else {
            adjustedAmount = taxService.calculateAdjustedValue(countryCode, typeCode, amount);
        }

        return adjustedAmount;
    }

    @GetMapping("/list/country/{countryCode}/type/{typeCode}")
    public ResponseEntity<List<CountryTaxHistory>> getTaxList(
            @PathVariable String countryCode,
            @PathVariable String typeCode) {
        return ResponseEntity.ok(taxService.getTaxList(countryCode, typeCode));
    }

}
