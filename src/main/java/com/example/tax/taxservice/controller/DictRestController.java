package com.example.tax.taxservice.controller;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.model.TaxType;
import com.example.tax.taxservice.service.CountryService;
import com.example.tax.taxservice.service.TaxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictRestController
{
    private final CountryService countryService;
    private final TaxTypeService taxTypeService;

    public DictRestController(CountryService countryService, TaxTypeService taxTypeService) {
        this.countryService = countryService;
        this.taxTypeService = taxTypeService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = countryService.findAll();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/tax-types")
    public ResponseEntity<List<TaxType>> getTaxType() {
        List<TaxType> taxTypes = taxTypeService.findAll();
        return ResponseEntity.ok(taxTypes);
    }
}
