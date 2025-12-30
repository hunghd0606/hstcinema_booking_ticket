FROM eclipse-temurin:8-jdk-alpine
WORKDIR /app
COPY target/notification-service-*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","app.jar"]
