package api

import doobie.imports._
import scalaz.concurrent.Task

import database.Connection.xa
import RootSchema.Person

class Repository {
  def getPerson(id: Int) = {
    sql"""SELECT id, first, last FROM users WHERE id = $id"""
      .query[Person]
      .unique
      .transact(xa)
      .unsafePerformSync

  }
  import show.Schema.TVSeries
  def searchTVSeries(name: String) = {
    val searchString = s"%$name%"
    //sql"""SELECT id FROM tvseries WHERE seriesname LIKE '%$name%'"""
    sql"""SELECT seriesid, seriesname, genre, rating, imdb_id FROM tvseries WHERE seriesname LIKE $searchString"""
    .query[TVSeries]
    .list
    .transact(xa)
    .unsafePerformSync
  }

  import show.Schema.TVSeason
  def getSeasons(id: Int) = {
    sql"SELECT season FROM tvseasons WHERE seriesid = $id"
    .query[TVSeason]
    .list
    .transact(xa)
    .unsafePerformSync
  }
}

object Repository {
}
