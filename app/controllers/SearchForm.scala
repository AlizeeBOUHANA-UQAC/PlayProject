package controllers

object SearchForm {
  import play.api.data.Forms._
  import play.api.data.Form
  case class Data(textSearch: String, filedTextSearch: String, levelMin: Int, levelMax: Int)

  /**
   * The form definition for the "create a widget" form.
   * It specifies the form fields and their types,
   * as well as how to convert from a Data to form data and vice versa.
   */
  val form = Form(
    mapping(
      "Text Search" -> text,
      "Field Text Search" -> nonEmptyText,
      "Level min" -> number(min = 0),
      "Level max" -> number(min = 1)
    )(Data.apply)(Data.unapply)
  )
}