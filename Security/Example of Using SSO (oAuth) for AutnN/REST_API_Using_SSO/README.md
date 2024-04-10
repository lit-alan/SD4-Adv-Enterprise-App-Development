## Implementing AuthN with Single Sign On in a Simple REST API :computer:

This guide walks you through integrating GitHub as an OAuth 2.0 provider for a Spring Boot application. I'll cover the configuration steps and explain the code snippets provided for setting up security and handling basic REST endpoints.

SSO is an authentication process that allows a user to access multiple applications with one set of login credentials. This means that the user logs in once and gains access to all associated systems without being prompted to log in again at each of them. When using GitHub as an SSO provider, your application delegates the authentication process to GitHub. Here's the flow:

1. **User Attempts to Access a Protected Resource:** The user tries to access a part of your application that requires authentication.  
2. **Redirection to GitHub:** Instead of prompting the user for a username and password, your application redirects the user to GitHub's login page.  
3. **Authentication:** The user enters their GitHub credentials. If they're already logged in to GitHub, this step might be skipped.  
4. **Authorization:** The user is asked to authorize your application to access their GitHub information (based on the scopes you've defined). This step is also skipped if the authorization has already been granted in the past.  
5. **Redirection Back to Your Application:** After successful authentication and authorization, GitHub redirects the user back to your application, along with an authorization code.  
6. **Access Token Exchange:** Your application exchanges the authorization code for an access token using the client ID and client secret.  
7. **Access Granted:** Your application uses the access token to make API requests on behalf of the user or simply grants access to the protected resource.  

### Step 1: Registering your application with GitHub
As previously mentioed, this example is using GitHub as a SSO provider so you will need to register your application with GitHub (your own GitHub account) for OAuth 2.0 authentication.

- Navigate to your account settings on GitHub, then to the "Developer settings" section.
- Go to the "OAuth Apps" page and click on "New OAuth App".
- Fill out the form with your application's details:  
       ![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/049470b0-3bd4-4901-879c-bc48b9dd8bd7)
 
The authorization callback URL is the URL in your application where users will be sent after authorization. This must be a URL where your application is prepared to receive an OAuth code and exchange it for an access token. You can enter ```http://localhost:8080/login/oauth2/code/github```

After registering, you'll be provided with a Client ID and a Client Secret. These are important credentials that you'll use in your application to authenticate with GitHub's OAuth server. You will need to add them to the the [application.properties file](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Security/Example%20of%20Using%20SSO%20(oAuth)%20for%20AutnN/REST_API_Using_SSO/src/main/resources/application.properties) in your project. This file contains the OAuth 2.0 client configuration for GitHub authentication, including client credentials, scopes, and GitHub's authorization and token endpoints. I have also enabled Spring Security logging settings in this file.


### Step 2: Create Controllers

In this example there are two controllers:

- A [HelloWorldController](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Security/Example%20of%20Using%20SSO%20(oAuth)%20for%20AutnN/REST_API_Using_SSO/src/main/java/com/example/rest_api_using_sso/controllers/HelloWorldController.java). This controller contains two simple endpoints ```/hello``` and ```/goodbye```
- A [UserController](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Security/Example%20of%20Using%20SSO%20(oAuth)%20for%20AutnN/REST_API_Using_SSO/src/main/java/com/example/rest_api_using_sso/controllers/UserController.java). This accesses the authenticated user's principal information, allowing you to retrieve user details like the username.


### Step 3: Define Security Config

This [configuration](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Security/Example%20of%20Using%20SSO%20(oAuth)%20for%20AutnN/REST_API_Using_SSO/src/main/java/com/example/rest_api_using_sso/security/SecurityConfig.java) disables CSRF protection (as it's often not required for token-based REST APIs), permits unauthenticated access to the root (```/```) and ```/goodbye``` endpoints, but requires authentication (using SSO with GitHub) for all other requests.


### Step 4: Running and Testing the Application

Access to (```/```) and ```/goodbye``` are allowed without authentication. This means anyone can access these routes.

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/57914e13-39b4-4f2d-abf0-8ab3faec89bd)


Attempting to access ```/hello``` will lead to you being redirected to GitHub for login. Upon successful authentication (with your own GitHub username and password), GitHub redirects back to your application, granting access to the protected resource.

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/f3380cac-62f3-452e-9079-540aa6a6f924)

I have MFA enabled on my GitHub account (you may not) so I am presented with a further challenge to complete authentication.

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/59b8b9a7-b481-4207-80fd-fb707611a393)

I am then redirected to the restricted content at ```/hello```

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/6de395ec-03b1-4f36-a543-cbd32e1a2cbe)


Accessing ```/user/name``` will see the authenticated user name (GitHub account username) displayed.

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/bf43c207-3d41-4165-9a25-852e9a42d912)


Accessing ```/user``` will see the authenticated user's principal returned. The Principal object represents the currently authenticated user and contains information about the user's identity as recognised by the application after successful authentication through GitHub's OAuth 2.0 process. 

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/3a1cd68e-a533-4d57-9f57-9cf0d4c284a9)


 As can be seen, when I navigate to ```/user```  I see some basic information about my GitHub account (number of followers, a HATEOAS style link to information about my followers etc).
 




