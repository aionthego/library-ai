package com.ai.onthego.librabry.catalog.service.deepseek;

import com.ai.onthego.librabry.catalog.SensitiveInfo;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class DeekSeekAdapter {
    private final SensitiveInfo sensitiveInfo;
    public static final MediaType JSON = MediaType.get("application/json");
    public static final String GROK_API_ENDPOINT = "https://api.deepseek.com/chat/completions";
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
            "    \"model\": \"deepseek-chat\"," +
            "    \"stream\": false," +
            "    \"temperature\": 0" +
            "}";

    public DeekSeekAdapter(SensitiveInfo sensitiveInfo) {
        this.sensitiveInfo = sensitiveInfo;
    }


    public String fetch(String prompt) throws IOException {
        StringBuilder sb = new StringBuilder();
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
        String json = PAYLOAD1+prompt+PAYLOAD2;
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(GROK_API_ENDPOINT)
                .addHeader(AUTHORIZATION, BEARER + sensitiveInfo.getDeepSeekKey())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.code());
            String apiresponse = response.body().string();
            System.out.println(apiresponse);
            if(response.code() == 200){
                JSONObject jsonObject = new JSONObject(apiresponse);
                JSONArray jsonArray = jsonObject.getJSONArray("choices");
                JSONObject choice = jsonArray.getJSONObject(0);
                JSONObject message =    choice.getJSONObject("message");
                String content = message.getString("content");
                content = content.replace("\n", "<br>");
                content = content.replace("**", "");
                sb.append(content);
            } else {
                sb.append(apiresponse);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
