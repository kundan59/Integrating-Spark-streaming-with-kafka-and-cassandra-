package com.knoldus.Spark

import com.knoldus.model.Car
import com.knoldus.services.{CassandraService, KafkaService}
import org.apache.spark.sql.{Dataset, SparkSession}

/**
  * Job is a Trait that contain abstract method run
  * and extending KafkaService and CassandraService Traits.
  */
trait Job extends KafkaService with CassandraService {

  def run(spark: SparkSession): Unit
}

/**
  * RunJob Class extending Job Trait and implementing run method
  * It also extends Logging Trait to add loggers.
  */
class RunJob extends Job {

  /**
    * Reading streams from Kafka and Writing it to Cassandra.
    * @param spark SparkSession
    */
  override def run(spark: SparkSession): Unit = {

    //"Reading streams from Kafka.
    val carDataset: Dataset[Car] = readFromKafkaTopic(spark)

    //do Transformations and Actions with the dataSet here

    //writing t streams to CassandraDB
    writeStreamToCassandra(carDataset, spark)
  }
}

object Main extends {

  // Main method
  def main(args: Array[String]) {

    //Creating spark session.
    val sparkSession = SparkSession.builder()
      .appName("Integrating Cassandra")
      .master("local[2]")
      .getOrCreate()

    //Instantiating RunJob then Reading and writing streams.
    val runJob = new RunJob()
    runJob.run(sparkSession)
  }
}

