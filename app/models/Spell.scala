package models

import models.Spell
import java.io.FileInputStream

import scala.io.Source
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsArray, Json}
import com.sun.org.apache.xpath.internal.operations.And
import org.apache.spark.sql.SparkSession
import play.api.libs.json._

/**
 * Presentation object used for displaying data in a template.
 *
 * Note that it's a good practice to keep the presentation DTO,
 * which are used for reads, distinct from the form processing DTO,
 * which are used for writes.
 */
case class Spell(components: Array[String], spell_resistance: Boolean, level: Array[Int], school: String, description: String, name: String){


}
object Spell {
  val mySpark = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()
  def allSpells(): List[Spell] = {


    // For implicit conversions like converting RDDs to DataFrames
    import mySpark.implicits._
    val rdd = mySpark.read.json("AllSpells.json")





    val result: List[Spell] = List[Spell]()

    val t = rdd.map(row => {
      println(row.getAs[Array[String]]("components"))
      println(row.getAs[Boolean]("spell_resistance"))
      println(row.getAs[Array[Int]]("level"))
      println(row.getAs[String]("school"))
      println(row.getAs[String]("description"))
      println(row.getAs[String]("name"))
      //val s = Spell(row.getAs[String]("name"), row.getAs[String]("name"), row.getAs[String]("name"), row.getAs[String]("name"), row.getAs[String]("name"))
      val s = Spell(row.getAs[Array[String]]("components"), row.getAs[Boolean]("spell_resistance"), row.getAs[Array[Int]]("level"), row.getAs[String]("school"),
        row.getAs[String]("description"), row.getAs[String]("name"))
      result :+ s
    })

    return result
  }



}



/*
object Spell {
  def allSpells(): Reads[Spell] = {
    val stream = new FileInputStream("AllSpells.json")
    val json = try {
      Json.parse(stream)
    } finally {
      stream.close()
    }

    implicit val spellReads: Reads[Spell] = (
      (JsPath \ "components").read[Array[String]] and
        (JsPath \ "spell_resistance").read[Boolean] and
        (JsPath \ "level").read[Array[Int]] and
        (JsPath \ "school").read[String] and
        (JsPath \ "description").read[String] and
        (JsPath \ "name").read[String]
      )(Spell.apply _)

    val s = Reads.seq(spellReads)
    val ss = Seq(spellReads)
    println("VICTOIRE " + spellReads)

    for (e <- spellReads){

    }

    List[Spell] l = Reads.list[]
    Reads.listReads[spellReads]
    return spellReads
  }
}

*/