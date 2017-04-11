package server.show

import org.http4s._
import org.http4s.dsl._

object ShowService {
  val service = HttpService {
    case GET -> Root =>
      Ok("show api")
  }
}
