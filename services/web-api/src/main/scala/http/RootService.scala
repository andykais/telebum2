package server

import org.http4s._
import org.http4s.server._
import org.http4s.dsl._
import _root_.argonaut._, Argonaut._
//import org.http4s.argonaut._

import org.http4s.circe._
import io.circe._
import io.circe.literal._
import io.circe.syntax._
import io.circe.generic.auto._


import doobie.imports._

import database.Connection


object RootService {
  //implicit def circleJsonEncoder[A](implicit encoder: Encoder[A]) =
    //jsonEncoderOf[A]

  case class Hello(name: String)
  case class User(name: String)

  //implicit val HelloEncoder: Encoder[Hello] =
  //Encoder.instance { hello: Hello => 
    //json"""{"hello": ${hello.name}}"""
  //}
  //Hello("bob").asJson


  val service = HttpService {
    case GET -> Root =>
    {
      println("starting")
      val a = Connection.fourtyTwo.transact(Connection.xa)
      //a.unsafePerformIO

      println("a")
      println(a)
      //Ok(a)
      Ok("test")
    }
  }
}
