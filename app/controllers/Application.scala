package controllers

import models.Game
import play.api.mvc._
import org.slf4j.{LoggerFactory, Logger}

object Application extends Controller {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[Game])

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def queue() = Action { request =>
    val body: AnyContent = request.body
    Ok(body.toString)
  }
}