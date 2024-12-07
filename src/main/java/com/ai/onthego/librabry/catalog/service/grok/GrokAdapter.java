package com.ai.onthego.librabry.catalog.service.grok;

import com.ai.onthego.librabry.catalog.SensitiveInfo;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GrokAdapter {
    private final SensitiveInfo sensitiveInfo;
    public static final MediaType JSON = MediaType.get("application/json");
    public static final String GROK_API_ENDPOINT = "https://api.x.ai/v1/chat/completions";
    public static final String AUTHORIZATION ="Authorization";
    public static final String BEARER = "Bearer ";
    public static final String PAYLOAD1 = "{" +
            "    \"messages\": [" +
            "        {" +
            "            \"role\": \"system\"," +
            "            \"content\": \"Mitchell Park Library\"" +
            "        }, {" +
            "            \"role\": \"user\"," +
            "            \"content\":\"";
    public static final String PAYLOAD2 = "\" }]," +
            "    \"model\": \"grok-beta\"," +
            "    \"stream\": false," +
            "    \"temperature\": 0" +
            "}";

    public GrokAdapter(SensitiveInfo sensitiveInfo) {
        this.sensitiveInfo = sensitiveInfo;
    }


    public String fetch(String prompt) throws IOException {
        StringBuilder sb = new StringBuilder();
        OkHttpClient client = new OkHttpClient();
        String json = PAYLOAD1+prompt+PAYLOAD2;
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(GROK_API_ENDPOINT)
                .addHeader(AUTHORIZATION, BEARER + sensitiveInfo.getGrokKey())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String grokresponse = response.body().string();
            JSONObject jsonObject = new JSONObject(grokresponse);
            JSONArray jsonArray = jsonObject.getJSONArray("choices");
            JSONObject choice = jsonArray.getJSONObject(0);
            JSONObject message =    choice.getJSONObject("message");
            String content = message.getString("content");
            content = content.replace("\n", "<br>");
            content = content.replace("**", "");
            sb.append(content);
        }
        return sb.toString();
    }
}
