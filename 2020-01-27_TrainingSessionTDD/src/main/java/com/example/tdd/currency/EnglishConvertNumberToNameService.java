package com.example.tdd.currency;

import org.springframework.stereotype.Service;

@Service
public class EnglishConvertNumberToNameService {

    public String convertToString(int i) {
        if (i == 1) {
            return "one";
        }
        return "";
    }

}

