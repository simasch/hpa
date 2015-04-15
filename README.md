#High-performance applications despite O/R mapping (or CQRS in the small)

##Run webapp
mvn spring-boot:run

[http://localhost:8080](http://localhost:8080)

##Data Generation

The HSQLDB is already filled with some test data. If you like to generate new data first:

1. DELETE http://localhost:8080/api/customers

2. GET http://localhost:8080/api/customers/generate
