package com.wgcisotto.buddy.google.sheets.model;

import com.wgcisotto.buddy.google.sheets.model.enums.Currency;
import com.wgcisotto.buddy.google.sheets.model.enums.Frequency;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
public class Movement {

    private LocalDate date;

    private MovementType type;

    private String account;

    private String title;

    private String description;

    private String category;

    private Double amount;

    private Currency currency;

    private boolean fixedAmount;

    private boolean recurrent;

    private Optional<Integer> recurrenceTimes;

    private Frequency frequency;

    private boolean disabled;

}