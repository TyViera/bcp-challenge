#Exchange rate backend challenge

Backend bcp challenge code.

This project use java 14 to run. 

###Build:
Execute:
````
mvn clean install
````

###Run:
Execute:
````
mvn spring-boot:run
````

###Docker:
Execute:
````
mvn clean package
docker build -t bcp-challenge .
docker run --publish 8080:8080 --name bcp-challenge bcp-challenge
````
