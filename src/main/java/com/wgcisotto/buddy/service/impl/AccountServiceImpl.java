package com.wgcisotto.buddy.service.impl;

import com.wgcisotto.buddy.configuration.GoogleConfig;
import com.wgcisotto.buddy.model.Account;
import com.wgcisotto.buddy.pojo.AccountsPOJO;
import com.wgcisotto.buddy.service.AccountService;
import com.wgcisotto.buddy.spliterator.AccountSpliterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
//@Transacional //TODO: verificar para que serve esta annotation
public class AccountServiceImpl implements AccountService {

    private RestTemplate restTemplate;

    private GoogleConfig googleConfig;

    private AccountServiceImpl(RestTemplate restTemplate, GoogleConfig googleConfig){
        this.restTemplate = restTemplate;
        this.googleConfig = googleConfig;
    }

    @Override
    public Stream<Account> findAll(){

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
    public Account create(Account account) {
        return Account.builder().build();
    }

}