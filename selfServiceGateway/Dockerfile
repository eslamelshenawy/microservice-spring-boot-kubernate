FROM  openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} selfServiceGateway-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/selfServiceGateway-1.1.jar"]
