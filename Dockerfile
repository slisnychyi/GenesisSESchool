FROM openjdk:17-jdk-slim AS build
WORKDIR /app

COPY ./ ./
RUN ./gradlew clean build

FROM openjdk:17-jdk-slim
COPY --from=build /app/build/libs/GenesisSESchool-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
