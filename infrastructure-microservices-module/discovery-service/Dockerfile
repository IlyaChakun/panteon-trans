FROM java:8-jre


ADD target/discovery-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/discovery-service.jar"]

EXPOSE 8761