package graph

import org.apache.spark.sql.types._
import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import config.ConfigStore._
import udfs.UDFs._
import udfs._
import graph._

@Visual(id = "Filter0", label = "Filter0", x = 509, y = 312, phase = 0)
object Filter0 {

  def apply(spark: SparkSession, in: DataFrame): Filter = {
    import spark.implicits._

    val out = in.filter(lit(true))

    out

  }

}
