package com.knoldus.services

import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}
import com.knoldus.model.Car

/**
  * CassandraService is a Trait that has one concrete method writeStreamToCassandra.
  * It extends Logging Trait to add loggers.
  */
trait CassandraService {

  /**
    * Writes carDataset to CassandraDB.
    *
    * @param carDataset Dataset to write.
    * @param spark SparkSession.
    */
  def writeStreamToCassandra(carDataset: Dataset[Car], spark: SparkSession): Unit = {

    // Connecting to CassandraDB and writing streams to it.
    carDataset
      .writeStream
      .foreach(new CarCassandraForeachWriter(spark))
      .start()
      .awaitTermination()
  }

}
