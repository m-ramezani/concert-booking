#The UML diagrams are available in src/main/resources/modeling
For sake of modularity, in real case application, it's better to have two services (booking and payment) 

#To run the service, please run commands as below:

docker build /app/src/concert-booking -t concert-booking -f Dockerfile
docker run concert-booking