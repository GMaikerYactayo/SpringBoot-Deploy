FROM amazoncorretto:17-alpine-jdk
COPY target/SprinBoot-Deploy-0.0.1-SNAPSHOT.jar sprin-app.jar
ENTRYPOINT ["java","-jar","/mgb-app.jar"]