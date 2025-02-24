FROM amazoncorretto:21-alpine
WORKDIR /mnt
COPY . /mnt
RUN chmod +x scripts/*.sh
RUN ./gradlew clean build
EXPOSE 9090
CMD ["java", "-jar", "app/build/libs/app-1.0.0.jar"]
