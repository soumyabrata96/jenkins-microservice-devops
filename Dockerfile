FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8006
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]