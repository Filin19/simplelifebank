Guide for running Simple Life Bank application.
1. For running application you need Java 21 and Gradle 8.11.1.
2. For building project you need go to project directory and execute commant "gradlew build" in console.
3. To running project use command "java -jar build/libs/simplelifebank-1.0.0.jar" in the same directory.
4. To manually testing project you can use embedded swagger in project, use the URL below in your browser
http://localhost:8080/api/bank/swagger-ui/index.html#/.
5. Jacoco test coverage report generated automatically during project building. And you can find it in directory "build/reports/jacoco/test/html/index.html"  