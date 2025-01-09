package com.example.tax.taxservice.controller;

import com.example.tax.taxservice.model.TaxType;
import com.example.tax.taxservice.service.TaxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class TaxTypeController {
    private final TaxTypeService taxTypeService;
    @Autowired
    public TaxTypeController(TaxTypeService taxTypeService) {
        this.taxTypeService = taxTypeService;
    }

    private final String REDIRECT_LIST = "redirect:/tax-types";
    @GetMapping("/tax-types")
    public String findAll(Model model) {
        List<TaxType> taxTypes = taxTypeService.findAll();
        model.addAttribute("taxTypes", taxTypes);
        return "tax-type-list";
    }

    @GetMapping("/tax-type-create")
    public String createTaxTypeForm(TaxType taxType) {
        return "tax-type-create";
    }

    @PostMapping("/tax-type-create")
    public String createTaxType(TaxType taxType) {
        taxTypeService.saveTaxType(taxType);
        return REDIRECT_LIST;
    }

    @GetMapping("/tax-type-delete/{id}")
    public String deleteTaxType(@PathVariable("id") Long id) {
        taxTypeService.deleteTaxTypeById(id);
        return REDIRECT_LIST;
    }

    @GetMapping("/tax-type-update/{id}")
    public String taxTypeUpdateForm(@PathVariable("id") Long id, Model model) {
        TaxType taxType = taxTypeService.findById(id);
        model.addAttribute("taxType", taxType);
        return "tax-type-update";
    }

    @PostMapping("/tax-type-update")
    public String taxTypeUpdate(TaxType taxType) {
        taxTypeService.saveTaxType(taxType);
        return REDIRECT_LIST;
    }

}
