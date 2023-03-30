FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/tpAchatProject-1.1-SNAPSHOT.jar tpAchatProject-1.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-1.1-SNAPSHOT.jar"]
