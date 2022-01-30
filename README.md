#This project includs design and implementation of a booking service for concert ticket, the modeling consists of UML diagrams depicting entity relation, sequence, and class diagram. The service is implemented using Spring Boot, Maven, H2 database, Junit for testing, docker for containerization, and JPA as ORM
#The UML diagrams are available in src/main/resources/modeling
For sake of modularity, in real case application, I strongly recommend to implement two standolone microservices (i.e. booking and payment service), but here I've implemented one service. However the payment service has been separated in service layer. The strategy and factory design patterns are used to handle different kinds of payments (Single and Multiple payment strategy)

#To run the service, please issue commands as below:
1. docker build /project-path-on-your-system/concert-booking -t concert-booking -f Dockerfile 
2. docker run concert-booking

#There are two ways inorder to build the project and run tests
1. docker build /project-path-on-your-system/concert-booking -t concert-booking -f Dockerfile 
Or
2. mvn clean test install

