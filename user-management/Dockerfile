FROM openjdk:11
ADD ./target/user-management-0.0.1-SNAPSHOT.jar /usr/src/user-management-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "user-management-0.0.1-SNAPSHOT.jar"]