FROM  openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} selfService-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/selfService-1.1.jar"]
