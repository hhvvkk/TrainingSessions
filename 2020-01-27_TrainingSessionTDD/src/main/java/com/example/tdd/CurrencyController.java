package com.example.tdd;

import com.example.tdd.currency.CurrencyService;
import com.example.tdd.currency.dto.CurrencyDTO;
import com.example.tdd.currency.entity.Currency;
import com.example.tdd.currency.mapper.CurrencyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    private final CurrencyMapper currencyMapper;

    @Autowired
    public CurrencyController(CurrencyService currencyService,
                              CurrencyMapper currencyMapper) {
        this.currencyService = currencyService;
        this.currencyMapper = currencyMapper;
    }

    @GetMapping
    public List<CurrencyDTO> findAllCurrencies() {
        return currencyMapper.toDTOsList(currencyService.findAll());
    }

    @PostMapping
    public CurrencyDTO findAllCurrencies(@RequestBody CurrencyDTO requestDTO) {
        Currency currency = currencyService.save(requestDTO.getType(), requestDTO.getValue());
        return currencyMapper.toDTO(currency);
    }



}
