package api.show

import sangria.macros.derive._
import sangria.schema._

object Schema {
  case class TVSeries(
    seriesid: Option[String],
    seriesname: Option[String],
    genre: Option[String],
    rating: Option[String],
    imdb_id: Option[String]
  )

  case class TVSeason(
    seasonid: String,
    season: String
    )
}


object Arguments {
  val TVSeriesName = Argument("name", StringType, description = "name of television show")
}

object Fragments {
  import Schema._

  val TVSeriesType = deriveObjectType[Unit, TVSeries](
    DocumentField("seriesid", "id related to the series"),
    DocumentField("seriesname", "name of television show"),
    DocumentField("genre", "genre of the show"),
    DocumentField("rating", "tv guide ratings (TV-PG, TV-MA, etc)")
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
      resolve = (ctx:Context[Repository, Unit]) => ctx.ctx.searchTVSeries(ctx.arg(TVSeriesName)))
    )
}
