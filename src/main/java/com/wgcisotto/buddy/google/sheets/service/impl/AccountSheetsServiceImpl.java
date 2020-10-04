package com.wgcisotto.buddy.google.sheets.service.impl;

import com.wgcisotto.buddy.google.sheets.configuration.GoogleConfig;
import com.wgcisotto.buddy.google.sheets.model.AccountSheets;
import com.wgcisotto.buddy.google.sheets.pojo.AccountsPOJO;
import com.wgcisotto.buddy.google.sheets.service.AccountSheetsService;
import com.wgcisotto.buddy.google.sheets.spliterator.AccountSpliterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
//@Transacional //TODO: verificar para que serve esta annotation
public class AccountSheetsServiceImpl implements AccountSheetsService {

    private RestTemplate restTemplate;

    private GoogleConfig googleConfig;

    private AccountSheetsServiceImpl(RestTemplate restTemplate, GoogleConfig googleConfig){
        this.restTemplate = restTemplate;
        this.googleConfig = googleConfig;
    }

    @Override
    public Stream<AccountSheets> findAll(){

        String url = googleConfig.getUrl() + "/" +
                googleConfig.getSheetsApi() + "/" +
                googleConfig.getAccount().getSpreadsheetsId() +
                googleConfig.getAccount().getBuddy().getAccounts() + "?key=" +
                googleConfig.getAccount().getKey();

        log.info("Calling googles's service URL={}", url);

        ResponseEntity<AccountsPOJO> movementsPOJOResponseEntity = restTemplate.getForEntity(url, AccountsPOJO.class);

        return StreamSupport.stream(new AccountSpliterator(movementsPOJOResponseEntity.getBody().getValues().spliterator()), false);
    }

    @Override
    public AccountSheets create(AccountSheets accountSheets) {
        return AccountSheets.builder().build();
    }

}