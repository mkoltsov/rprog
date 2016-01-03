def iterate(n:Int, f:Int => Int, x:Int):Int = 
	if (n==0) x else iterate(n-1, f, f(x))

def square(x:Int) = x * x

println(iterate(1, square, 3))

var x:String = "Hello"

var count = 111

class BankAccount {
	private var balance = 0

	def deposit(amount:Int): Unit = {
		if (amount > 0) balance = balance + amount
	}

	def withdraw(amount:Int):Int = 
		if (0< amount && amount <= balance) {
			balance = balance - amount
			balance
		} else throw new Error("insufficient funds")
}

val account = new BankAccount

account deposit 50
account withdraw 20
account withdraw 20
account withdraw 15

def cons[T](hd:T, tl: => Stream[T]) = new Stream[T]{
	override def head = hd
	private var tl0pt :Option[Stream[T]] = None
	override def tail:T = tl0pt match {
		case Some(x:T) => x
		case None => tl0pt = Some(tl); tail
	}
	
}

class BackAccountProxy(ba:BankAccount) {
	def deposit(amount:Int):Unit = ba.deposit(amount)
	def withdraw(amount:Int):Int = ba.withdraw(amount)
}