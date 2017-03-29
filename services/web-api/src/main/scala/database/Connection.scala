package database

import doobie.imports._
//import cats._, cats.data._, cats.implicits._
//import fs2.interop.cats._
import scalaz._, Scalaz._
import scalaz.concurrent.Task
import scalaz.stream._


object Connection {

  val xa = DriverManagerTransactor[Task](
    "org.postgresql.Driver",
    "jdbc:postgresql://postgres:5432/telebum",
    "postgres",
    "test"
  )
  case class SimpleNumber(n: Int)
  case class Name(first: String, last: String)
  case class Country(code: String, name: String, population: Int, gnp: Option[Double])

  def fourtyTwo: ConnectionIO[SimpleNumber] = {
    sql"select 42"
      .query[SimpleNumber]
      .unique
  }

  def getNameProcess: Process[Task, Name] = {
    sql"SELECT first, last FROM names"
      .query[Name]
      .process
      .transact(xa)
  }

  def getNameList: Task[List[Name]] = {
    sql"SELECT first, last FROM names"
      .query[Name]
      .list
      .transact(xa)
  }

}
