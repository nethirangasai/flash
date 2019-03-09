package info.inetslov

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import scala.tools.scalap.Main
import org.apache.spark.launcher.Main

object word {
 // println("sai");
  def main(args:Array[String]){
    
  
  val conf=new SparkConf().setAppName("wordcount").setMaster("local")
  
  val spark=new SparkContext(conf)
  
  val file=spark.textFile(args(0))
  
  val words=file.flatMap(line => line.split(" "))
  
  val counts=words.map(word => (word,1))
  
  val wordcount=counts.reduceByKey(_+_)

  val wc=wordcount.sortByKey()
  
  wc.foreach(println)
  
  spark.stop()
  
  }
}