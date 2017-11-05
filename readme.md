# Prometeo Authentication Microservice
A transparent proxy authentication microservice for prometeo. 
Built using Spring Boot and Jetty-Proxy with basic authentication.

# Build
```
mvn package
```

# Running the Proxy
```
java -Xmx25m -jar target/prometeo-auth-1.0-SNAPSHOT.jar --DESTINATION_URL=http://docs.spring.io/ --URL_MAPPINGS=/* --USER_PASSWORD=testing
```

# Testing thw proxy with Postman

- **Method**: GET
- **URL**: http://localhost:8080/spring-boot/docs/current/reference/html/boot-features-security.html
- **Headers**: Authorization: Basic dXNlcjp0ZXN0aW5n

To generate the basic authentication header use for example a generator like [this](https://www.blitter.se/utils/basic-authentication-header-generator/) passing:

- username = user
- password = testing

should produce the following result: **Authorization: Basic dXNlcjp0ZXN0aW5n**

| Environment Var  | Description  | Example  |   
|---|---|---|
| DESTINATION_URL   | The root of the site the proxy points to.  | http://docs.spring.io/  |
| URL_MAPPINGS  | The pattern for filtering the paths which are to be accessible.  | "/*" for everything  |
| security.user.password  | The password to use for authenticating **user**  | e.g. testing  |