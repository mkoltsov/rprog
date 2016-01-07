def power(x:Double, exp:Int):Double = { 
	var r = 1.0
	var i = exp
	while(i>0) {r=r*x; i=i-1}
	r
}

// def WHILE(condition:=>Boolean) (command: => Unit):Unit =
// if (condition) {
// 	command
// 	WHILE(condition)(command)
// }
// else ()

// def REPEAT(command: => Unit) (condition: =>Boolean)={
// 	command
// 	if (condition)
// 	else REPEAT(command)(condition)
// }

for (i <- 1 until 3) {println(i)}

(1 until 3).foreach(i=>"abc" foreach (j=> println(s"$i $j")))