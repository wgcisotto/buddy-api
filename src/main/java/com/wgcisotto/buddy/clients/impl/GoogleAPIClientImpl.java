package com.wgcisotto.buddy.clients.impl;

import com.wgcisotto.buddy.clients.GoogleAPIClient;
import com.wgcisotto.buddy.configuration.GoogleConfig;
import com.wgcisotto.buddy.model.Movement;
import com.wgcisotto.buddy.pojo.MovementsPOJO;
import com.wgcisotto.buddy.spliterator.MovementsSpliterator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class GoogleAPIClientImpl implements GoogleAPIClient {

    private RestTemplate restTemplate;
    private GoogleConfig googleConfig;

    private GoogleAPIClientImpl(RestTemplate restTemplate, GoogleConfig googleConfig){
        this.restTemplate = restTemplate;
        this.googleConfig = googleConfig;
    }

    @Override
    public Stream<Movement> getAllMovements() {
        ResponseEntity<MovementsPOJO> movementsPOJOResponseEntity = restTemplate.getForEntity("https://sheets.googleapis.com/v4/spreadsheets/1HLw4CFkO71G4G2w6F42lLRbonKBB9GVrAPZ-L2Ju6XE/values/buddy-despesas!A2:H?key=AIzaSyB1WUAhOB8Na7cnzORiBOu5nLF5cd9N6tE", MovementsPOJO.class);
        ResponseEntity<MovementsPOJO> movementsFixedPOJOResponseEntity = restTemplate.getForEntity("https://sheets.googleapis.com/v4/spreadsheets/1HLw4CFkO71G4G2w6F42lLRbonKBB9GVrAPZ-L2Ju6XE/values/buddy-eventos-fixos!A2:M?key=AIzaSyB1WUAhOB8Na7cnzORiBOu5nLF5cd9N6tE", MovementsPOJO.class);

        List<List<String>> values = movementsPOJOResponseEntity.getBody().getValues();

        values.addAll(movementsFixedPOJOResponseEntity.getBody().getValues());

        return StreamSupport.stream(new MovementsSpliterator(values.spliterator()), false);
    }

    @Override
    public Stream<Movement> getAllFixedMovements() {
        StringBuilder urlToCall = new StringBuilder(googleConfig.getUrl());
        urlToCall.append("/").append(googleConfig.getSheetsApi())
                .append("/").append(googleConfig.getAccount().getSpreadsheetsId())
                .append("/").append(googleConfig.getAccount().getBuddy().getMovementsFixed())
                .append("?key=").append(googleConfig.getAccount().getKey());

        ResponseEntity<MovementsPOJO> movementsFixedPOJOResponseEntity = restTemplate.getForEntity(urlToCall.toString(), MovementsPOJO.class);
        return StreamSupport.stream(new MovementsSpliterator(movementsFixedPOJOResponseEntity.getBody().getValues().spliterator()), false);
    }

    @Override
    public Stream<Movement> getAllVariableMovements() {
        StringBuilder urlToCall = new StringBuilder(googleConfig.getUrl());
        urlToCall.append("/").append(googleConfig.getSheetsApi())
                .append("/").append(googleConfig.getAccount().getSpreadsheetsId())
                .append("/").append(googleConfig.getAccount().getBuddy().getMovementsVariables())
                .append("?key=").append(googleConfig.getAccount().getKey());

        ResponseEntity<MovementsPOJO> movementsFixedPOJOResponseEntity = restTemplate.getForEntity(urlToCall.toString(), MovementsPOJO.class);
        return StreamSupport.stream(new MovementsSpliterator(movementsFixedPOJOResponseEntity.getBody().getValues().spliterator()), false);
    }
}