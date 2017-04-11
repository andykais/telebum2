package server

import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.Request
import org.http4s.Response
import org.http4s.{ParseFailure}
import sangria.renderer.QueryRenderer
import scalaz.concurrent.Task

import graphql.GraphQlQueryDecoder.requestToQuery
import graphql.GraphQlQueryExecutor.execute
import api.RootSchema.schema
import api.Context.NameRepo

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
          val renderConfig = QueryRenderer.Pretty.copy(renderComments = false)
          println(QueryRenderer.render(graphqlQuery.document, renderConfig))

          val executorFuture = execute(schema, graphqlQuery, new NameRepo)
          Ok(executorFuture)
        }
    })
  }
}
