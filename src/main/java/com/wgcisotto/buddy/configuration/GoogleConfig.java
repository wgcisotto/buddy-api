package com.wgcisotto.buddy.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google")
public class GoogleConfig {

    private String url;
    private String sheetsApi;
    private GoogleAccountConfig account;
}
