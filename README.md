### Spring REST API for blogging content management

> Using MySQL Community Server 8.0.34, Oracle JDK 17.0.2, Eclipse IDE 4.25, Maven 3.8.8, VSCode

#### For API documentation and(maybe demos): visit below link

[Azure Web App](http://javaspringtest2.azurewebsites.net/)

#### How to run on local machine

1. Prepare a MySQL instance: [Normal Installation](https://dev.mysql.com/downloads/installer/), Docker Images(https://hub.docker.com/_/mysql), Remote Database...

Change ```src/main/resources/application.properties``` for database connection and hibernate behavior

2. Modified JWT Secrect with another value if needed, a sample key has been given, key has to be long enough. Change the expiration time of jwt if need.

3. Build and Run Project in any IDE.

- If using command line, buid the project with below command
```./mvnw clean install```
- or in Windows:
```.\mvnw.cmd clean install```

- If using command line, run the app with below command, auto reload included.
```./mvnw spring-boot:run```
- or in Windows:
```.\mvnw.cmd spring-boot:run```

Basic Rest API for a blogging content website with usecases:
- CRUD Category(admin)
- CRUD Post(admin)
- CRUD Comments on Post
- Authentication

#### References
[The Builder Pattern in Spring Boot Development](https://wiki.yowu.dev/en/Knowledge-base/Spring-Boot/the-builder-pattern-in-spring-boot-development)

**Authorization disable for testing**

> IoC - DI - Proxy in Spring framework
> DTO design pattern, instead of calling request for each data part(attribute of an object...),
> combined in DTO and bring around
> --> in complex app, mapping from DTO to Model --> ResponseEntity<>(DTO, HttpStatus)
