package models

class Queue {

  val queue = collection.mutable.Queue[Game]()

}

object Queue{

  val queue = new Queue().queue

  implicit class StringExtensions(val s: String) extends AnyVal {
    def insensitive = new {
      def unapply(other: String) = s.equalsIgnoreCase(other)
    }
  }

  val next = "next".insensitive
  val print = "print".insensitive
  val add = "add".insensitive
  val flush = "flush".insensitive
  val current = "current".insensitive
  val admin = "ivanknmk"


  def action(author: String, text: Array[String]):String = text(0) match {

    case next()  => quitar(author)
    case print()  => todo(author)
    case add()  => agregar(author, Game(Player(text(1)), Player(text(2))))
    case flush()  => eliminarTodo(author)
    case current()  => actual(author)
    case _ => "Operation doesn't exist"
  }

  def quitar(author: String): String = {
    if (queue.isEmpty)
      "{\"text\": \" @" + author + " there's nothing in the queue.\" , \"link_names\": 1}"

    else if (authorized(author, List(queue.head.player1.toString, queue.head.player2.toString, admin) ) ) {
      queue.dequeue()
      "{\"text\": \" @" + author + " has triggered the next game. " + queue.head + " you are next!\" , \"link_names\": 1}"
    }
    else
      "{\"text\": \" @" + author + " you are not authorized to dequeue. @" + queue.head.player1 +
        " @" + queue.head.player2 + " or @" + admin +" please run [/queue next]\" , \"link_names\": 1}"

  }

  def todo(author: String): String = {

    val queueAsString = {
      if (queue.isEmpty)
        "null"
      else
        queue mkString("::\\n::")

    }
    "{\"text\": \" @" + author + " here's the queue:\\n" + queueAsString + "\" , \"link_names\": 1}"

  }

  def agregar(author: String, game: Game): String = {
    queue.enqueue(game)
    "{\"text\": \" @" + author + " has added a game: " + game.toString() + "\" , \"link_names\": 1}"
  }

  def eliminarTodo(author: String): String = {
    if (authorized(author, List(admin) ) ){
      queue.dequeueAll((game: Game) => true)
      "{\"text\": \" @" + author + " has dequeued everything.\" , \"link_names\": 1}"
    }
    else
      "{\"text\": \" @" + author + " you are not authorized to dequeue. Please contact @" + admin + "\" , \"link_names\": 1}"
  }

  def actual(author: String): String ={
    if (queue.isEmpty)
      "{\"text\": \" @" + author + " there's nothing in the queue.\" , \"link_names\": 1}"
    else
      "{\"text\": \" @" + author + " current game: " + queue.head + "\" , \"link_names\": 1}"
  }

  def authorized(author: String, allowed: List[String]): Boolean = allowed.contains(author)


}

