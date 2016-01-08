trait Publisher {
	private var subscribers:Set[Subscriber] = Set()

	def subscribe(subscriber:Subscriber):Unit = 
		subscribers += subscriber

	def unsubscribe(subscriber:Subscriber):Unit = 
		subscribers -= subscriber

	def publish():Unit = 
		subscribers.foreach(_.handler(this))			
}

trait Subscriber {
	def handler(pub:Publisher)
}

class BankAccount extends Publisher {
	private var balance = 0
	def currentBalance = balance
	def deposit(amount:Int):Unit = 
		if (amount>0) {
			balance = balance+amount 
			publish()
		}
	def with	
}

clas Consolidator(observed:List[BankAccount]) extends Subscriber {
	observed.foreach(_.subscribe(this))

	private var total:Int = _
	compute()

	private def compute() = 
}