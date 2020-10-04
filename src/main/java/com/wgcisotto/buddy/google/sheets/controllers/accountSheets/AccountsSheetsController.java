package com.wgcisotto.buddy.google.sheets.controllers.accountSheets;

import com.wgcisotto.buddy.google.sheets.controllers.AccountsAPI;
import com.wgcisotto.buddy.google.sheets.model.AccountSheets;
import com.wgcisotto.buddy.google.sheets.service.AccountSheetsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class AccountsSheetsController implements AccountsAPI {

    private AccountSheetsService accountSheetsService;

    private AccountsSheetsController(AccountSheetsService accountSheetsService){
        this.accountSheetsService = accountSheetsService;
    }

    @Override
    @GetMapping(value = "/accounts-sheets")
    public ResponseEntity<Flux<AccountSheets>> getAllAccounts() {
        return new ResponseEntity<>(Flux.fromStream(accountSheetsService.findAll()), HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/accounts-sheets")
    public ResponseEntity<Mono<AccountSheets>> addAccount(@Valid @RequestBody AccountSheets accountSheets) {
        return new ResponseEntity<>(Mono.just(accountSheetsService.create(accountSheets)), HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    @PutMapping(value = "/accounts-sheets")
    public ResponseEntity<Mono<AccountSheets>> updateAccount() {
        return null;
    }

}