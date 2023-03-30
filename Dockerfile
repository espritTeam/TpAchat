FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/tpAchatProject-3.0.jar tpAchatProject-3.0.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-3.0.jar"]
