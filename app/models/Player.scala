package models

case class Player (name: String) {
  require(
    name.charAt(0).equals('@')
    , s"name [$name] must start with @"
  )

  override def toString(): String = name
}

object Player {

  def empty(): Player = Player("")

}