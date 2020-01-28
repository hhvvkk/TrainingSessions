package com.example.tdd.currency;

import com.example.tdd.currency.entity.Currency;
import com.example.tdd.currency.entity.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String fullNameForNumber = englishConvertNumberToNameService.convertToString(value);

        Currency currency = new Currency();

        currency.setEnglishNumberName(fullNameForNumber);
        currency.setType(type);
        currency.setValue(value);

        return currencyRepository.save(currency);
    }

}
