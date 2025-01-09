package com.example.tax.taxservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Date;

@Entity
@Data
@Table(name="COUNTRY_TAX_HISTORY")
public class CountryTaxHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PK_COUNTRY_TAX_HISTORY_ID")
    private Long pkCountryTaxHistoryId;

    @ManyToOne()
    @JoinColumn(name="FK_COUNTRY_ID")
    private Country country;

    @ManyToOne()
    @JoinColumn(name = "FK_TAX_TYPE_ID")
    private TaxType taxType;

    @Column(name = "ACTUAL_BEFORE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualBefore;

    @Column(name="TAX_RATE")
    private Double taxRate;

}
