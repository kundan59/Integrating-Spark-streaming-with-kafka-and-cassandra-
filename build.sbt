name := "spark-streaming-with-cassandra"

version := "0.1"

scalaVersion := "2.12.10"

val sparkVersion = "2.4.0"
val cassandraConnectorVersion = "2.4.2"
val kafkaVersion = "2.3.0"
val log4jVersion = "2.4.1"

mainClass in compile := Some("com.knoldus.Spark.Job")
resolvers ++= Seq(
  "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven",
  "Typesafe Simple Repository" at "https://repo.typesafe.com/typesafe/simple/maven-releases",
  "MavenRepository" at "https://mvnrepository.com"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,

  // streaming
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  
  // streaming-kafka
  "org.apache.spark" % "spark-sql-kafka-0-10_2.12" % sparkVersion,

  // low-level integrations
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kinesis-asl" % sparkVersion,


  // cassandra
  "com.datastax.spark" %% "spark-cassandra-connector" % cassandraConnectorVersion,

  // logging
  "org.apache.logging.log4j" % "log4j-core" % "2.13.0",

  // kafka
  "org.apache.kafka" %% "kafka" % kafkaVersion,
  "org.apache.kafka" % "kafka-streams" % kafkaVersion
)