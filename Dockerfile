FROM eclipse-temurin:17-jre AS runtime
WORKDIR /app
COPY target/*-runner.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
