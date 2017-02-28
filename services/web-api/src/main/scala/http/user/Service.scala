package server.user

import org.http4s._
import org.http4s.server._
import org.http4s.dsl._

import _root_.argonaut._, Argonaut._
import org.http4s.argonaut._

object UserService {
  val service = HttpService {
    case GET -> Root => Ok("user api")
    case GET -> Root / name => Controller.index(name)
    case POST -> Root/ name => Controller.addUser
    //case GET -> Root / "hello" / name =>
      //Ok(jSingleObject("message", jString(s"Hello, ${name}")))
  }
}
