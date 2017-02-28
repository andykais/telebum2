organization := "andykais"
name := "telebum-api"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.1"

val http4sVersion = "0.15.5a"
val doobieVersion = "0.4.1"
//val scalazVersion = "7.2"


mainClass in Compile := Some("server.TelebumApi")

// server dependencies
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-dsl"          % http4sVersion,
  "org.http4s" %% "http4s-argonaut"     % http4sVersion,
  "org.http4s" %% "http4s-circe"        % http4sVersion
  //// Optional for auto-derivation of JSON codecs
  //"io.circe"   %% "circe-generic"       % "0.6.1",
  //// Optional for string interpolation to JSON model
  //"io.circe"   %% "circe-literal"       % "0.6.1"
)

// database dependencies
libraryDependencies ++= Seq(
  //"org.scalaz"    %% "scalaz-core"       % scalazVersion,
  //"org.scalaz"    %% "scalaz-concurrent" % scalazVersion,
  "org.specs2"   %% "specs2-core"           % "3.8.6",
  "org.tpolecat" %% "doobie-specs2-cats"    % doobieVersion,
  "org.tpolecat" %% "doobie-core-cats"      % doobieVersion,
  "org.tpolecat" %% "doobie-postgres-cats"  % doobieVersion,
  "org.tpolecat" %% "doobie-scalatest-cats" % doobieVersion
)

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"

