package websockets

import akka.actor._

import akka.actor.{ActorRef, ActorSystem}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object MyWebSocketActor {
  def props(out: ActorRef, actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) = Props(new MyWebSocketActor(out, actorSystem))
}

class MyWebSocketActor(out: ActorRef, actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) extends Actor {
  def receive: PartialFunction[Any, Unit] = {
    case msg: String =>
      actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 10.seconds) {
        out ! ("I received your message: " + msg)
      }
  }
}