# ----------- STAGE 1: BUILD -------------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

# ----------- STAGE 2: RUN --------------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /build/target/quarkus-app/ ./

EXPOSE 8081

CMD ["java", "-jar", "quarkus-run.jar"]