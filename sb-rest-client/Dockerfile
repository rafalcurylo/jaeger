FROM openjdk:8-jdk-alpine
EXPOSE 9087
ARG JAR_FILE=target/sb-rest-client-4jaeger-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]