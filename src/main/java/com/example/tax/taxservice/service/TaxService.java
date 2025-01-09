package com.example.tax.taxservice.service;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.model.CountryTaxHistory;
import com.example.tax.taxservice.model.TaxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TaxService {
    private final CountryTaxHistoryService countryTaxHistoryService;

    private final CountryService countryService;

    private final TaxTypeService taxTypeService;

    @Autowired
    public TaxService(CountryTaxHistoryService countryTaxHistoryService, CountryService countryService, TaxTypeService taxTypeService) {
        this.countryTaxHistoryService = countryTaxHistoryService;
        this.countryService = countryService;
        this.taxTypeService = taxTypeService;
    }

    public Double calculateAdjustedValue(String countryCode, String taxTypeCode, Double amount) {
        Country country = countryService.findByCode(countryCode);
        TaxType taxType = taxTypeService.findByCode(taxTypeCode);
        Double tax = countryTaxHistoryService.findLastTaxFromHistoryByCountryAndTaxType(country, taxType).getTaxRate();
        return amount*(100 + tax)/100;
    }

    public Double calculateAdjustedValueToDate(String countryCode, String taxTypeCode, Date date, Double amount) {
        Country country = countryService.findByCode(countryCode);
        TaxType taxType = taxTypeService.findByCode(taxTypeCode);
        Double tax = countryTaxHistoryService.findLastBeforeDateTaxFromHistoryByCountryAndTaxType(country, taxType, date).getTaxRate();
        return amount*(100 + tax)/100;
    }

    public List<CountryTaxHistory> getTaxList(String countryCode, String taxTypeCode) {
        Country country = countryService.findByCode(countryCode);
        TaxType taxType = taxTypeService.findByCode(taxTypeCode);
        return countryTaxHistoryService.findTaxHistoryByCountryAndTaxType(country, taxType);
    }
}
