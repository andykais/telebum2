package graphql

import io.circe.Json
import sangria.execution._
import sangria.execution._
import sangria.execution.{ErrorWithResolver, Executor, QueryAnalysisError}
import sangria.marshalling.circe._
import sangria.schema._
import sangria.schema.Schema
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import scala.concurrent.Future

object GraphQlQueryExecutor {
  def execute[C](schema: Schema[C, Unit], query: GraphQlQuery,  rootContext: C): Future[Json] = {
    Executor.execute(
      schema = schema,
      queryAst = query.document,
      userContext = rootContext,
      operationName = query.operationName
    )
      .map(json => json)
      .recover {
        case error: QueryAnalysisError => (error.resolveError) // BadRequest
        case error: ErrorWithResolver => (error.resolveError)  // InternalError
      }
  }
}
