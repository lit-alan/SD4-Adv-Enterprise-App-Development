## Implementing AuthN with JWT in a Simple REST API :boom:

If you want to jump straight into testing the code using Postman, then click [here](#6-sample-run). Don't forget to come back and read the rest of the document to gain a full understanding of how it all works.

**JSON Web Token (JWT)** are widely used in authentication and authorisation, including in the context of web applications and APIs. 

### 1. Structure of a Token _(this part is a little technical and at this stage you don't need to fully understand it)_ :relieved:  

A JWT consists of three parts, separated by dots (.) and might look like the following `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwOTgzMjY4MiwiZXhwIjoxNzA5ODY4NjgyfQ.1ZlVxIA3klIxhw4bcRaQqA3b_z6qayhBJ69AoZKbf7U`

- **Header:** Contains metadata about the type of token (JWT) and the signing algorithm being used (e.g., HMAC SHA256 or RSA).

- **Payload:**  Contains the claims. Claims are statements about an entity (typically, the user) and additional data. There are three types of claims:
        Registered claims: A set of predefined claims which are not mandatory but recommended. They include iss (issuer), exp (expiration time), sub (subject), and others.
        Public claims: These can be defined at will by those using JWTs. They should be defined in the IANA JSON Web Token Registry or be defined as a URI to avoid collisions.
        Private claims: Custom claims created to share information between parties that agree on using them.

- **Signature:** Used to verify that the sender of the JWT is who it says it is and to ensure that the message wasn't changed along the way.

---
### 2. How JWT Works

- **Token Generation:** After a user's credentials have been verified (by the API) a JWT is generated. 

- **Token Transmission:** The API sends the JWT back to the client, where it can be stored in local storage, session storage, or a cookie.

- **Token Use in Requests:** When the client makes requests to a server that requires authentication, the JWT is included in the request headers, typically as a Bearer token.

- **Token Verification:** The API then validates the token's signature to ensure it's valid and checks the token's expiration (in the code I have provided, the token is valid for 10hrs), issuer, and subject against the expected values.

- **Access Granted or Denied:** Based on the validity of the token, the API decides whether to fulfill the request or deny it.

---

### 3. Examining the API's routes.

- **Publicly Accessible Routes**:  
        `/authenticate:` Used for user authentication. Upon successful authentication, it returns a JWT token.  
        `/goodbye:` A simple endpoint that returns "Goodbye World" and does not require authentication.

- **Protected Routes**:
        All other routes (there's only one atm, `/hello`) in the application are protected and require a valid JWT token for access. The token must be included in the Authorization header of the request.

---

### 4. Overview of the provided code.


### Controllers

- **AuthenticationController:** Handles the `/authenticate` POST request. It authenticates the user by their username and password, generates a JWT (JSON Web Token) if authentication is successful, and returns the token in the response.

- **HelloWorldController**: Provides two simple GET endpoints, `/hello` and `/goodbye`, that return "Hello World" and "Goodbye World" strings, respectively.

### Exception Handling

- **GlobalExceptionHandler**: Catches and handles `SignatureException` globally, which usually indicates an invalid JWT signature. It returns an HTTP 401 Unauthorized response with a message about the invalid JWT signature.

### Models

- **AuthenticationRequest**: Represents the request body for authentication, containing the username and password.

- **AuthenticationResponse**: Encapsulates the JWT to be sent back to the client upon successful authentication.

### Filters

- **JwtRequestFilter**: A filter that runs once per request. It checks for the JWT in the `Authorization` header, validates it, and sets the authentication in the security context if the token is valid.

### Utility Classes

- **JwtUtil**: Contains utility methods for working with JWTs, such as extracting the username, checking token expiration, generating tokens, and validating tokens against a secret key and username.

### Security Configuration

- **SecurityConfig**: Configures Spring Security for the application. It disables CSRF protection (since token-based authentication is used), specifies URL-based authorization rules, sets session management to stateless, and registers `JwtRequestFilter` to run before `UsernamePasswordAuthenticationFilter`.

### Service

- **MyUserDetailsService**: Implements `UserDetailsService`. It defines how to load user details given a username. In this simple example, it checks if the username is "admin" and returns a user with an encoded password.

---


### 5. Workflow

1. **Authentication**: Upon receiving a POST request to `/authenticate`, `AuthenticationController` uses `AuthenticationManager` to authenticate the user. If successful, it generates a JWT using `JwtUtil` and returns it in the response.

2. **Authorization and Token Validation**: For other requests, `JwtRequestFilter` extracts and validates the JWT from the `Authorization` header. If the token is valid, it sets the authentication in the security context, allowing the request to proceed.

3. **Accessing Protected Resources**: With the security context set, requests to protected endpoints are allowed based on the configurations in `SecurityConfig`. The example allows public access to `/authenticate` and `/goodbye`, while other endpoints require authentication.

4. **Exception Handling**: `GlobalExceptionHandler` provides centralized exception handling, specifically for JWT signature exceptions, enhancing security by providing a uniform way to handle authentication errors.

5. **Security Configuration**: `SecurityConfig` ensures that CSRF protection is disabled for this token-based authentication system, enforces URL-based authorization rules, and adopts a stateless session management policy to fit the RESTful architecture and the token-based authentication approach.

---

### 6. Sample Run


Authentication is not requrired to access `/goodbye`

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/907c6279-87ec-4e11-9e2e-78058ab04ea2)

Any attempt by an unauthorised user to access `/hello` will return a status of **403 Forbidden**.

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/ff1c5aa2-5c0c-40c2-ba42-cef13414508e)


