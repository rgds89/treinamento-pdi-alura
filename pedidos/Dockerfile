FROM maven:3-openjdk-17 AS build
COPY src /home/roger-silva/Workspace/treinamento-pdi-alura/pedidos/src
COPY pom.xml /home/roger-silva/Workspace/treinamento-pdi-alura/pedidos
RUN mvn -f /home/roger-silva/Workspace/treinamento-pdi-alura/pedidos/pom.xml clean package -Dmaven.text.skip

FROM eclipse-temurin:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8080
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
