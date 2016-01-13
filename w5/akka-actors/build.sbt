scalaVersion :="2.11.7"

libraryDependencies := Seq("com.typesafe.akka" %% "akka-actor" % "2.4.1")


name := "actors"

version := "1.0"

fork in run := true

connectInput in run := true

