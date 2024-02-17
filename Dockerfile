FROM amazoncorretto:11-alpine-jdk
MAINTAINER baeldung.com
COPY build/libs/*.jar user-management.jar
ENTRYPOINT ["java","-jar","/user-management.jar"]