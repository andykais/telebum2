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
  import show.Repository.searchTVSeriesByName
  def searchTVSeries(name: String) = searchTVSeriesByName(name)


  import show.Repository.getTVSeriesSeason
  def getSeasons(id: Int) = getTVSeriesSeason(id)

  import show.Repository.getTVSeriesSeasonEpisodes
  def getEpisodes(id: Int) = getTVSeriesSeasonEpisodes(id)

}

object Repository {
}
