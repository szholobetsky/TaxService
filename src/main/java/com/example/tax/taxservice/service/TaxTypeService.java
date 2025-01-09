package com.example.tax.taxservice.service;

import com.example.tax.taxservice.model.TaxType;
import com.example.tax.taxservice.repository.TaxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxTypeService {

    private final TaxTypeRepository taxTypeRepository;
    @Autowired
    public TaxTypeService(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    public  TaxType findById(Long id) {
        return taxTypeRepository.findById(id).orElse(null);
    }

    public TaxType findByCode(String code) { return taxTypeRepository.findByCode(code).orElse(null); }
    public List<TaxType> findAll() {
        return taxTypeRepository.findAllByOrderByNameAsc();
    }

    public void saveTaxType(TaxType taxType) {
        taxTypeRepository.save(taxType);
    }

    public void deleteTaxTypeById(Long id) {
        taxTypeRepository.deleteById(id);
    }
}
