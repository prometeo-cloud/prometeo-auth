# Prometeo Authentication Microservice
A transparent proxy authentication microservice for prometeo. 
Useful for sitting in front of a web-application that is lacking basic security, like Solr and Elasticsearch.
Built using Spring Boot.

# Build
```
mvn package
```

# Usage
```
java -Xmx25m -jar target/prometeo-auth-1.0-SNAPSHOT.jar --destination.url=http://docs.spring.io/ --security.user.password=testing
```

Now you can visit `http://localhost:8080/spring-boot/docs/current/reference/html/boot-features-security.html`, which will require logging in with username/password: `user`/`testing`.
Supports all configuration methods of Spring Boot. 