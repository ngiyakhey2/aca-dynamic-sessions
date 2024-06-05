## ACADynamicSessions

This Java application is a Spring Boot application that interacts with the Dynamic Sessions API. It retrieves environment variables, makes a POST request to the API, and calls methods from other classes to upload, list, and download files.

## How it works
The application retrieves several environment variables including the region, subscription ID, resource group, session pool name, session ID, and filename. It then uses these variables to construct a URL for the Dynamic Sessions API.

The application makes a POST request to the API with a JSON payload. The payload includes properties for the code input type, execution type, and the code to be executed.

The application then calls methods from the UploadFile, ListFiles, and DownloadFile classes. These methods perform actions such as uploading a file, listing files, and downloading a file.

## Environment Variables
The application uses the following environment variables:

region: The region for the Dynamic Sessions API.
subscriptionId: The subscription ID for the Dynamic Sessions API.
resourceGroup: The resource group for the Dynamic Sessions API.
sessionPoolName: The session pool name for the Dynamic Sessions API.
sessionId: The session ID for the Dynamic Sessions API.
filename: The name of the file to be uploaded.

## Dependencies
The application uses the following dependencies:

Spring Boot
Azure Identity
OkHttp

## Running the application
To run the application, use the following command:

```bash
mvn clean install
mvn spring-boot:run
```

## Note
Ensure that the necessary environment variables are set before running the application.