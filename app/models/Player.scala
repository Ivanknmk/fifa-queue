package models

case class Player (name: String) {
}


object Player {

  def empty(): Player = Player("")

}