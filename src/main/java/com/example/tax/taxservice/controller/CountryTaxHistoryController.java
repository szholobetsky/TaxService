package com.example.tax.taxservice.controller;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.model.CountryTaxHistory;
import com.example.tax.taxservice.model.TaxType;
import com.example.tax.taxservice.service.CountryService;
import com.example.tax.taxservice.service.CountryTaxHistoryService;
import com.example.tax.taxservice.service.TaxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CountryTaxHistoryController {

    private final CountryTaxHistoryService countryTaxHistoryService;

    private final CountryService countryService;
    private final TaxTypeService taxTypeService;
    @Autowired
    public CountryTaxHistoryController(CountryTaxHistoryService countryTaxHistoryService, CountryService countryService, TaxTypeService taxTypeService) {
        this.countryTaxHistoryService = countryTaxHistoryService;
        this.countryService = countryService;
        this.taxTypeService = taxTypeService;
    }

    @GetMapping("/tax-histories")
    public String findAll(Model model) {
        List<CountryTaxHistory> countryTaxHistories = countryTaxHistoryService.findAll();
        model.addAttribute("countryTaxHistories", countryTaxHistories);
        return "tax-history-list";
    }
    @GetMapping("/tax-history-create")
    public String createTaxHistoryForm(CountryTaxHistory countryTaxHistory, Model model) {
        List<Country> countries = countryService.findAll();
        List<TaxType> taxTypes = taxTypeService.findAll();
        model.addAttribute("taxTypes", taxTypes);
        model.addAttribute("countries", countries);
        return "tax-history-create";
    }

    @PostMapping("/tax-history-create")
    public String createTaxHistory(CountryTaxHistory countryTaxHistory) {
        countryTaxHistoryService.saveTaxHistory(countryTaxHistory);
        return "redirect:/tax-histories";
    }

    @GetMapping("/tax-history-delete/{id}")
    public String deleteTaxType(@PathVariable("id") Long id) {
        countryTaxHistoryService.deleteCountryTaxHistoryById(id);
        return "redirect:/tax-histories";
    }

    @GetMapping("/tax-history-update/{id}")
    public String taxTypeUpdateForm(@PathVariable("id") Long id, Model model) {
        CountryTaxHistory countryTaxHistory = countryTaxHistoryService.findById(id);
        List<Country> countries = countryService.findAll();
        List<TaxType> taxTypes = taxTypeService.findAll();
        model.addAttribute("taxTypes", taxTypes);
        model.addAttribute("countries", countries);
        model.addAttribute("countryTaxHistory", countryTaxHistory);
        return "tax-history-update";
    }

    @PostMapping("/tax-history-update")
    public String taxTypeUpdate(CountryTaxHistory countryTaxHistory) {
        countryTaxHistoryService.saveTaxHistory(countryTaxHistory);
        return "redirect:/tax-histories";
    }
}
