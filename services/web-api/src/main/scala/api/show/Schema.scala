package api.show

import sangria.macros.derive._
import sangria.schema._


object Schema {
  import Repository._

  case class TVSeries(
    id: Int,
    seriesid: Option[String],
    seriesname: Option[String],
    genre: Option[String],
    rating: Option[String],
    imdb_id: Option[String]
  )
  case class TVSeason(
    id: Int,
    season: Int
  )

  case class TVEpisode(
    id: Int,
    episodenumber: Int,
    episodename: Option[String],
    firstaired: Option[String]
  )
}


object Arguments {
  val TVSeriesName = Argument("name", StringType, description = "name of television show")

  //val TVSeasonId = Argument("id", IntType, description = "id of television show")
}

object Fragments {
  import Schema._

  val TVEpisodeType = deriveObjectType[Unit, TVEpisode](
    DocumentField("id", "id of episode"),
    DocumentField("episodenumber", "number used to order episodes"),
    DocumentField("episodename", "name episode was released with"),
    DocumentField("firstaired", "date episode was aired")
  )

  val TVSeasonType = deriveObjectType[api.Repository, TVSeason](
    DocumentField("id", "id of season, used to grab episodes"),
    DocumentField("season", "number of the season"),
    AddFields(
      Field("episodes", ListType(TVEpisodeType),
        resolve = (ctx: Context[api.Repository, TVSeason]) => ctx.ctx.getEpisodes(ctx.value.id))
      )
    )

  val TVSeriesType = deriveObjectType[api.Repository, TVSeries](
    DocumentField("seriesid", "id related to the series"),
    DocumentField("seriesname", "name of television show"),
    DocumentField("genre", "genre of the show"),
    DocumentField("rating", "tv guide ratings (TV-PG, TV-MA, etc)"),
    AddFields(
      Field("seasons", ListType(TVSeasonType),
        resolve = (ctx: Context[api.Repository, TVSeries]) => ctx.ctx.getSeasons(ctx.value.id)
        )
      )
    )
}

object Fields {
  import Schema._
  import Fragments._
  import Arguments._

  val TVApiType = ObjectType(
    "TVSeriesApi", "tv show related queries",
    fields[api.Repository, Unit](
      Field("series", ListType(TVSeriesType),
        arguments = TVSeriesName :: Nil,
        resolve = ctx => ctx.ctx.searchTVSeries(ctx.arg(TVSeriesName)))
      )
    )
  //fieldList: _*
  val fieldList = List(
    Field("series", ListType(TVSeriesType),
      arguments = TVSeriesName :: Nil,
      resolve = (ctx:Context[api.Repository, Unit]) => ctx.ctx.searchTVSeries(ctx.arg(TVSeriesName)))
    )
}
