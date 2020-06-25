FROM gradle:6.3.0-jdk14
RUN addgroup --system spring
RUN adduser --system spring --gecos spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]