FROM amazoncorretto:17-alpine-jdk
MAINTAINER MGY
COPY target/SprinBoot-Deploy-0.0.1-SNAPSHOT.jar sprinBoot-deploy-app.jar
ENTRYPOINT ["java","-jar","/mgb-app.jar"]