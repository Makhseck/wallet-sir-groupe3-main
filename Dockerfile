#Image de base
FROM openjdk:17-alpine
LABEL maintainer="sir-soir-groupe3@gmail.com"
VOLUME /sir-soir-data
ADD target/sir2022-0.0.1-SNAPSHOT.jar groupe3.jar
EXPOSE 8082
#java -jar
ENTRYPOINT ["java", "-jar", "/groupe3.jar"]