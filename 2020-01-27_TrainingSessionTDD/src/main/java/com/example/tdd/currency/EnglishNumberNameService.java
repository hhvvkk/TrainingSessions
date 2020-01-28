package com.example.tdd.currency;

import org.springframework.stereotype.Service;

@Service
public class EnglishNumberNameService {

    public String convertToString(int i) {
        if (i == 1) {
            return "one";
        }
        return "";
    }

}

