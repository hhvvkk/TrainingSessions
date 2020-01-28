package com.example.tdd.currency.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false, length = 80)
    private CurrencyType type;

    @Column(name = "currency_value", nullable = false)
    private int value;

    @Column(name = "english_number_name", nullable =  false, length = 500)
    private String englishNumberName;


}
