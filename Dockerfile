FROM amazoncorretto:17-alpine-jdk
COPY SprinBoot-Deploy-0.0.1-SNAPSHOT.jar spring-app.jar
ENTRYPOINT ["java","-jar","/spring-app.jar"]