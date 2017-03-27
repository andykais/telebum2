package database

import doobie.imports._
//import cats._, cats.data._, cats.implicits._
//import fs2.interop.cats._
import scalaz._, Scalaz._
import scalaz.concurrent.Task
import scalaz.stream._


object Connection {

  //val xa: Transactor[Task] = DriverManagerTransactor[Task](
  val xa = DriverManagerTransactor[Task](
    "org.postgresql.Driver",
    "jdbc:postgresql://postgres:5432/telebum",
    "postgres",
    "test"
  )
  //def fourtyTwo(): scalaz.concurrent.Task[org.http4s.Response] = {
  //def fourtyTwo: Process[ConnectionIO, Int] =
  case class SimpleNumber(n: Int)
  case class TupleNumber(n: Int, r: Double)
  case class Name(first: String)

//  def nRandom: ConnectionIO[TupleNumber] = {
//    for {
//      a <- sql"select 42".query[Int].unique
//      b <- sql"select random()".query[Double].unique
//    } yield (a, b)
//  }
  def fourtyTwo: ConnectionIO[SimpleNumber] = {

    sql"select 42"
      .query[SimpleNumber]
      .unique
  }

  def getArray: ConnectionIO[List[Name]] = {

    val n = 4
    sql"SELECT first FROM name"
      .query[Name]
      .list
  }
  }
