FROM openjdk:17
ARG JAR_FILE=build/libs/neoris-0.0.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]