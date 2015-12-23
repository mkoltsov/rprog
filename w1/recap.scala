trait JSON

case class JSeq(elems:List[JSON]) extends JSON

case class JObj(bindings:Map[String, JSON]) extends JSON

case class JNum(num:Double) extends JSON

case class JStr(str:String) extends JSON

case class JBool(b:Boolean) extends JSON

case object JNull extends JSON

val data = JObj(Map( 
	"firstName" -> JStr("John"),
	"lastName" -> JStr("Smith"),
	"address" -> JObj(Map(
		"streetAddress" -> JStr("21 2nd street"),
		"state" -> JStr("NY"),
		"postalCode"-> JNum(10021))),
	"phoneNumbers" -> JSeq(List(
		JObj(Map(
			"type" -> JStr("home"),
			"number" -> JStr("212 555-1234")
			)),
		JObj(Map(
			"type" -> JStr("work"),
			"number" -> JStr("111 555-1234")
			))
		))
	))

def show(json:JSON):String = json match {
	case JSeq(elems) => s"[${(elems map show mkString ",")}]"
	case JObj(bindings) => val assocs = bindings map {
		case (key, value) => s"${'"'}${key}${'"'}:${show(value)}"
	}
	s"{${assocs mkString ","}}"
	case JNum(num) => num.toString
	case JStr(str) => str
	case JBool(b ) => b.toString
	case JNull => "null"   
}

println(show(data))

val foo:String =>String  =  {case  "ping" => "pong"}
val f:PartialFunction[String,String]  =  {case  "ping" => "pong"}

println(f("ping"))
println(f.isDefinedAt("ping"))
println(f.isDefinedAt("abc "))

val fn = (x:Int) => println(s"the number is greater that 100: ${x>100}")

fn(99)
fn(101)