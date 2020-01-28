package com.example.tdd.currency.dto;

import com.example.tdd.currency.entity.CurrencyType;
import lombok.Data;

@Data
public class CurrencyDTO {

    private long id;

    private CurrencyType type;

    private int value;

    private String englishNumberName;
}
