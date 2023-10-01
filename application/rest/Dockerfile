FROM openjdk:17-alpine

COPY /target/rest-*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]
