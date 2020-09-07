package com.wgcisotto.buddy.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "buddy")
public class BuddyDataConfig {

    private String accounts;
    private String movementsVariables;
    private String movementsFixed;

}