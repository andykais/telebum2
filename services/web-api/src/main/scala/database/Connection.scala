package database

import doobie.imports._
import cats._, cats.data._, cats.implicits._
import fs2.interop.cats._

object Connection {
  def main(args: Array[String]): Unit = {

    val program1 = 42.pure[ConnectionIO]

    val xa = DriverManagerTransactor[IOLite](
      "org.postgresql.Driver", "jdbc:postgresql:world", "postgres", ""
    )
    val task = program1.transact(xa)

    task.unsafePerformIO
  }
}
