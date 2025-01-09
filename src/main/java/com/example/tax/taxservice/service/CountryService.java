package com.example.tax.taxservice.service;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country findById(Long countryId) {
        return countryRepository.findById(countryId).orElse(null);
    }

    public Country findByCode(String code) {return countryRepository.findByCode(code).orElse(null);}

    public List<Country> findAll() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    public void saveCountry(Country country) {
        countryRepository.save(country);
    }

    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }


}
