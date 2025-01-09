package com.example.tax.taxservice.repository;

import com.example.tax.taxservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByOrderByNameAsc();

    Optional<Country> findByCode(String code);
}
