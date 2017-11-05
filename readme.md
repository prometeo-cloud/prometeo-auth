# Prometeo Authentication Micro-service
A transparent proxy authentication micro-service for Prometeo. 

Built using Spring Boot and Jetty-Proxy.
 
Supports [HTTP Basic Authentication](https://en.wikipedia.org/wiki/Basic_access_authentication). 

HTTP requests to the proxy should be done over a TLS connection to prevent a [man-in-the-middle attack](https://en.wikipedia.org/wiki/Man-in-the-middle_attack).

# Build
```
mvn package
```

# Running the Proxy
```
java -Xmx25m -jar target/prometeo-auth-1.0-SNAPSHOT.jar --DESTINATION_URL=http://docs.spring.io/ --URL_MAPPINGS=/* --USER_PASSWORD=testing
```

# Testing the proxy with Postman

- **Method**: GET
- **URL**: http://localhost:8080/spring-boot/docs/current/reference/html/boot-features-security.html
- **Headers**: Authorization: Basic dXNlcjp0ZXN0aW5n

To generate the basic authentication header use for example a generator like [this](https://www.blitter.se/utils/basic-authentication-header-generator/) passing:

- username = user
- password = testing

should produce the following result: **Authorization: Basic dXNlcjp0ZXN0aW5n**

**NOTE**: should the authentication header be wrong or missing the proxy responds with HTTP 404 (Not Found).

# Configuration via Environment Variables

The following variables configure the behaviour of the proxy:

| Environment Var  | Description  | Example  |   
|---|---|---|
| DESTINATION_URL   | The root of the site the proxy points to.  | http://docs.spring.io/  |
| URL_MAPPINGS  | The pattern for filtering the paths which are to be accessible.  | "/*" for everything  |
| USER_PASSWORD  | The password to use for authenticating the "**user**" username. | e.g. testing  |
| HTTP_PORT  | The port the proxy is listening on. | The default is 8081  |

**NOTE**: the username is always "user".