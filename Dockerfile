FROM amazoncorretto:17-alpine-jdk
ADD target/SprinBoot-Deploy-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]