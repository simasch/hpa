# High-performance applications despite O/R mapping (or CQRS in the small)

## Database

hpa uses Derby Database for the examples. So you need to start  Derby in server mode:

run $DERBY_HOME/bin/startNetworkServer

## Run webapp
mvn spring-boot:run

[http://localhost:8080](http://localhost:8080)

## Generate Test Data
The database already contains some test data. But if you like to generate it you can use these services:

 1. DELETE http://localhost:8080/api/customers
 
 2. GET http://localhost:8080/api/customers/generate

## Links
- [Random Data Generator](https://code.google.com/p/random-data-generator/)
- [log4jdbc](https://code.google.com/p/log4jdbc-log4j2/)
- [Apache Solr](http://lucene.apache.org/solr/)
