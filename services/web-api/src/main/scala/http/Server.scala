package server

import java.util.concurrent.{ExecutorService, Executors}
import scala.util.Properties.envOrNone
import scalaz.concurrent.Task
import org.http4s.server.{Server, ServerApp, Router}
import org.http4s.server.blaze.BlazeBuilder

import server.user.UserService
import server.show.ShowService


object TelebumApi extends ServerApp {

  val port : Int              = envOrNone("WEB_API_PORT") map (_.toInt) getOrElse 3001
  val ip   : String           = "0.0.0.0"
  val pool : ExecutorService  = Executors.newCachedThreadPool()

  val services = Router(
    "/"     -> RootService.service,
    "/user" -> UserService.service,
    "/show" -> ShowService.service
  )


  override def server(args: List[String]): Task[Server] =
    BlazeBuilder
      .bindHttp(port, ip)
      .mountService(services, "/")
      .withServiceExecutor(pool)
      .start
}
