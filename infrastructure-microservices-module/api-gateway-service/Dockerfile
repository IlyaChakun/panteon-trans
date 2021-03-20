

FROM java:8-jre

VOLUME /tmp
ADD target/api-gateway-service.jar api-gateway-service.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/api-gateway-service.jar"]

EXPOSE 8080