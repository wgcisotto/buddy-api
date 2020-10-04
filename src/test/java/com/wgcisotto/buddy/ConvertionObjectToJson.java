package com.wgcisotto.buddy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgcisotto.buddy.entity.Movement;
import com.wgcisotto.buddy.google.sheets.model.enums.Currency;
import com.wgcisotto.buddy.google.sheets.model.enums.Frequency;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ConvertionObjectToJson {


    @Test
    public void convert() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
//
//        Movement movement = Movement.builder()
//                .account("activo - ALTERAR") //Must be an object
////                .amount(100D)
//                .category("alimentacao - ALTERAR") //Must be object
//                .date(LocalDate.now())
//                .fixedAmount(false)
//                .recurrenceTimes(0)
//                .frequency(null)
//                .title("movement")
//                .description("for testing")
//                .type(MovementType.EXPENSE)
//                .currency(Currency.EUR)
//                .recurrent(false)
//                .build();

//        String jsonString = mapper.writeValueAsString(movement);

//        System.out.println(jsonString);
    }

}
