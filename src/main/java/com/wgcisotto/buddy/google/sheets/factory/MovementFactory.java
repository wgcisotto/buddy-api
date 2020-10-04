package com.wgcisotto.buddy.google.sheets.factory;


import com.wgcisotto.buddy.google.sheets.model.Movement;
import com.wgcisotto.buddy.google.sheets.model.enums.Currency;
import com.wgcisotto.buddy.google.sheets.model.enums.Frequency;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class MovementFactory {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //TODO: create a better way to create
    public static Movement createMovement(List<String> line) {
        Movement movement = Movement.builder()
                .date(LocalDate.parse(line.get(0), formatter))
                .type(MovementType.fromName(line.get(1)))
                .account(line.get(2))
                .title(line.get(3))
                .category(line.get(4))
                .description(line.get(5))
                .amount(Double.parseDouble(line.get(6)))
                .currency(Currency.fromValue(line.get(7)))
                .build();

        if(line.size()>8){
            movement.setFixedAmount(Boolean.parseBoolean(line.get(8)));
            movement.setRecurrent(Boolean.valueOf(line.get(9)));
            movement.setFrequency(Frequency.fromName(line.get(10)));
            movement.setRecurrenceTimes(line.get(11).equals("") ? Optional.empty() : Optional.of(Integer.valueOf(line.get(11))));
            movement.setDisabled(Boolean.valueOf(line.get(12)));
//            movement.setPayments(movement.isFixedAmount() ? null : createPayments(line));
        }

        return movement;
    }

//    private static List<Payment> createPayments(List<String> values){
//        List<Payment> payments = new ArrayList<>();
//
//        IntStream.iterate(13, i -> i = i + 2)
//                    .limit((values.size() -13)/2)
//                    .forEach(index->{
//                        Payment payment = Payment.builder()
//                                .date(LocalDate.parse(values.get(index), formatter))
//                                .amount(Double.valueOf(values.get(index + 1)))
//                                .build();
//
//                        payments.add(payment);
//                }
//        );
//        return payments;
//    }

}
