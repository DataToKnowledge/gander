import sbt._

organizationName := "Intent HQ"

organizationHomepage := Some(url("http://www.intenthq.com"))

name := "gander"

description := "Extracts text, metadata from web pages."

homepage := Some(url("https://github.com/intenthq/gander"))

developers := List(
  Developer(id = "albertpastrana", name = "Albert Pastrana", email = "", url = new URL("https://github.com/albertpastrana")),
  Developer(id = "ArturSoler", name = "Artur Soler", email = "", url = new URL("https://github.com/ArturSoler"))
)

licenses += "Apache2" -> url("http://www.apache.org/licenses/")

scalaVersion := "2.10.6"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

Defaults.itSettings

scalacOptions ++= Seq(
  "-Xlint",
  "-Xfatal-warnings",
  "-unchecked",
  "-deprecation",
  "-feature")

testOptions in Test += Tests.Argument("-oF")


libraryDependencies ++= Seq(
  "commons-lang" % "commons-lang" % "2.6",
  "com.google.guava" % "guava" % "18.0",
  "joda-time" % "joda-time" % "2.9.1",
  "junit" % "junit" % "4.12" % Test,
  "org.joda" % "joda-convert" % "1.8.1",
  "org.jsoup" % "jsoup" % "1.8.3",
  "org.slf4j"	% "slf4j-api"	% "1.7.13",
  "org.specs2" %% "specs2-core" % "3.6.5" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")

