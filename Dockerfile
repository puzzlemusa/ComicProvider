FROM maven:3.6.3-jdk-11
WORKDIR /usr/src/comic-serice
COPY . /usr/src/comic-serice/
RUN mvn package
RUN cp /usr/src/comic-serice/target/*.jar ./app.jar
CMD ["java", "-jar", "app.jar"]