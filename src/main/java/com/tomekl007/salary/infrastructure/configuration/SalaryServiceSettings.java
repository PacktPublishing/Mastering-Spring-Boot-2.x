package com.tomekl007.salary.infrastructure.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "salary-config")
public class SalaryServiceSettings {

    private List<String> supportedAccounts;

    public List<String> getSupportedAccounts() {
        return supportedAccounts;
    }

    public void setSupportedAccounts(List<String> supportedAccounts) {
        this.supportedAccounts = supportedAccounts;
    }
}
