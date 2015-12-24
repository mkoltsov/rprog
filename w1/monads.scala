trait M[T] {
	def flatMap[U](f:T => M[U]):M[U] //can be also called bind
}

def unit[T](x:T):M[T]

// Three laws exist to prove that a structure is a monad:

//1) Associativity m flatMap f flatMap g == m flatMap (x=> f(x) flatMap g)

//2) Left unit unit(x) flatMap f == f(x)

//3) Right unit m flatMap unit == m

abstract class Try[+T]

case class Success[T](x:T) extends Try[T]
case class Failure[T](ex:Exception) extends Try[Nothing]