FROM openjdk:17-jdk-alpine
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} medical-clinic-proxy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/medical-clinic-proxy-0.0.1-SNAPSHOT.jar"]