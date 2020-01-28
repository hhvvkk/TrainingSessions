package com.example.tdd.currency;

import org.springframework.stereotype.Service;

@Service
public class EnglishConvertNumberToNameService {

    public String convertToString(int i) {
        return NumNames.NUM_NAMES[i % 20];
    }

}

