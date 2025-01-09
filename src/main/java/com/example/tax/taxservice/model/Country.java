package com.example.tax.taxservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="COUNTRY")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PK_COUNTRY_ID")
    private Long pkCountryId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;


}
