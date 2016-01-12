import rx.lang.scala.Observable

object Hello extends App{

  def hello(names: String*) {
    Observable.from(names) subscribe { n =>
      println(s"Hello $n!")
    }
  }
}