package api.user


object UserSchema {

  case class Name(first: String, last: String)

  import sangria.schema._
  import sangria.macros.derive._
  implicit val NameType =
    deriveObjectType[Unit, Name](
      ObjectTypeDescription("The product picture"))

  trait Identifiable {
    def id: String
  }
  val IdentifiableType = InterfaceType(
    "Identifiable",
    "Entity that can be identified",
    fields[Unit, Identifiable](
      Field("id", StringType, resolve = _.value.id)))
  case class User(id: String) extends Identifiable {
    def name(): Name =
      Name(first = "andrew", last = "kaiser")
  }
  val ProductType =
    deriveObjectType[Unit, User](
      Interfaces(IdentifiableType),
      IncludeMethods("name"))


  class NameRepo {
    private val Names = List(
      Name("andrew", "kaiser"),
      Name("vijay", "nambir"))

    def name(first: String): Option[Name] =
      Names find (_.first == first)
  }
  val First = Argument("first", StringType)
  val QueryType = ObjectType("Query", fields[NameRepo, Unit](
    Field("name", OptionType(NameType),
      description = Some("a name"),
      arguments = First :: Nil,
      resolve = c => c.ctx.name(c arg First))
    ))
  val schema = Schema(QueryType)
}
