package com.example.tax.taxservice.service;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.model.CountryTaxHistory;
import com.example.tax.taxservice.model.TaxType;
import com.example.tax.taxservice.repository.CountryTaxHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class CountryTaxHistoryService {
    private final CountryTaxHistoryRepository countryTaxHistoryRepository;

    @Autowired
    public CountryTaxHistoryService(CountryTaxHistoryRepository countryTaxHistoryRepository) {
        this.countryTaxHistoryRepository = countryTaxHistoryRepository;
    }

    public List<CountryTaxHistory> findTaxHistoryByCountryAndTaxType(Country country, TaxType taxType) {
        return countryTaxHistoryRepository.findAllByCountryAndTaxTypeOrderByActualBeforeDesc(country, taxType);
    }

    public CountryTaxHistory findLastTaxFromHistoryByCountryAndTaxType(Country country, TaxType taxType) {
        return countryTaxHistoryRepository.findAllByCountryAndTaxTypeOrderByActualBeforeDesc(country, taxType).stream().findFirst().orElse(null);
    }

    public CountryTaxHistory findLastBeforeDateTaxFromHistoryByCountryAndTaxType(Country country, TaxType taxType, Date actualDate) {
        return countryTaxHistoryRepository.findAllByCountryAndTaxTypeOrderByActualBeforeAsc(country, taxType).stream()

                .filter(it ->
                        actualDate == null ||
                                (it.getActualBefore() != null && actualDate.before(it.getActualBefore())) ||
                                it.getActualBefore() == null
                )
                .sorted(Comparator.comparing((CountryTaxHistory history) -> history.getActualBefore() == null ? 1 : 0)
                        .thenComparing(CountryTaxHistory::getActualBefore))
                .findFirst()
                .orElse(null);
    }

    public void saveTaxHistory(CountryTaxHistory countryTaxHistory) {
        this.countryTaxHistoryRepository.save(countryTaxHistory);
    }

    public void deleteCountryTaxHistoryById(Long id) {
        this.countryTaxHistoryRepository.deleteById(id);
    }

    public List<CountryTaxHistory> findAll() {
        return this.countryTaxHistoryRepository.findAll(orderByCountryTaxTypeAscActualDateDesc());
    }

    public CountryTaxHistory findById(Long id) {
        return this.countryTaxHistoryRepository.findById(id).orElse(null);
    }

    private Sort orderByCountryTaxTypeAscActualDateDesc() {
        return Sort.by(Sort.Direction.ASC, "country")
                .and(Sort.by(Sort.Direction.ASC, "taxType"))
                .and(Sort.by(Sort.Direction.DESC, "actualBefore"));
    }
}
