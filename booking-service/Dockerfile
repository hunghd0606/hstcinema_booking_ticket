FROM eclipse-temurin:8-jdk-alpine
WORKDIR /app
COPY target/booking-service-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
