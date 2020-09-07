package com.wgcisotto.buddy.model;

import com.wgcisotto.buddy.model.enums.Currency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

    private String accountId;

    private String name;

    private Double amount;

    private Currency currency;

}