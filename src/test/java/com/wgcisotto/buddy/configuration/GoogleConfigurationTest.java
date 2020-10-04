package com.wgcisotto.buddy.configuration;

import com.wgcisotto.buddy.google.sheets.configuration.GoogleConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = GoogleConfig.class)
public class GoogleConfigurationTest {

    @Autowired
    private GoogleConfig googleConfig;

//    @Test
    public void testGoogleConfig(){
        assertEquals("https://sheets.googleapis.com", googleConfig.getUrl());
        assertEquals("v4/spreadsheets", googleConfig.getSheetsApi());
        assertEquals("1HLw4CFkO71G4G2w6F42lLRbonKBB9GVrAPZ-L2Ju6XE", googleConfig.getAccount().getSpreadsheetsId());
        assertEquals("/values/buddy-accounts!A2:D", googleConfig.getAccount().getBuddy().getAccounts());
        assertEquals("/values/buddy-despesas!A2:H", googleConfig.getAccount().getBuddy().getMovementsVariables());
        assertEquals("/values/buddy-eventos-fixos!A2:U", googleConfig.getAccount().getBuddy().getMovementsFixed());

    }

}