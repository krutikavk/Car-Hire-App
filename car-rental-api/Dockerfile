FROM openjdk:13-jdk-alpine
EXPOSE 8080
WORKDIR /opt/app
ARG JAR_FILE=target/MyCar/Application.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]