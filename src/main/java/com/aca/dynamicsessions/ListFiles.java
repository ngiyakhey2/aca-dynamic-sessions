package com.aca.dynamicsessions;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListFiles {

    public static String list(String url, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
            .url(url)
            .get()
            .addHeader("Authorization", "Bearer " + token)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Parse the response body as JSON
            JSONObject json = new JSONObject(response.body().string());

            // Create a StringBuilder to store the filenames
            StringBuilder filenames = new StringBuilder();

            // Get the "value" array from the JSON object
            JSONArray valueArray = json.getJSONArray("value");

            // Loop through each object in the "value" array
            for (int i = 0; i < valueArray.length(); i++) {
                // Get the current object
                JSONObject currentObject = valueArray.getJSONObject(i);

                // Get the "properties" object from the current object
                JSONObject properties = currentObject.getJSONObject("properties");

                // Get the filename from the "properties" object
                String filename = properties.getString("filename");

                // Append the filename to the StringBuilder
                filenames.append(filename);

                // If this is not the last filename, append a comma and a space
                if (i < valueArray.length() - 1) {
                    filenames.append(", ");
                }
            }

            // Return the string representation of the StringBuilder
            return filenames.toString();
        }
    }
}