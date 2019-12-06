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

import scala.util.matching.Regex


case class ReqSearch(textSearch: String, fieldTextSearch: String, levelMin: Int, levelMax: Int){

  def resultReq(allSpells: List[Spell]): List[Spell] ={
    var spellFilter: List[Spell] = List[Spell]()

    for(spell <- allSpells){
      if(testSpell(spell)){
        spellFilter = spell::spellFilter
      }
    }

  return spellFilter
  }

  def testSpell(spell: Spell): Boolean ={

    // Text Fiilter
    if(!textSearch.isEmpty){
      val pattern = new Regex("(?i)("+textSearch+ ".*)")

      if(fieldTextSearch.equals("creature")) {
        return false
      } else if(fieldTextSearch.equals("spell1")) {
        if(!spell.name.toLowerCase().contains(textSearch.toLowerCase())){ // pas de correspondance
          println("false 1")
          return false
        }
      } else if(fieldTextSearch.equals("spell2")) {
        if(!(spell.description match {case pattern(_*) => true})){ // pas de correspondance
          println("false 2")
          return false
        }
      }else if(fieldTextSearch.equals("all")) {
        if(!((spell.name + " " + spell.description) match {case pattern(_*) => true})){ // pas de correspondance
          println("false 3")
          return false
        }
      }

    }


    // Level Filter
    if(levelMin > levelMax){
      println("false 4")
      return false
    }
    if(spell.level < levelMin || spell.level > levelMax) {
      println("false 5")
      return false
    }



    return true

  }



}



