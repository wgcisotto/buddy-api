package com.wgcisotto.buddy.google.sheets.controllers;

import com.wgcisotto.buddy.google.sheets.model.AccountSheets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api("movements")
public interface AccountsAPI {

//    TODO: adicionar annotations swagger-ui.html
    ResponseEntity<Flux<AccountSheets>> getAllAccounts();

    ResponseEntity<Mono<AccountSheets>> addAccount(@ApiParam(value = "Account to be added", required = true) AccountSheets accountSheets);

    ResponseEntity<Mono<AccountSheets>> updateAccount();

}