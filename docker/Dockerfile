FROM maven:3.8.3-openjdk-17-slim AS build

WORKDIR app

COPY ./pom.xml .

RUN mvn package verify clean --fail-never

COPY ./src ./src

RUN mvn -Dmaven.test.skip=true package

FROM openjdk:17-slim

WORKDIR '/app'

COPY --from=build app/target/tweetapp-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "tweetapp-0.0.1-SNAPSHOT.jar"]
