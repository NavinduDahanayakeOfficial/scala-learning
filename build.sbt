ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "FirstProject"
  )

// Add your project dependencies here
// Example: add JSON library
libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.6"