To begin the process of authentication send a **POST** request to `/authenticate`. The **body** of the request must contain a username and password combination of 'admin' and 'password' respectively. For example:

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/ba9391dc-80e8-4fe1-8858-b696eef9c0eb)



_Note that the username and password are submitted in JSON format._

Upon receipt of a valid username and password combination, the REST API will create a Web Token which is returned to the client (see the body of the response above). The client will be required to send this token with every request it makes for a restricted resource (in this API there is only one restricted resource `/hello`).

If an invalid username and password combination are sent to the API it will respond with a 403 Forbidden Status (and cruically, no token will be returned). For example:

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/470f4c34-9783-4c9e-9db9-791ad397993f)

To access the (restricted) content at `/hello` the token must be sent in the authorisation header of the request. Firstly, copy the token from the response that you recieved upon authentication, then select the _Authorization_ tab in Postman and set the type to _Bearer Token_. Paste the copied token into the space provided and submit the **GET** request to `/hello`. The API will then read the token from the header, validate it and then return the restricted content. For example:

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/8348af95-874f-43d6-b20d-ca1666d23007)

The token will expire 10 hours after it has been generated in which case the client would be required to re-authenticate with the API.

If the token is tampered with in any way, for example, below I have appended 'zzz' to it, the API will return a 403 Forbidden status and the restricted content will not be returned.

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/9440b153-13cc-4a28-a756-a3d9f65da210)

If the client neglects to send the token with subsequent requests they will recieve a 403 Forbidden status. For example:

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/26f6e2f9-c0b5-4cf6-9986-22bbcac54281)

---

### 7. Running the Java Client.

While the Spring boot application is running , you can run the provided Java Client. This code authenticates a user and accesses the protected resource (`/hello`) using a JWT.

The authenticate method sends a (POST) request to `/authenticate` with a JSON body containing user credentials (username and password). If the authentication is successful (HTTP 200 status code), it parses the response to extract the JWT token provided by the server.  

With the obtained JWT token, the accessProtectedResource method sends a (GET) request to `/hello`. It includes the JWT token in the Authorisation header as a Bearer token. If access is granted (HTTP 200 status code), it prints the response from the protected resource.  

The output for the client looks as follows:  

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/62e047b2-689d-414b-903d-1c0e422fd44b)

 If authentication fails or accessing the protected resource fails, it prints an appropriate message to the console.
