organization := "andykais"
name := "telebum-api"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.1"

val http4sVersion = "0.15.5a"
val circleVersion = "0.6.1"
val doobieVersion = "0.4.0"


mainClass in Compile := Some("server.TelebumApi")
fork in run := true
cancelable in Global := true

libraryDependencies ++= Seq(
  // http server dependencies
  "org.http4s"          %% "http4s-dsl"          % http4sVersion,
  "org.http4s"          %% "http4s-blaze-server" % http4sVersion,
  "org.http4s"          %% "http4s-blaze-client" % http4sVersion,
  // json dependencies
  "org.http4s"          %% "http4s-circe"        % http4sVersion,
  "io.circe"            %% "circe-generic"       % circleVersion, // Optional for auto-derivation of JSON codecs
  "io.circe"            %% "circe-literal"       % circleVersion, // Optional for string interpolation to JSON model
  // graphql dependencies
  "org.sangria-graphql" %% "sangria"             % "1.1.0",
  "org.sangria-graphql" %% "sangria-circe"       % "1.0.1",
  // database dependencies
  "org.specs2"          %% "specs2-core"         % "3.8.6", // checks sql syntax at compile time
  "org.tpolecat"        %% "doobie-specs2"       % doobieVersion,
  "org.tpolecat"        %% "doobie-core"         % doobieVersion,
  "org.tpolecat"        %% "doobie-postgres"     % doobieVersion,
  "org.tpolecat"        %% "doobie-scalatest"    % doobieVersion
)

libraryDependencies += "org.typelevel" %% "cats-core" % "0.9.0"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"

//scalacOptions ++= Seq(
  //"-unchecked",
  //"-deprecation",
  //"-feature",
  //"-Xfatal-warnings",
  //"-Xlint",
  //"-Ywarn-unused-import"
//)
maxErrors := 5
triggeredMessage := Watched.clearWhenTriggered
