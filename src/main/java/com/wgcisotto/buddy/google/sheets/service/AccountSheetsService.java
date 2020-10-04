package com.wgcisotto.buddy.google.sheets.service;

import com.wgcisotto.buddy.google.sheets.model.AccountSheets;

import java.util.stream.Stream;

public interface AccountSheetsService {

    Stream<AccountSheets> findAll();

    AccountSheets create(AccountSheets accountSheets);
}