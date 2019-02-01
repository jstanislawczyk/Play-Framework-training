package model

case class User(id: Long, firstName: String, lastName: String, mobile: Long, email: String)

object Users {

  var users: Seq[User] = Seq()

  def get(id: Long): Option[User] = users.find(_.id == id)

  def getAll: Seq[User] = users

  def add(user: User): Unit = {
    users = users :+ user.copy(id = users.length)
  }

  def delete(id: Long): Option[Int] = {
    val originalSize = users.length
    users = users.filterNot(_.id == id)
    Some(originalSize - users.length)
  }
}