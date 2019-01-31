package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index(): Action[AnyContent] = Action {
    Ok("Hello world")
  }

  def pathVariable(test: String): Action[AnyContent] = Action {
    Ok(s"Some variable: $test")
  }

  def params(someString: String, someInt: Int): Action[AnyContent] = Action {
    Ok(s"Some string: $someString and some int: $someInt")
  }

  def default(someString: String, someInt: Int): Action[AnyContent] = Action {
    Ok(s"Default values for string: $someString and int: $someInt")
  }

  def fixed(fixedString: String): Action[AnyContent] = Action {
    Ok(s"Fixed param: $fixedString")
  }
}
