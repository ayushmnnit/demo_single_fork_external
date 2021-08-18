import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

import graph._

@Visual(mode = "batch", interimMode = "full")
object Main {

  def graph(spark: SparkSession): Unit = {

    val df_Source0:   Source   = Source0(spark)
    val df_Reformat0: Reformat = Reformat0(spark, df_Source0)
    val df_Filter0:   Filter   = Filter0(spark,   df_Reformat0)

  }

  def main(args: Array[String]): Unit = {
    import config._
    ConfigStore.Config = ConfigurationFactoryImpl.fromCLI(args)

    val spark = SparkSession
      .builder()
      .appName("test111")
      .config("spark.sql.legacy.allowUntypedScalaUDF", "true")
      .enableHiveSupport()
      .getOrCreate()

    UDFs.registerUDFs(spark)
    UDAFs.registerUDAFs(spark)

    val sc = spark.sparkContext
    sc.setCheckpointDir("/tmp/checkpoints")

    graph(spark)
  }

}
