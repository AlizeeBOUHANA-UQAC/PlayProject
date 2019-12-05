package models

import models.Spell
import java.io._

import java.util.regex.Matcher
import java.util.regex.Pattern
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
case class Spell(components: Array[String], spell_resistance: Boolean, level: Int, school: String, description: String, name: String)
object Spell {
  def allSpells(): List[Spell] = {

    var listResult: List[Spell] = List();
    val stream = new FileInputStream("AllSpells.json")
    val json: JsValue = try {
      Json.parse(stream)
    } finally {
      stream.close()
    }

    val components = json \\ "components"
    val spell_resistance = json \\ "spell_resistance"
    val level = json \\ "level"
    val school = json \\ "school"
    val description = json \\ "description"
    val names = json \\ "name"
    for (i <- names.indices){
      val matcher = Pattern.compile("\\d+").matcher(level(i).toString)
      var lev = -1
      if (matcher.find) {
        lev = matcher.group(0).toInt
      }

      val s = Spell(
        components(i).toString.replaceAll("\\[|\\]|\"", "").split(",") ,
        spell_resistance(i).toString.toBoolean,
        lev,
        school(i).toString.replaceAll("\"", ""),
        description(i).toString.replaceAll("\"", ""),
        names(i).toString.replaceAll("\"", "")
      )
      listResult = s::listResult
    }
    return listResult
  }

  
}

