package models

import models.Spell
import java.io.FileInputStream
import scala.io.Source

import com.sun.org.apache.xpath.internal.operations.And
import play.api.libs.json._


case class ReqSearch(textSearch: String, fieldTextSearch: String, levelMin: Int, levelMax: Int){
  //Todo: retourner les Spell resultats
  def createSpellReq(): Spell = {
    val stream = new FileInputStream("Spells_TestBig.json")
    val json = try {
      Json.parse(stream)
    } finally {
      stream.close()
    }


    implicit val spellReads: Reads[Spell] = (
      (JsPath \ "textSearch").read[String] &&
        (JsPath \ "width").read[String] &&
        (JsPath \ "height").read[Int]
      )(Spell.apply _)

    println("VICTOIRE " + json)




    return Spell(textSearch, fieldTextSearch, levelMin)
  }


}



