package server

import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.Request
import org.http4s.Response
import org.http4s.{ParseFailure}
import sangria.renderer.QueryRenderer
import sangria.schema._
import scalaz.concurrent.Task

import graphql.GraphQlQueryDecoder.requestToQuery
import graphql.GraphQlQueryExecutor.execute


// data
case class Person(
  id: String,
  first: String,
  last: String
)

class NameRepo {
  import NameRepo._

  def getPerson(id: String): Option[Person] = names.find(c => c.id == id)
}

object NameRepo {
  val names = List(
    Person(
      id = "1",
      first = "andrew",
      last  = "kaiser"
    ),
  Person(
    id = "2",
    first = "vijay",
    last = "nambir"
  )
)
}



object Controller {
  // schema
  //val PersonType = deriveObjectType[Unit, Person](
  //ObjectTypeName("Person"),
  //ObjectTypeDescription("A person in db"),
  //DocumentField("first", "first name"),
  //DocumentField("last", "last name"))
  val PersonType =
    ObjectType(
      "Person",
      "a person with data",
      fields[NameRepo, Person](
        Field("id", StringType,
          Some("id of person"),
          resolve = _.value.id),
        Field("first", StringType,
          Some("persons first name"),
          resolve = _.value.first),
        Field("last", StringType,
          Some("persons last name"),
          resolve = _.value.last)
        )
      )
  val ID = Argument("id", StringType, description = "id of person")

  val Query = ObjectType(
    "Query", fields[NameRepo, Unit](
      Field("person", OptionType(PersonType),
        arguments = ID :: Nil,
        resolve = (ctx) => ctx.ctx.getPerson(ctx.arg(ID)))
      )
    )
  val schema = Schema(Query)

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
          println(QueryRenderer.render(graphqlQuery.document))

          val executorFuture = execute(schema, graphqlQuery, new NameRepo)
          Ok(executorFuture)
        }
    })
  }
}
