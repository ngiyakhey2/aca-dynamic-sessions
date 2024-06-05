package com.aca.dynamicsessions;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenRequestContext;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;


@SpringBootApplication
public class ACADynamicSessions {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ACADynamicSessions.class, args);
        ACADynamicSessions app = context.getBean(ACADynamicSessions.class);
        try {
            app.run(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(String[] args) throws IOException {
        // Get values from the application.properties file
        String region = env.getProperty("region");
        String subscriptionId = env.getProperty("subscriptionId");
        String resourceGroup = env.getProperty("resourceGroup");
        String sessionPoolName = env.getProperty("sessionPoolName");
        String sessionId = env.getProperty("sessionId");
        String filename = env.getProperty("filename");        
        
        // Get the token using DefaultAzureCredential
        String token = getToken();  
                
        // Call the method from executeCode class
        String eurl = String.format("https://%s.dynamicsessions.io/subscriptions/%s/resourceGroups/%s/sessionPools/%s/code/execute?api-version=2024-02-02-preview&identifier=%s", 
        region, subscriptionId, resourceGroup, sessionPoolName, sessionId);
        System.out.println("\nRunning ExecuteCode with URL: " + eurl + "\n");
        ExecuteCode.execute(eurl, token);

        // Call the method from UploadFile class
        String uurl = String.format("https://%s.dynamicsessions.io/subscriptions/%s/resourceGroups/%s/sessionPools/%s/files/upload?api-version=2024-02-02-preview&identifier=%s", 
        region, subscriptionId, resourceGroup, sessionPoolName, sessionId);
        System.out.println("\nUploading filename: " + filename + " \nwith URL: " + uurl + "\n");
        UploadFile.upload(uurl, token, filename);
    
        // Call the method from ListFiles class
        String lurl = String.format("https://%s.dynamicsessions.io/subscriptions/%s/resourceGroups/%s/sessionPools/%s/files?api-version=2024-02-02-preview&identifier=%s", 
        region, subscriptionId, resourceGroup, sessionPoolName, sessionId);
        System.out.println("\nRunning ListFiles with URL: " + lurl + "\n");
        String gfilename = ListFiles.list(lurl, token);
        System.out.println("\nFiles found: \n" + gfilename);

        // Call the method from DownloadFile class
        String durl = String.format("https://%s.dynamicsessions.io/subscriptions/%s/resourceGroups/%s/sessionPools/%s/files/content/%s/?api-version=2024-02-02-preview&identifier=%s", 
        region, subscriptionId, resourceGroup, sessionPoolName, gfilename, sessionId);
        System.out.println("\nDownloading file: " + gfilename + " \n with URL: " + durl + "\n"); 
        DownloadFile.download(durl, token);
        
    }

    private static String getToken() {
        DefaultAzureCredential credential = new DefaultAzureCredentialBuilder().build();
        TokenRequestContext requestContext = new TokenRequestContext().addScopes("https://dynamicsessions.io/.default");
        AccessToken token = credential.getToken(requestContext).block();
        return token.getToken();
    }

}
