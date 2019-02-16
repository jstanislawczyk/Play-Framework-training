package controllers

import javax.inject._
import model.UserRepository
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext
import play.api.mvc._
import akka.stream.scaladsl._
import play.api.libs.streams.ActorFlow
import akka.actor.ActorSystem
import akka.stream.Materializer
import websockets.MyWebSocketActor

@Singleton
class HomeController @Inject()(repository: UserRepository, cc: MessagesControllerComponents)
                              (implicit ec: ExecutionContext, system: ActorSystem, mat: Materializer) extends MessagesAbstractController(cc) {

  def socket(): WebSocket = WebSocket.accept[String, String] { accept =>
    println(s"SOCKET $accept")

    val in = Sink.foreach[String](println)

    val out = Source.single("Hello Test!").concat(Source.maybe)

    Flow.fromSinkAndSource(in, out)
  }

  def socketActor(): WebSocket = WebSocket.accept[String, String] { _ =>
    ActorFlow.actorRef { out =>
      MyWebSocketActor.props(out)
    }
  }

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

  def testFind: Action[AnyContent] = Action.async { implicit request =>
    repository.list().map { people =>
      Ok(Json.toJson(people))
    }
  }

  def testSave: Action[AnyContent] = Action {
    repository.create("test3", "test2")
    Ok("created")
  }
}
