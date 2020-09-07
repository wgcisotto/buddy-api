package com.wgcisotto.buddy.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Payment {

    private LocalDate date;

    private Double amount;

}
