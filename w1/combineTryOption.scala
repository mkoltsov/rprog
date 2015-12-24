def some[T](t:T):Option[T] = Option[T](t)
def some2[T](t:T):Option[T] = Option[T](t)

val compute1 = some(None)
val compute2 = some("chef")

println(for {
	x <- compute1
	y <- compute2
} yield (x,y))


def try[T](t:T):Try[T] = Option[T](t)
def try2[T](t:T):Option[T] = Option[T](t)

val compute1 = some(None)
val compute2 = some("chef")

println(for {
	x <- compute1
	y <- compute2
} yield (x,y))