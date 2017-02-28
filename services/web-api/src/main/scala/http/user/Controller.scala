package server.user

import org.http4s._
import org.http4s.server._
import org.http4s.dsl._
import scalaz.concurrent.Task


object Controller {
  def index(name: String): Task[org.http4s.Response] = {
    Ok(s"$name controller")
  }

  def addUser(): Task[org.http4s.Response] = {
    Ok("user controller")
  }
}
