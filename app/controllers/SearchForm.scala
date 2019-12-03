package controllers

object SearchForm {
  import play.api.data.Forms._
  import play.api.data.Form
  case class Data(opt1: String, opt2: String, opt3: Int, opt4: String)

  /**
   * The form definition for the "create a widget" form.
   * It specifies the form fields and their types,
   * as well as how to convert from a Data to form data and vice versa.
   */
  val form = Form(
    mapping(
      "opt1" -> nonEmptyText,
      "opt2" -> nonEmptyText,
      "opt3" -> number(min = 1),
      "opt4" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )
}