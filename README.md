# Recipe Management System

.Guide
  This is a simple spring boot rest  application to generate recipes by using ingridients.
  Its having a in memory database for storing generated recipes.Basically its having features 
  like creating recipes,getting all created recipies,getting recipe by id,updating recipe and
  deleting recipe.

1.What you'll need

  --JDK 1.11     
  --Maven 4.0.0     
  --spring-boot 2.4.0      

2.Here is the API Documentation of the application - http://localhost:8090/swagger-ui.html#!/

3.Run from comand line

  Change the current directory to your project’s directory, then type the following command to package your Spring Boot application to a JAR file:

   mvn package

  Notice that, to use Maven’s commands outside an IDE, you need to have Maven installed on your computer and the PATH environment variable includes 
  an entry to Maven’s bin directory. The mvn package command will compile the project and package it to a JAR file which is created in the project’s target directory.
  To run your Spring Boot application from the generated JAR file in command line, change the current directory to target, and type:

   java –jar ReciepeManagementSystem-0.0.1-SNAPSHOT.jar

