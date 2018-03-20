name := "$name;format="lower,hyphen"$"

lazy val commonSettings = Seq(
  organization := "$organization$",
  version := "$version$",
  scalaVersion in ThisBuild := "$scala_version$",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8"),
  javacOptions ++= Seq("-encoding", "UTF-8")
)

lazy val commonResolvers = Seq(
  "Maven Central Proxy" at "http://reaper.birchwoodlangham.com:8081/repository/maven-central/",
  "Sonatype Nexus Releases" at "http://reaper.birchwoodlangham.com:8081/repository/maven-releases/",
  "Sonatype Nexus Snapshots" at "http://reaper.birchwoodlangham.com:8081/repository/maven-snapshots/"
)

val nscalaTimeVersion = "$nscalaTimeVersion$" // https://github.com/nscala-time/nscala-time
val scalaLoggingVersion = "$scalaLoggingVersion$" // https://github.com/typesafehub/scala-logging
val logbackVersion = "$logbackVersion$" // http://logback.qos.ch/
val configVersion = "$configVersion$" // https://github.com/typesafehub/config
val scalaTestVersion = "$scalaTestVersion$" // http://www.scalatest.org/
val scalacticVersion = "$scalacticVersion$" // http://www.scalatest.org/
val akkaVersion = "$akkaVersion$" // http://akka.io/docs

lazy val commonDependencies = Seq(
  "com.github.nscala-time" %% "nscala-time" % nscalaTimeVersion,  // use nscala-time for time manipulation
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion, // use scala-logging and logback for logging
  "ch.qos.logback" % "logback-classic" % logbackVersion, // required by scala-logging
  "com.typesafe" % "config" % configVersion // use typesafe's configuration library for reading configuration files
)

lazy val testDependencies = Seq(
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "org.scalactic" %% "scalactic" % scalaTestVersion
)

lazy val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)

lazy val root = project.in(file("."))
  .settings(commonSettings:_*)
  .settings(
    resolvers ++= commonResolvers,
    libraryDependencies ++= commonDependencies ++ testDependencies ++ akkaDependencies
  )

// the next statuement is required to workaround the issues with publishing to Sonatype Nexus with sbt 1.0.X+
// updateOptions := updateOptions.value.withGigahorse(false)

// this block is only required if you want to publish an artifact to Sonatype Nexus
//
// credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
//
// publishTo := {
//   val nexus = "http://reaper.birchwoodlangham.com:8081/"
//   if (isSnapshot.value)
//     Some("snapshots" at nexus + "repository/maven-snapshots")
//   else
//     Some("releases" at nexus + "repository/maven-releases")
// }
