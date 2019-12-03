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


case class ReqSearch(textSearch: String, fieldTextSearch: String, levelMin: Int, levelMax: Int){




}



