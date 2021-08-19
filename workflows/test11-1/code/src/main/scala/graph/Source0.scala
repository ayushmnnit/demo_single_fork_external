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

@Visual(id = "Source0", label = "Source0", x = 216, y = 207, phase = 0)
object Source0 {

  @UsesDataset(id = "17", version = 0)
  def apply(spark: SparkSession): Source = {
    import spark.implicits._

    val out = Config.fabricName match {
      case "dev" =>
        val schemaArg = StructType(
          Array(StructField("k1",       StringType, false),
                StructField("order_id", StringType, false),
                StructField("kajari",   StringType, false)
          )
        )
        spark.read
          .format("csv")
          .option("header", true)
          .option("sep",    ",")
          .schema(schemaArg)
          .load("http://kajari")
          .cache()
      case _ => throw new Exception(s"The fabric is not handled")
    }

    out

  }

}
