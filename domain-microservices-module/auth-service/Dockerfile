
FROM java:8-jre

ADD ./target/auth-service.jar /app/auth-service.jar
CMD ["java", "-Xmx200m", "-jar", "/app/auth-service.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:5000/actuator/health || exit 1

EXPOSE 5000