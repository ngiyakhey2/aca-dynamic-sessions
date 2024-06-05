## ACADynamicSessions

This Java application is a Spring Boot application that interacts with the Dynamic Sessions API. It retrieves environment variables, makes a POST request to the API, and calls methods from other classes to upload, list, and download files.

## Prerequisites: 

You will need the following prerequisites:

Azure Subscription: You need an active Azure subscription to create resources like resource groups and session pools.

Java Development Kit (JDK) 17 or newer

Maven: This project uses Maven for dependency management and building. Ensure Maven is installed and configured on your system.

Azure CLI: For setting up the Azure environment as described in the documentation, the Azure Command-Line Interface (Azure CLI) must be installed to execute commands for creating resource groups and session pools.

Git: To clone the repository if the source code is hosted on a Git repository.

An IDE or Text Editor: While not strictly necessary, having an Integrated Development Environment (IDE) like Visual Studio Code, IntelliJ IDEA, or Eclipse can simplify the development and debugging process.

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

### For bash (Linux/macOS)

```bash
export region="YourRegion"
export subscriptionId="YourSubscriptionId"
export resourceGroup="YourResourceGroup"
export sessionPoolName="YourSessionPoolName"
export sessionId="YourSessionId"
export filename="HelloWorld.java"
```
### For cmd (Windows Command Prompt)

```cmd

set region=YourRegion
set subscriptionId=YourSubscriptionId
set resourceGroup=YourResourceGroup
set sessionPoolName=YourSessionPoolName
set sessionId=YourSessionId
set filename="HelloWorld.java"

```

## Setting up the Azure environment

Use these bash commands to create a new Azure resource group, and create a new session pool within that resource group, using the Azure CLI (az command). 

```bash
az group create --name $resourceGroup --location $region

az containerapp sessionpool create \
    --name $sessionPoolName \
    --resource-group $resourceGroup \
    --location $region \
    --container-type PythonLTS \
    --max-sessions 100 \
    --cooldown-period 300 \
    --network-status EgressDisabled

```

## Running the application
To run the application, use the following command:

```bash
mvn clean install
mvn spring-boot:run
```

## Note
Ensure that the necessary environment variables are set before running the application.