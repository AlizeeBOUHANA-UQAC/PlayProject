package models

import models.Spell

case class ReqSearch(opt1_nomSpell: String, opt2_class: String, opt3_levelMin: Int, opt4_bool: String){
  //Todo: retourner les Spell resultats
  def createSpellReq(): Spell = {
    return Spell(opt1_nomSpell, opt2_class, 300)
  }

}



