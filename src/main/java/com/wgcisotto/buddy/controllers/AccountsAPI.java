package com.wgcisotto.buddy.controllers;

import com.wgcisotto.buddy.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api("movements")
public interface AccountsAPI {

//    TODO: adicionar annotations swagger-ui.html
    ResponseEntity<Flux<Account>> getAllAccounts();

    ResponseEntity<Mono<Account>> addAccount(@ApiParam(value = "Account to be added", required = true) Account account);

    ResponseEntity<Mono<Account>> updateAccount();

}