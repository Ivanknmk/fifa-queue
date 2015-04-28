package models

import scala.collection.immutable.Queue

object Queue {

  val queue = collection.immutable.Queue(Game.empty())

  def quitar(): Queue[Game] = queue.dequeue._2

  def agregar(game: Game): Queue[Game] = queue.enqueue(game)

  def todo(): String = queue.toString()

  def flush(): Queue[Game] = collection.immutable.Queue(Game.empty())
}

