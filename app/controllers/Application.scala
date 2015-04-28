package controllers

import models.{Queue, Game}
import play.api.mvc._

import utils.Webhook
import play.api.Logger

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def queue() = Action(parse.urlFormEncoded) { request =>
    val body = request.body

    Logger.debug(body.toString())
    Logger.error(body.toString())
    Logger.info(body.toString())

    Webhook.send(body("text").head)

    Ok(body("command").head + " " + body("text").head)
  }
}