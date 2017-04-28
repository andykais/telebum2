package api

import sangria.macros.derive._
import sangria.schema._

import show.Fields

object RootSchema {
  case class Person(
    id: Int,
    first: String,
    last: String
  )


  val PersonType = deriveObjectType[Unit, Person](
    DocumentField("first", "what I call me"),
    DocumentField("last", "what I call my family"))

  val ID = Argument("id", IntType, description = "id of person")

  val Query = ObjectType(
    "Query", "the root of all queries",
    fields[Repository, Unit](
      Field("person", OptionType(PersonType),
        arguments = ID :: Nil,
        resolve = ctx => ctx.ctx.getPerson(ctx.arg(ID))
        ),
      //Fields.fieldList: _*
      Field("TVAPI",
        Fields.TVApiType,
        resolve = _ => ())
      )
    )
  val schema = Schema(Query)
}
