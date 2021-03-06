package example

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object PhysComp {
  def main(args: Array[String]) {
    if (args.length < 1) {
      System.err.println("Usage: PhysComp <file>")
      System.exit(1)
    }

    val sconf = new SparkConf().setAppName("PhysComp Count").set("spark.ui.port","4141")
    val sc = new SparkContext(sconf)

    val file = args(0)
    val counts = (sc.textFile(file).map(getCol)).map(word => (word,1)).reduceByKey((v1,v2) => v1+v2) 
    counts.repartition(1).saveAsTextFile("/home/training/Desktop/output")
    
    sc.stop()
  }
  def getCol(line: String ): String=
  {
   var variable=line.split(",")
   variable(2)+"  "+variable(23)+"  "+variable(24)
    
  }
}


