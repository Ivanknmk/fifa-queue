package controllers

import models.{Queue, Game}

import play.api.libs.ws.{WSResponse, WS, WSRequestHolder}
import play.api.mvc._
import play.api.Play.current
//needed for implicits
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.Logger

import scala.concurrent.Future

object Application extends Controller {

  val url = s"https://hooks.slack.com/services/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"
  val token = s"wd8GFk1rs29Lyp5X16l6B4Kw"

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def queue() = Action.async { request =>
    val body: Map[String, Seq[String]]= request.body.asFormUrlEncoded.get

    Logger.debug(body.toString())

    if ( body.get("token").get(0).toString != token )
      BadRequest("You are not authorized to use this bot")

//    val team_id: String = body.get("team_id").get(0)
//    val team_domain: String = body.get("team_domain").get(0)
//
//    val channel_id: String = body.get("channel_id").get(0)
//    val channel_name: String = body.get("channel_name").get(0)

//    val user_id: String = body.get("user_id").get(0)
    val user_name: String = body.get("user_name").get(0)

    val text: Array[String] = body.get("text").get(0).split(" ")

    val requestHolder: WSRequestHolder = WS.url(url)

    requestHolder
      .withHeaders(CONTENT_TYPE -> JSON.concat("; charset=UTF-8") )
      .withRequestTimeout(300)

    val futureResponse: Future[WSResponse] = requestHolder
      .post(Map("payload" -> Seq(Queue.action(user_name, text))))

    futureResponse.map(response => {
      Logger.debug(response.body)
      Ok(response.body)
    })

  }
}