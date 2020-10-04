package com.wgcisotto.buddy.google.sheets.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "account")
public class GoogleAccountConfig {

    private String spreadsheetsId;
    private String key;
    private BuddyDataConfig buddy;

}