package com.wgcisotto.buddy.google.sheets.factory;

import com.wgcisotto.buddy.google.sheets.model.AccountSheets;
import com.wgcisotto.buddy.google.sheets.model.enums.Currency;

import java.util.List;

public class AccountFactory {

    public static AccountSheets createAccount(List<String> line) {
        return AccountSheets.builder()
                .accountId(line.get(0))
                .name(line.get(1))
                .amount(Double.parseDouble(line.get(2)))
                .currency(Currency.fromValue(line.get(3)))
                .build();
    }
}