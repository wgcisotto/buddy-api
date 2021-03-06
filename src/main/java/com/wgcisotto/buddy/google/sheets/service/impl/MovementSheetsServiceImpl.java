package com.wgcisotto.buddy.google.sheets.service.impl;

import com.wgcisotto.buddy.google.sheets.model.Movement;
import com.wgcisotto.buddy.google.sheets.pojo.MovementsPOJO;
import com.wgcisotto.buddy.google.sheets.service.MovementSheetService;
import com.wgcisotto.buddy.google.sheets.spliterator.MovementsSpliterator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
//@Transactional //TODO: verificar para que serve esta annotation
public class MovementSheetsServiceImpl implements MovementSheetService {

    private MovementSheetsServiceImpl(){
    }

    @Override
    public Stream<Movement> findAll(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MovementsPOJO> movementsPOJOResponseEntity = restTemplate.getForEntity("https://sheets.googleapis.com/v4/spreadsheets/1HLw4CFkO71G4G2w6F42lLRbonKBB9GVrAPZ-L2Ju6XE/values/buddy-despesas!A2:H?key=AIzaSyB1WUAhOB8Na7cnzORiBOu5nLF5cd9N6tE", MovementsPOJO.class);

        ResponseEntity<MovementsPOJO> movementsFixedPOJOResponseEntity = restTemplate.getForEntity("https://sheets.googleapis.com/v4/spreadsheets/1HLw4CFkO71G4G2w6F42lLRbonKBB9GVrAPZ-L2Ju6XE/values/buddy-eventos-fixos!A2:U?key=AIzaSyB1WUAhOB8Na7cnzORiBOu5nLF5cd9N6tE", MovementsPOJO.class);

        List<List<String>> values = movementsPOJOResponseEntity.getBody().getValues();

        values.addAll(movementsFixedPOJOResponseEntity.getBody().getValues());

        return StreamSupport.stream(new MovementsSpliterator(values.spliterator()), false);
    }

}