package com.example.tax.taxservice.controller;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class CountryController {
    private final CountryService countryService;

    private final String REDIRECT_LIST = "redirect:/countries";
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public String findAll(Model model) {
        List<Country> countries = countryService.findAll();
        model.addAttribute("countries", countries);
        return "country-list";
    }

    @GetMapping("/country-create")
    public String createCountryForm(Country country) {
        return "country-create";
    }

    @PostMapping("/country-create")
    public String createCountry(Country country) {
        countryService.saveCountry(country);
        return REDIRECT_LIST;
    }

    @GetMapping("/country-delete/{id}")
    public String deleteCountry(@PathVariable("id") Long id) {
        countryService.deleteById(id);
        return REDIRECT_LIST;
    }

    @GetMapping("/country-update/{id}")
    public String countryUpdateForm(@PathVariable("id") Long id, Model model) {
        Country country = countryService.findById(id);
        model.addAttribute("country", country);
        return "country-update";
    }

    @PostMapping("/country-update")
    public String countryUpdate(Country country) {
        countryService.saveCountry(country);
        return REDIRECT_LIST;
    }

}
