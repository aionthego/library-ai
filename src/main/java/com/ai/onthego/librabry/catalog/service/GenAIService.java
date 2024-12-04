package com.ai.onthego.librabry.catalog.service;

import org.springframework.stereotype.Service;

@Service
public class GenAIService {
    public String fetch(String message) throws RuntimeException {
        String response = null;
        response = "all is well";
        response = response.replace("*", "");
        response = response.replace("##", "");
        StringBuilder sb = new StringBuilder();
        sb.append(HTMLBuilder.TOP_HTML);
        sb.append(response+"<br><br>");
        sb.append(HTMLBuilder.BOTTOM_HTML);
        return sb.toString();
    }
}
