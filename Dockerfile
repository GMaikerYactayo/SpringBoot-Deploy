FROM amazoncorretto:17-alpine-jdk
MAINTAINER MGY
COPY target/SprinBoot-Deploy-0.0.1-SNAPSHOT.jar /home/spring-app.jar
ENTRYPOINT ["java","-jar","/home/spring-app.jar"]