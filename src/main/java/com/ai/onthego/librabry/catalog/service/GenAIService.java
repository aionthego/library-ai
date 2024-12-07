package com.ai.onthego.librabry.catalog.service;

import com.ai.onthego.librabry.catalog.service.grok.GrokAdapter;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GenAIService {
    private final GrokAdapter grokAdapter;

    public GenAIService(GrokAdapter grokAdapter) {
        this.grokAdapter = grokAdapter;
    }

    public String fetch(String message) throws RuntimeException {
        String response = null;
        try {
            response = grokAdapter.fetch(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(HTMLBuilder.TOP_HTML);
        sb.append("<p style=\"padding: 5px 15px 5px 15px\">"+response+"</p><br><br>");
        sb.append(HTMLBuilder.BOTTOM_HTML);
        return sb.toString();
    }
}
