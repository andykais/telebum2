package server

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import scalaz.concurrent.Task

//import doobie.imports._

import database.Connection


object RootService {

  val service = HttpService {
    case GET -> Root =>
    {
      //val task = Connection.getNameList
      //Ok(task.map(_.asJson))
      val process = Connection.getNameProcess
      Ok(process.map(_.asJson))

    }

    case request @ POST -> Root / "graphql" => Controller.rootSchema(request)

    case request @ GET -> "graphiql" /: path =>
    {
      val filePath = "/graphiql" + (
        path.toString match {
          case "/" => "/index.html"
          case _        => path.toString
        }
      )
      StaticFile.fromResource(filePath, Some(request)).fold(NotFound())(Task.now)
    }



          case _ -> Root => NotFound()


  }
}
