FROM eclipse-temurin:8-jdk-alpine
WORKDIR /app
COPY target/auth-service-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
