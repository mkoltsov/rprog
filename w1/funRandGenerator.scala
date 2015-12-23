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
  def run() = println(integers.generate) 
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