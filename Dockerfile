# -------- BUILD STAGE --------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# -------- RUN STAGE --------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/fitness-monolith-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
