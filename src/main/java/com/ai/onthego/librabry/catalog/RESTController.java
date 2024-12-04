package com.ai.onthego.librabry.catalog;

import com.ai.onthego.librabry.catalog.service.GenAIService;
import com.ai.onthego.librabry.catalog.service.HTMLBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

    private final GenAIService genAIService;

    public RESTController(GenAIService genAIService) {
        this.genAIService = genAIService;
    }

    @PostMapping("/ai")
    public ResponseEntity<String> generate(@RequestParam(value = "message") String message) {
        ResponseEntity<String> responseEntity = null;
        if(message != null && message.length() > 3){
            try {
                String response = genAIService.fetch(message);
                responseEntity = new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
            } catch (Exception ex){
                responseEntity = new ResponseEntity<>(HTMLBuilder.getServerException(ex.getMessage()), HttpStatusCode.valueOf(500));
            }
        } else {
            responseEntity = new ResponseEntity<>(HTMLBuilder.getBadRequest(), HttpStatusCode.valueOf(400));
        }
        return responseEntity;
    }

    @GetMapping("/ai")
    public ResponseEntity<String> reset() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HTMLBuilder.getForm(), HttpStatusCode.valueOf(200));
        return responseEntity;
    }
}
