package com.example.tax.taxservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="TAX_TYPE")
@Data
public class TaxType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_TAX_TYPE_ID")
    private Long pkTaxTypeId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;
}
