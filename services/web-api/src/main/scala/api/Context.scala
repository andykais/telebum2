package api

import RootSchema.Person

object Context {
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
}
