FROM openjdk:17-alpine
ARG JAR_FILE
COPY target/jwt-0.0.1-SNAPSHOT.jar /jwt-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jwt-0.0.1-SNAPSHOT.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]