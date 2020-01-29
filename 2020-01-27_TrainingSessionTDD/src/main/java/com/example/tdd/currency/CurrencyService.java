package com.example.tdd.currency;

import com.example.tdd.currency.entity.Currency;
import com.example.tdd.currency.entity.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final EnglishConvertNumberToNameService englishConvertNumberToNameService;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository,
                           EnglishConvertNumberToNameService englishConvertNumberToNameService) {
        this.currencyRepository = currencyRepository;
        this.englishConvertNumberToNameService = englishConvertNumberToNameService;
    }

    public List<Currency> findAll(){
        return currencyRepository.findAll();
    }

    public Currency save(CurrencyType type, int value) {
        if (type == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad type provided - type is null");
        }

        String fullNameForNumber = englishConvertNumberToNameService.convertToString(value);

        Currency currency = new Currency();

        currency.setEnglishNumberName(fullNameForNumber);
        currency.setType(type);
        currency.setValue(value);

        currency = currencyRepository.save(currency);
        return currency;
    }

}
