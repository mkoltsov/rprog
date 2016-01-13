import akka.actor.Actor

class Counter extends Actor {
    var count =0 

    def receive = {
	case "incr" => count+=1
	case "get" => sender ! count
    }
}

import  akka.actor.Props

object Hello extends App with Actor{

  override def main(args: Array[String]): Unit = {
    val counter = context.actorOf(Props[Counter], "counter")

    counter ! "incr"
    counter ! "incr"
    counter ! "get"
  }

    def receive = {
	case count:Int => println(s"count was $count")
	context.stop(self)
    }
}