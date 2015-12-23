 trait Generator[+T] {
 	def generate:T
 }

 val integers = new Generator[Int] {
 	val rand = new java.util.Random
 	def generate = rand.nextInt()
 }

 val booleans = new Generator[Boolean] {
 	def generate = integers.generate>0
 }


 val pairs = new Generator[(Int, Int)] {
 	def generate = (integers.generate, integers.generate)
 }

 println(integers.generate)

 import java.util.concurrent._

val ex = new ScheduledThreadPoolExecutor(4)
val task = new Runnable { 
  def run() = println(s"${Thread.currentThread().getName()} - ${integers.generate}") 
}
val f = ex.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS)
val d = ex.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS)
val g = ex.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS)
val h = ex.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS)
//f.cancel(false)

trait Generator2[+T] {
	self => //an alias for this
	def generate:T
	
	def map[S](f:T => S):Generator2[S] = new Generator2[S] {
		def generate = f(self.generate)
	}


	def flatMap[S](f:T => Generator2[S]):Generator2[S] = new Generator2[S] {
		def generate = f(self.generate).generate
	}
}

val integers2 = new Generator2[Int]{
	val rand = new java.util.Random
 	def generate = rand.nextInt()
}

val booleans2 = for( x <- integers2) yield x>0 

def single[T](x:T):Generator2[T] = new Generator2[T] {
	def generate = x
}

def choose(lo:Int, hi:Int):Generator2[Int] = 
	for( x <- integers2) yield lo + x % (hi-lo)

def oneOf[T](xs:T*):Generator2[T] = for( idx <- choose(0, xs.length)) yield xs(idx)

def lists:Generator2[List[Int]] = for {
	isEmpty <- booleans2
	list <-  if (isEmpty) emptyLists else nonEmptyLists
} yield list

def emptyLists = single(Nil)

def nonEmptyLists = for {
	head <- integers2
	tail <- lists
} yield head :: tail

trait Tree

case class Inner(left:Tree, right:Tree) extends Tree
case class Leaf(x:Int) extends Tree

def leafs:Generator2[Leaf] = for {
	x <- integers2
} yield Leaf(x)

def inners:Generator2[Inner] = for {
	l <- trees
	r <- trees
} yield Inner(l, r)

def trees:Generator2[Tree] = for {
	isLeaf <- booleans2
	tree <- if (isLeaf) leafs else inners
} yield tree

println(trees.generate)

