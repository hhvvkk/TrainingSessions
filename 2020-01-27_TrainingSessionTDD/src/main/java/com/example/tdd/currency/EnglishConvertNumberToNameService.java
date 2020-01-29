package com.example.tdd.currency;

import com.example.tdd.currency.constants.NumNames;
import com.example.tdd.currency.constants.TensNames;
import org.springframework.stereotype.Service;

@Service
public class EnglishConvertNumberToNameService {

    public String convertToString(int i) {
        String sofar = "";
        if (i % 100 < 20) {
            sofar = NumNames.NUM_NAMES[i % 20];
            i /= 100;
        } else {
            //3 4
            sofar = NumNames.NUM_NAMES[i % 10];
            i /= 10;

            sofar = TensNames.TENS_NAMES[i % 10] + " " + sofar;
            i /= 10;
        }

        if (i == 0) {
            return sofar.trim();
        }

        return (NumNames.NUM_NAMES[i] + " hundred " + sofar).trim();
    }

}

