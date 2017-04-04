# High-performance applications despite O/R mapping (or CQRS in the small)

## Database

hpa uses Derby Database for the examples. So you need to start  Derby in server mode:

run $DERBY_HOME/bin/startNetworkServer

## Run webapp
mvn spring-boot:run

[http://localhost:5050](http://localhost:5050)

## Generate Test Data
 
[http://localhost:5050/api/customers/generate](http://localhost:5050/api/customers/generate)

## Links
- [Random Data Generator](https://code.google.com/p/random-data-generator/)
- [log4jdbc](https://code.google.com/p/log4jdbc-log4j2/)
- [Apache Solr](http://lucene.apache.org/solr/)
