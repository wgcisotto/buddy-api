package com.wgcisotto.buddy.model;

import com.wgcisotto.buddy.model.enums.Currency;
import com.wgcisotto.buddy.model.enums.Frequency;
import com.wgcisotto.buddy.model.enums.MovementType;
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