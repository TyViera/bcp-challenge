# Exchange rate backend challenge

Backend bcp challenge code.

This project use java 14 to run. 

### Build:
Execute:
````
mvn clean install
````

### Run:
Execute:
````
mvn spring-boot:run
````

### Docker:
Execute the first time:
````
mvn clean package
docker build -t bcp-challenge .
docker run --publish 8080:8080 --name bcp-challenge bcp-challenge
````

Stop:
````
docker stop bcp-challenge
````

Start/restart:
````
docker stop bcp-challenge
````

Reload:

If you want to reload the jar in docker file, first you must stop and remove the docker app

````
docker stop bcp-challenge
docker rm bcp-challenge 
````

And then execute again:
````
mvn clean package
docker build -t bcp-challenge .
docker run --publish 8080:8080 --name bcp-challenge bcp-challenge
````
