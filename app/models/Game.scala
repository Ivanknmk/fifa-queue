package models

case class Game (player1: Player, player2: Player) {

  override def toString(): String = player1.toString() + " vs " +  player2.toString()

}

object Game{

  def empty(): Game = Game(Player.empty(), Player.empty())

}
