#FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7
FROM amazoncorretto:11.0.15

# App
CMD mkdir /app
COPY build/libs/*.jar /app/app.jar

# Ports exposing
EXPOSE 9080

# Set entrypoint command
ENTRYPOINT echo JAVA_OPTS=$JAVA_OPTS && java $JAVA_OPTS -jar /app/app.jar
