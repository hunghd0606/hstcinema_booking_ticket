FROM eclipse-temurin:8-jdk-alpine
WORKDIR /app
COPY target/payment-service-*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","app.jar"]
