package server

import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.Request
import org.http4s.Response
import org.http4s.{ParseFailure}
import scalaz.concurrent.Task

import database.Connection.xa
import api.Repository
import api.RootSchema.schema
import graphql.GraphQlQueryDecoder.requestToQuery
import graphql.GraphQlQueryExecutor.execute

object Controller {

  // all graphql schemas start here
  def rootSchema(request: Request): Task[Response] = {

    import scala.concurrent.ExecutionContext
    import ExecutionContext.Implicits.global

    requestToQuery(request).flatMap(_ match {
      case Left(error) => error match {
        case ParseFailure(message, _) => BadRequest(message)
        case _ => InternalServerError()
      }
        case Right(graphqlQuery) => {
          // print query to console
          //import sangria.renderer.QueryRenderer
          //val renderConfig = QueryRenderer.Pretty.copy(renderComments = false)
          //println(QueryRenderer.render(graphqlQuery.document, renderConfig))

          val executorFuture = execute(schema, graphqlQuery, new Repository(xa))
          Ok(executorFuture)
        }
    })
  }
}
