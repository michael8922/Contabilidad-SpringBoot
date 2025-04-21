# Etapa 1: Compilación del proyecto
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=builder /app/target/contabilidad-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
