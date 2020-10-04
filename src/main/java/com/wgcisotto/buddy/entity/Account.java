package com.wgcisotto.buddy.entity;

import com.wgcisotto.buddy.google.sheets.model.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account")
public class Account {

    @Id
    private String id;

    @TextIndexed
    private String name;

    private long amount;

    private Currency currency;

    private boolean delete = false;

}