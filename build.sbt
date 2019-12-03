name := "PlayProject"

version := "1.0"

lazy val playproject = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.7"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.7"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.7"


libraryDependencies ++= Seq(
  jdbc,
  ehcache,
  ws,
  specs2 % Test,
  guice,
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "org.apache.spark" %% "spark-mllib" % "2.4.0",
  "org.apache.spark" %% "spark-streaming" % "2.4.0"
)