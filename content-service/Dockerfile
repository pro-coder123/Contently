FROM openjdk:11
ADD ./target/content-service-0.0.1-SNAPSHOT.jar /usr/src/content-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "content-service-0.0.1-SNAPSHOT.jar"]
