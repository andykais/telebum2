package server

import io.circe.literal._
import io.circe.generic.auto._
import org.http4s.circe._
import org.http4s.client._
import org.http4s.client.blaze._
import org.http4s.dsl._
import scala.concurrent.duration._
import scalaz.concurrent.Task
import scalaz.stream.io._
import scalaz.stream.{ time, Process }

object TVDBApi {
  val httpClient = PooledHttp1Client()
  val apiKey = json"""
    {"apikey": "49D3CC1F3C6665A8"}
  """
  case class JWT(token: String)

  def update(time: String): Task[String] = {
    println("updooting")
    // POST to login
    // make a request?
    getJWT()
      //.map{ x => {
        //println(x.toString)
        //x
      //} }

      //apiRequest.unsafePerformSync
      ???
  }

  private def getJWT(): Task[JWT] = POST(uri("https://api.thetvdb.com/login"), apiKey).as(jsonOf[JWT])

  private def checkForUpdate() {}
}

object Scheduler {

  def start() {
    implicit val S = scalaz.concurrent.Strategy.DefaultTimeoutScheduler

    val clock = time.awakeEvery(1.second)
      .map(_ => s"Current system time: ${System.currentTimeMillis()} ms\n")
      .flatMap(x => Process.eval(TVDBApi.update(x)))
      .to(stdOutLines)

    Task.fork( clock.run ).unsafePerformAsync( _ => () )
  }
}
