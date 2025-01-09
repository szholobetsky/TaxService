package com.example.tax.taxservice.repository;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.model.CountryTaxHistory;
import com.example.tax.taxservice.model.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryTaxHistoryRepository extends JpaRepository<CountryTaxHistory, Long> {

    List<CountryTaxHistory> findAllByCountryAndTaxTypeOrderByActualBeforeDesc(Country country, TaxType taxType);

    List<CountryTaxHistory> findAllByCountryAndTaxTypeOrderByActualBeforeAsc(Country country, TaxType taxType);
}
