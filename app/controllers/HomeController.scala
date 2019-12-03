package controllers

import java.util.Calendar

import controllers.SearchForm.{Data, form}
import javax.inject._
import models.{ReqSearch, Spell}
import play.api.data.Form
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc)  {

  private val spells = scala.collection.mutable.ArrayBuffer(
    Spell("Abadar's Truthtelling", "http://www.dxcontent.com/SDB_SpellBlock.asp?SDBID=1725", 1),
    Spell("Ablative Barrier", "http://www.dxcontent.com/SDB_SpellBlock.asp?SDBID=1269", 2),
    Spell("Absorbing Inhalation", "http://www.dxcontent.com/SDB_SpellBlock.asp?SDBID=1526", 4)
  )
  private val postUrl = routes.HomeController.createReqSearch()


  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
    /*
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  */

  def listSearch: Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    Ok(views.html.spellSearchView(spells.toSeq, form, postUrl))
  }

  def createReqSearch: Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Data] =>
      println("false req" + formWithErrors.data)
      BadRequest(views.html.spellSearchView(spells.toSeq, formWithErrors, postUrl))
    }
    val successFunction = { data: Data =>
      val req = ReqSearch(data.textSearch, data.filedTextSearch, data.levelMin, data.levelMax);
      println("VICTOIRE" + req)
      spells.append(req.createSpellReq())
      val now = Calendar.getInstance()
      val currentMinute = now.get(Calendar.MINUTE)

      Redirect(routes.HomeController.listSearch()).flashing("New Search " ->
        (now.get(Calendar.DAY_OF_MONTH) + "/" + now.get(Calendar.MONTH)+1 + "/" + now.get(Calendar.YEAR) + "   " +
          now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND)))
    }

    val formValidationResult = form.bindFromRequest
    formValidationResult.fold(errorFunction, successFunction)
  }

}
