package model

import play.api.libs.json._

case class User(id: Long, firstName: String, lastName: String)

object User {
  implicit val personFormat: OFormat[User] = Json.format[User]
}