package controllers

import models.{Queue, Game}
import play.api.mvc._
import org.slf4j.{LoggerFactory, Logger}
import utils.Webhook

object Application extends Controller {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[Game])

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def queue() = Action(parse.urlFormEncoded) { request =>
    val body = request.body

    Webhook.send(body("text").head)

    Ok(body("command").head + " " + body("text").head)
  }
}