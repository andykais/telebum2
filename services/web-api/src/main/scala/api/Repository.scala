package api

import doobie.imports._
import scalaz.concurrent.Task

import RootSchema.Person

class Repository(xa: Transactor[Task]) {
  def getPerson(id: Int) = {
    sql"""SELECT id, first, last FROM users WHERE id = $id"""
      .query[Person]
      .unique
      .transact(xa)
      .unsafePerformSync

  }
}

object Repository {
}
