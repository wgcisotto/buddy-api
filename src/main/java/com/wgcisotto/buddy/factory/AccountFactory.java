package com.wgcisotto.buddy.factory;

import com.wgcisotto.buddy.model.Account;
import com.wgcisotto.buddy.model.enums.Currency;

import java.util.List;

public class AccountFactory {

    public static Account createAccount(List<String> line) {
        return Account.builder()
                .accountId(line.get(0))
                .name(line.get(1))
                .amount(Double.parseDouble(line.get(2)))
                .currency(Currency.fromValue(line.get(3)))
                .build();
    }
}