import akka.actor.Actor

class Counter extends Actor {
    var count = 0 

    def receive = {
	case "incr" => count+=1
	case "get" => sender ! count
    }
}

import  akka.actor.Props

class HelloWorld extends Actor {

    val counter = context.actorOf(Props[Counter], "counter")

        counter ! "incr"
        counter ! "incr"
        counter ! "get"

     def receive = {
        case count:Int => println(s"count was $count")
        context.stop(self)
    }
}

object Main {

  def main(args: Array[String]): Unit = {
    akka.Main.main(Array(classOf[HelloWorld].getName))
  }

}