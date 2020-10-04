package com.wgcisotto.buddy.entity;

import com.wgcisotto.buddy.google.sheets.model.enums.Currency;
import com.wgcisotto.buddy.google.sheets.model.enums.Frequency;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movement")
public class Movement {

    @Id
    private String id;

    private LocalDate date;

    private MovementType type;

    private Account account;

    @TextIndexed
    private String title;

    private String description;

    private Category category;

    private Double amount;

    private Currency currency;

    private boolean fixedAmount;

    private boolean recurrent;

    private int recurrenceTimes;

    private Frequency frequency;

    private boolean delete = false;

}
