package com.example.tdd.currency.mapper;

import org.mapstruct.Mapper;
import com.example.tdd.currency.dto.CurrencyDTO;
import com.example.tdd.currency.entity.Currency;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CurrencyMapper {

    public abstract Currency toEntity(CurrencyDTO dto);

    public abstract CurrencyDTO toDTO(Currency entity);

    public List<CurrencyDTO> toDTOsList(List<Currency> entities) {
        return entities
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
