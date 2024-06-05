package com.aca.dynamicsessions;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UploadFile {

    public static void upload(String url, String token, String filename) throws IOException {

        OkHttpClient client = new OkHttpClient();
            
        File file = new File(filename);
        RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/json"));
        RequestBody body = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", file.getName(), fileBody)
            .build();

        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Authorization", "Bearer " + token)
            .addHeader("Content-Type", "multipart/form-data")
            .build();
    
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        }
    }

}