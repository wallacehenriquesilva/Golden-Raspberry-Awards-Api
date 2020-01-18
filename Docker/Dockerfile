FROM openjdk:8-jre
ADD /jar/Worst-movie.jar /worst-movie.jar
ADD /files/movielist.csv /movielist.csv
ENTRYPOINT ["java" , "-jar", "-Dspring.profiles.active=production", "/worst-movie.jar"]