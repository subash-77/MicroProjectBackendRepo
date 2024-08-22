FROM openjdk:18
WORKDIR /app
COPY ./target/TelepsychiatryServicesManagementSystem-0.0.1-SNAPSHOT.jar /app
EXPOSE 1225
CMD ["java", "-jar", "TelepsychiatryServicesManagementSystem-0.0.1-SNAPSHOT.jar"]
