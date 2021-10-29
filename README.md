# High-performance applications despite O/R mapping (or CQRS in the small)

## Database

hpa uses PosgreSQL Database on Port 5433 for the examples.

Start with Docker

    docker run --name postgres-hpa -d -p5433:5432 -e POSTGRES_DB=hpa -e POSTGRES_USER=hpa -e POSTGRES_PASSWORD=hpa postgres

## Run webapp
mvn spring-boot:run

[http://localhost:8080](http://localhost:8080)

## Generate Test Data
 
[http://localhost:8080/api/customers/generate](http://localhost:8080/api/customers/generate)

## Links
- [Random Data Generator](https://code.google.com/p/random-data-generator/)
- [log4jdbc](https://code.google.com/p/log4jdbc-log4j2/)
- [Apache Solr](http://lucene.apache.org/solr/)
