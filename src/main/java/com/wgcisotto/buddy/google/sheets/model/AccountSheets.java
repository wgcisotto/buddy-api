package com.wgcisotto.buddy.google.sheets.model;

import com.wgcisotto.buddy.google.sheets.model.enums.Currency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountSheets {

    private String accountId;

    private String name;

    private Double amount;

    private Currency currency;

}