FROM openjdk:8-jdk-alpine
EXPOSE 9083
ARG JAR_FILE=target/sb-rest-service-4jaeger-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]