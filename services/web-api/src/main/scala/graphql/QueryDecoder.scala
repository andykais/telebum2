package graphql

import org.http4s.{ Request, EntityDecoder }
import cats.syntax.either._
import io.circe._
import org.http4s.circe._
import sangria.parser.QueryParser
import io.circe.generic.auto._
import io.circe.Json
import sangria.ast.Document
import scalaz.concurrent.Task
import org.http4s.{ParseFailure}

// json converters
case class RequestQuery(query: String, variables: Option[Json], operationName: Option[String] = None)
object RequestQuery {
  implicit val queryEntityDecoder: EntityDecoder[RequestQuery] = jsonOf[RequestQuery]
}

// class used by sangria
final case class GraphQlQuery(document: Document, variables: Option[Json], operationName: Option[String] = None)


trait GraphQlQueryDecoder {
  val emptyJsonObject: Json = Json.fromJsonObject(JsonObject.empty)

  final def requestToQuery(request: Request): Task[Either[ParseFailure, GraphQlQuery]] = {
    val queryStrings = request.as[RequestQuery]

    queryStrings.map { x =>
      for {
        parsedQuery <- Either.fromTry(QueryParser.parse(x.query.trim)).leftMap(e => parseQueryError(e))
      } yield GraphQlQuery(parsedQuery, x.variables, x.operationName)
    }
  }

  final def parseQueryError(t: Throwable): ParseFailure = ParseFailure(t.getMessage, "")

}

object GraphQlQueryDecoder extends GraphQlQueryDecoder
