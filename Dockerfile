FROM openjdk:27-ea-jdk
LABEL authors="tarunimamukherjee"
ADD target/bookmanagement.jar bookmanagement.jar
ENTRYPOINT ["java", "-jar", "/bookmanagement.jar"]