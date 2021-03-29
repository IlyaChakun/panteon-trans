
FROM java:8-jre


ADD ./target/companies-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/companies-service.jar"]

EXPOSE 8081
