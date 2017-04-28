package api.show

import doobie.imports._
import sangria.macros.derive._
import scalaz.concurrent.Task

import database.Connection.xa
import Schema.TVSeries

class Repository {
  def searchTVSeries(name: String) = {
    sql"""SELECT seriesid, seriesname, genre, rating, imdb_id FROM tvseries WHERE seriesname LIKE '%$name%'"""
    .query[TVSeries]
    .list
    .transact(xa)
    .unsafePerformSync
  }
}

object ShowRepository {
}
