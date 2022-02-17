FROM amazoncorretto:11-alpine-jdk
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/customer-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} customer.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /customer.jar" ]