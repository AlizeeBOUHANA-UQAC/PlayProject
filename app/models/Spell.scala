package models

import models.Spell
import java.io.FileInputStream
import scala.io.Source
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsArray, Json}

import com.sun.org.apache.xpath.internal.operations.And
import play.api.libs.json._

/**
 * Presentation object used for displaying data in a template.
 *
 * Note that it's a good practice to keep the presentation DTO,
 * which are used for reads, distinct from the form processing DTO,
 * which are used for writes.
 */
case class Spell(components: Array[String], spell_resistance: Boolean, level: Array[Int], school: String, description: String, name: String){
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

    println("VICTOIRE " + spellReads)

    return spellReads
  }


}

