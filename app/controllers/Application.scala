package controllers

import models.{Queue, Game}

import play.api.libs.ws.{WSResponse, WS, WSRequestHolder}
import play.api.mvc._
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.Logger

import scala.concurrent.Future

object Application extends Controller {

  val url = s"https://hooks.slack.com/services/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"

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def queue() = Action.async { request =>
    val body = request.body.asFormUrlEncoded

    Logger.debug(body.toString())
    Logger.error(body.toString())

    val text = body.get("text")

    val requestHolder: WSRequestHolder = WS.url(url)

    requestHolder
      .withRequestTimeout(300)

    val futureResponse: Future[WSResponse] = requestHolder.post(Map("payload" -> Seq("{\"text\": \" " + text + "\"}")))

    futureResponse.map(response => {
      Logger.debug(response.body)
      Ok(response.body)
    })

  }
}