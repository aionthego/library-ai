package com.ai.onthego.librabry.catalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SensitiveInfo {

    @Value("${grok.api.key}")
    private String grokKey;

    public String getGrokKey(){
        return grokKey;
    }
}
