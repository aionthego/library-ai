package com.ai.onthego.librabry.catalog.service;

import com.ai.onthego.librabry.catalog.service.deepseek.DeekSeekAdapter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class GenAIService {
    public static final String SPACE = " ";
    private final DeekSeekAdapter deekSeekAdapter;
    private static List<String> metrics = new LinkedList<>();

    public GenAIService(DeekSeekAdapter deekSeekAdapter) {
        this.deekSeekAdapter = deekSeekAdapter;
    }

    public String fetch(String message) throws RuntimeException {
        String response = null;
        try {
            metrics.add(new Date() + SPACE + message);
            response = deekSeekAdapter.fetch(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(HTMLBuilder.TOP_HTML);
        sb.append("<p style=\"padding: 5px 15px 5px 15px\">"+response+"</p><br><br>");
        sb.append(HTMLBuilder.BOTTOM_HTML);
        return sb.toString();
    }

    public List<String> getMetrics() {
        return metrics;
    }
}
