FROM eclipse-temurin:21-jdk
LABEL authors="mahbuhrer"

VOLUME /tmp
EXPOSE 8087

ADD target/Q07-0.0.1-SNAPSHOT.jar Questao07.jar
ENTRYPOINT ["java","-jar","/Questao07.jar"]