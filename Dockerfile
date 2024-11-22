FROM openjdk:17
COPY build/libs/ecommerce.jar ecommerce.jar
ENTRYPOINT ["java", "-Duser.timezone=America/Bogota", "-jar", "/ecommerce.jar"]

