# cowlib-server

## how to start
cd (project)/src/main/resources/application-local.properties
```
put social login
spring.social.facebook.appId=
spring.social.facebook.appSecret=
```
cd (project)
./gradlew build
java -jar -Dspring.profiles.active=local ./build/libs/com.cowlib-web-0.1.0.jar
