package com.aca.dynamicsessions;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ExecuteCode {

    public static void execute(String url, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Define your JSON payload
        //Note - inline code execution is python

        String json = "{"
        + "\"properties\": {"
        + "\"codeInputType\": \"inline\","
        + "\"executionType\": \"synchronous\","
        + "\"code\": \"print('Hello, world!')\""
        +  "}"
        +  "}";

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Authorization", "Bearer " + token)
            .addHeader("Content-Type", "application/json")
            .build();

        try (Response response = client.newCall(request).execute()) {                     
            System.out.println(response.body().string());  // Print the response body
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }
}