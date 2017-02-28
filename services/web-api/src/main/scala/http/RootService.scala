package server

import org.http4s._
import org.http4s.server._
import org.http4s.dsl._

import _root_.argonaut._, Argonaut._
import org.http4s.argonaut._

object RootService {
  val service = HttpService {
    case GET -> Root =>
      Ok("telebum api")
  }
}
