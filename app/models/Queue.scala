package models

object Queue {

  val queue = collection.immutable.Queue(Game.empty())

  def quitar() = queue.dequeue

  def agregar(game: Game) = queue.enqueue(game)

  def todo(): String = queue.toString()
}

