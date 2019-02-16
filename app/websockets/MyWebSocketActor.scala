package websockets

import akka.actor._

object MyWebSocketActor {
  def props(out: ActorRef) = Props(new MyWebSocketActor(out))
}

class MyWebSocketActor(out: ActorRef) extends Actor {
  def receive: PartialFunction[Any, Unit] = {
    case msg: String =>
      if(msg.equals("test"))  out ! ("I received your message: " + msg)
  }
}