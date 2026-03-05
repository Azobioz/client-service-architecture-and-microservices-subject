# Используем образ с Gradle и JDK 17
FROM gradle:8.3-jdk17 AS build

WORKDIR /app

# Копируем только build.gradle и исходники
COPY build.gradle ./
COPY src ./src

# Сборка Spring Boot jar
RUN gradle bootJar --no-daemon

# Минимальный runtime-образ
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Копируем jar из build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]