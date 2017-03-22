package database

import doobie.imports._
//import cats._, cats.data._, cats.implicits._
//import fs2.interop.cats._
import scalaz._, Scalaz._
import scalaz.concurrent.Task
import scalaz.stream._


object Connection {

  val xa: Transactor[Task] = DriverManagerTransactor[Task](
    "org.postgresql.Driver",
    "jdbc:postgresql://postgres:5432/telebum",
    "postgres",
    "test"
  )
  val xtemp = DriverManagerTransactor[IOLite](
    "org.postgresql.Driver",
    "jdbc:postgresql://postgres:5432/telebum",
    "postgres",
    "test"
  )
  //def main(args: Array[String]): Unit = {
  //def fourtyTwo(): scalaz.concurrent.Task[org.http4s.Response] = {
  //def fourtyTwo: Process[ConnectionIO, Int] =
  def fourtyTwo: ConnectionIO[Int] = {
    val prog = sql"select 34"
      .query[Int]
      .unique

      val task = prog.transact(xtemp)
      task.unsafePerformIO

      sql"select 42"
        .query[Int]
        .unique
        //.process
  }
  }
