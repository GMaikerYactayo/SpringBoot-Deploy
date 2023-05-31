FROM amazoncorretto:17-alpine-jdk
MAINTAINER MGY
COPY SprinBoot-Deploy-0.0.1-SNAPSHOT.jar  myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]