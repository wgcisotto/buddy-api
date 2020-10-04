package com.wgcisotto.buddy.google.sheets.model.enums;


import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum Currency {

    BRL("BRL"),
    EUR("EUR");

    private String value;

    public static Currency fromValue(String value) {
        return Stream.of(Currency.values())
                .filter(currency -> currency.value.equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}