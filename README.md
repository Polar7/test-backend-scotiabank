# Developer testing Backend Software Engineer Associate

This project is a backend developed in **Spring Boot** which serves as a sample of knowledge to apply for the position of Backend developer in Scotiabank.

## Prerequisites to build and run

Se requieren las siguientes herramientas para la ejecución local:

- JDK 17 o higher

### Steps for run using Gradle
1. Clone project.
2. In the root path of the project, run './gradlew clean build'. This will compile the project generating the JAR executable.
3. Finally, run 'java -jar ./build/libs/backend-scotiabank-test-0.0.1-SNAPSHOT.jar'. The application will be executed on port 8090 and will be accessible at this [path](http://localhost:8090/api-test-scotiabank/swagger-ui/index.html)

## Use
You can navigate through the various options in the Swagger interface.

![image](https://github.com/user-attachments/assets/feb2ea78-f3fa-46df-8f2e-ab257cac093f)

## Entity–relationship model
An entity/relationship model was developed for a better understanding of the exercise.

![image](https://github.com/user-attachments/assets/2feec52c-a93d-451e-96d4-19e5a4567122)


## Conclusion

Ports and adapters Architecture was implemented for the development.
The JSON to save or update has validations such as not null/empty, email format and telephone format (not decimals).
When a new position is entered for an employee, the current position becomes Inactive and and the new one becomes active.
A local database was used (H2)

I hope you like it.
