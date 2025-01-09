package com.example.tax.taxservice.repository;

import com.example.tax.taxservice.model.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {
    List<TaxType> findAllByOrderByNameAsc();

    Optional<TaxType> findByCode(String code);
}
