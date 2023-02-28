FROM amazoncorretto:11
RUN mkdir -p /app/
ADD build/libs/CacheDemo-service-1.0.0-SNAPSHOT.war /app/CacheDemo-service.war
ENTRYPOINT ["java", "-jar", "/app/CacheDemo-service.war"]
