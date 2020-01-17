FROM openjdk:8-jre
ADD /jar/Worst-movie.jar /worst-movie.jar
ENTRYPOINT ["java" , "-jar", "-Dspring.profiles.active=production", "/worst-movie.jar"]