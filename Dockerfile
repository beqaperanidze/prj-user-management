FROM amazoncorretto:11-alpine-jdk
COPY build/libs/*-SNAPSHOT.jar user-management.jar
ENTRYPOINT ["java","-jar","/user-management.jar"]