FROM openjdk:17
VOLUME /tmp
ADD ./target/Plz2Gps-Service-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]