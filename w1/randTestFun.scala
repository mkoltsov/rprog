trait Generator[+T] {
	self => //an alias for this
	def generate:T
	
	def map[S](f:T => S):Generator[S] = new Generator[S] {
		def generate = f(self.generate)
	}


	def flatMap[S](f:T => Generator[S]):Generator[S] = new Generator[S] {
		def generate = f(self.generate).generate
	}
}

def test[T](g:Generator[T], numTimes:Int=100)(test: T=> Boolean):Unit = {
	for (i <- 0 until numTimes) {
		val value = g.generate
		assert(test(value), s"test failed for value ${value}")
	}
	println(s"passed ${numTimes} tests")
}

 val integers = new Generator[Int] {
 	val rand = new java.util.Random
 	def generate = rand.nextInt()
 }

val booleans = for( x <- integers) yield x>0 

def single[T](x:T):Generator[T] = new Generator[T] {
	def generate = x
}

def choose(lo:Int, hi:Int):Generator[Int] = 
	for( x <- integers) yield lo + x % (hi-lo)

def oneOf[T](xs:T*):Generator[T] = for( idx <- choose(0, xs.length)) yield xs(idx)

def lists:Generator[List[Int]] = for {
	isEmpty <- booleans
	list <-  if (isEmpty) emptyLists else nonEmptyLists
} yield list

def emptyLists = single(Nil)

def nonEmptyLists = for {
	head <- integers
	tail <- lists
} yield head :: tail

 def pairs[T,U](t:Generator[T], u:Generator[U]):Generator[(T,U)] = for {
 	x <- t
 	y <- u
 } yield(x, y)

test(pairs(lists, lists)) {
	case (xs, ys) => (xs ++ ys).length > xs.length 
}