import sbt._

object Dependencies {

  object Version {
    val scala = "2.12.9"

    val cats = "2.0.0"
    val catsEffect = "2.0.0"

    val logback = "1.2.3"
    val log4cats = "1.0.0"
    val conduction = "0.2.1"
    
    val minitest = "2.7.0"
    val scalacheck = "1.14.0"
  }

  val cats = "org.typelevel" %% "cats-core" % Version.cats
  val catsEffect = "org.typelevel" %% "cats-effect" % Version.catsEffect

  val logback = "ch.qos.logback" % "logback-classic" % Version.logback
  val log4catsSlf4j = "io.chrisdavenport" %% "log4cats-slf4j" % Version.log4cats
  val conduction = "com.github.leigh-perry" %% "conduction" % Version.conduction

  val minitest = "io.monix" %% "minitest" % Version.minitest
  val minitestLaws = "io.monix" %% "minitest-laws" % Version.minitest
  val scalacheck = "org.scalacheck" %% "scalacheck" % Version.scalacheck
  val catsLaws = "org.typelevel" %% "cats-laws" % Version.cats

  val scalaReflect = "org.scala-lang" % "scala-reflect" % Version.scala
}
