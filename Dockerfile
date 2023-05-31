FROM alpine:latest as packager
RUN apk --no-cache add openjdk17-jdk openjdk17-jmods
COPY target/SprinBoot-Deploy-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]