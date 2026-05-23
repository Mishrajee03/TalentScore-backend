# Build Stage: Compiles your Java app using Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run Stage: Runs the compiled .jar file
FROM openjdk:17-jdk-slim
COPY --from=build /target/talentscore-0.0.1-SNAPSHOT.jar talentscore.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","talentscore.jar"]