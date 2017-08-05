name := "Flappy bird bot"

version := "0.1"

scalaVersion := "2.12.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

// Java then Scala for main sources
compileOrder in Compile := CompileOrder.JavaThenScala

mainClass in Compile := Some("eu.erdin.scala.flappy.Main")