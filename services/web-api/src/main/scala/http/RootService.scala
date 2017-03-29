package server

import org.http4s._
import org.http4s.server._
import org.http4s.dsl._

import org.http4s.circe._
import io.circe.syntax._
import io.circe.generic.auto._


import doobie.imports._

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
  }
}
