package api

import sangria.schema._

import Context.NameRepo

object RootSchema {
  // data
  case class Person(
    id: String,
    first: String,
    last: String
  )


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
}
