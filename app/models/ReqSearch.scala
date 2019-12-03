package models
import java.io.FileInputStream
import play.api.libs.json._


case class ReqSearch(textSearch: String, fieldTextSearch: String, levelMin: Int, levelMax: Int){
  //Todo: retourner les Spell resultats
  def createSpellReq(): Spell = {
    return Spell(textSearch, fieldTextSearch, levelMin)
  }

  val stream = new FileInputStream("file")
  val json = try {
    Json.parse(stream)
  } finally {
    stream.close()
  }
  println("VICTOIRE " + json)
}



