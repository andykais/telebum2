package api.auth

import org.http4s._
import org.http4s.dsl._

object AuthService {
  val service = HttpService {
    //case GET -> Root => Ok("user api")
    case GET -> Root => Ok("user api")
    case GET -> Root / name => Controller.index(name)
    case POST -> Root/ name => Controller.addUser
    //case GET -> Root / "hello" / name =>
      //Ok(jSingleObject("message", jString(s"Hello, ${name}")))
  }
}
