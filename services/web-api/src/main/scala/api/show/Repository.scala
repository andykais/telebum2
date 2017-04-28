package api.show

import doobie.imports._
import sangria.macros.derive._
import scalaz.concurrent.Task

import database.Connection.xa
import Schema.TVSeries
import Schema.TVSeason
import Schema.TVEpisode

object Repository {
  def searchTVSeriesByName(name: String) = {
    val searchString = s"%$name%"
    sql"""SELECT id, seriesid, seriesname, genre, rating, imdb_id FROM tvseries WHERE seriesname LIKE $searchString"""
      .query[TVSeries]
      .list
      .transact(xa)
      .unsafePerformSync
  }

  def getTVSeriesSeason(seasonid: Int) =
    sql"SELECT id, season FROM tvseasons WHERE seriesid = $seasonid"
      .query[TVSeason]
      .list
      .transact(xa)
      .unsafePerformSync


  def getTVSeriesSeasonEpisodes(seasonid: Int) =
    sql"SELECT id, episodenumber, episodename, firstaired FROM tvepisodes WHERE seasonid = $seasonid"
      .query[TVEpisode]
      .list
      .transact(xa)
      .unsafePerformSync

}
