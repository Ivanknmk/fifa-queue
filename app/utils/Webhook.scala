package utils

import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.ws.ning.NingAsyncHttpClientConfigBuilder
import scala.concurrent.Future

object Webhook {

  def send(text: String) = {

    val holder: WSRequestHolder = WS.url("https://hooks.slack.com/services/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")

    val futureResponse: Future[WSResponse] = holder.post(Map("payload" ->
      Seq("{\"channel\": \"#fifasquare\", \"username\": \"fifabot\", \"text\": "+ text + ", \"icon_emoji\": \":ghost:\"}")))


  }

}
