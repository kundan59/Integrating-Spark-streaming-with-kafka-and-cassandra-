# Integrating-Spark-streaming-with-kafka-and-cassandra

A basic template of integrating spark strucutured streaming with Kafka and CassandraDB
## Table of contents  
1. [Getting Started](#Getting-Started)  
2. [Running](#Running) 
3. [Result] (#Result)
  
## Getting Started  
#### Minimum requirements  
To run this example you will need  **Java 1.8+, Scala 2.12.10, SBT 1.3.8, spark 2.4.0 , Kafka 2.3.0 , Cassandra 3.10**.   

## Running 

Before running make sure kafka and Cassandra is running in your local
Or you can start fresh by following the below steps :

#######Running Cassandra:
Go to the Cassandra bin directory and run the Below command to start cassandra server
```
cassandra -f
```
Then, go inside the cassandra shell by running command:
```
cqlsh
```
In the shell, Run below commands to create Keyspace and table into cassandra
```
CREATE  KEYSPACE [IF NOT EXISTS] public 
   WITH REPLICATION = { 
      'class' : 'SimpleStrategy', 'replication_factor' : 1 } 
   };
CREATE TABLE [IF NOT EXISTS] public.car("Name" text primary key, "Cylinders" int, "Horsepower" int );
```
####### Running Kafka:
Go inside your kafka directory:
- Start Zookeper:
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```
- Start Kafka server:
```
bin/kafka-server-start.sh config/server.properties
```
- Create Kafka Topic:
```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafkaToCassandra
```
- Start Kafka Producer:
```
./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic kafkaToCassandra
```
Produce some sample messages in the kafka topic kafkaToCassandra
>>{"Name":"saab 99e", "Miles_per_Gallon":25, "Cylinders":4, "Displacement":104, "Horsepower":95, "Weight_in_lbs":2375, "Acceleration":17.5, "Year":"1970-01-01", "Origin":"Europe"}
>>{"Name":"amc gremlin", "Miles_per_Gallon":21, "Cylinders":6, "Displacement":199, "Horsepower":90, "Weight_in_lbs":2648, "Acceleration":15, "Year":"1970-01-01", "Origin":"USA"}
>> {"Name":"chevy c20", "Miles_per_Gallon":10, "Cylinders":8, "Displacement":307, "Horsepower":200, "Weight_in_lbs":4376, "Acceleration":15, "Year":"1970-01-01", "Origin":"USA"}

####### Runnuing spark application 
Go inside the project and open a terminal and run the below commands:
```
sbt clean compile
sbt run
```

###Result
Go to the cassandra shell and run the below command:
```
select * from public.car;
```
You will get Name of the cars, Number of Cylinders used, and Horsepower of a cars into the cassandra Database that streams from kafka.
