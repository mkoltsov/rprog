import rx.lang.scala.Observable
import scala.concurrent.duration._

object Hello extends App{
type Ob = Observable[Int]
  override def main(args: Array[String]): Unit = {
    val xs:Ob = Observable.from(List(3,2,1))
    val yss:Observable[Ob] = xs.map(x=> Observable.interval(x seconds).map(_=> x).take(2))
    val zs:Ob = yss.flatten

    val s = zs.subscribe(println(_))
  }
}