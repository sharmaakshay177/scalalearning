package ScalaCourseLectures.part3FP

import scala.collection.mutable.ListBuffer

case class Person(name: String)
//TODO make social networkmap common for all instead of class
//TODO check connection between the one person and another either direct or indirect
case class SocialNetwork(name: String){

  private val friendList:ListBuffer[String] = ListBuffer[String]()
  private val socialNetworkMap: Map[String,ListBuffer[String]] = Map()

  private def addToFriendList(name: String):ListBuffer[String] = this.friendList :+ name
  private def removeFromFriendList(name: String):ListBuffer[String] = this.friendList -= name

  def addToSocialNetwork():Map[String,ListBuffer[String]] = socialNetworkMap + ((this.name,friendList))
  def removeFromSocialNetwork():Map[String,ListBuffer[String]] = socialNetworkMap - (this.name)

  def friend(name: String):Unit = addToFriendList(name)
  def unFriend(name: String): Unit = removeFromFriendList(name)
  def friends:Unit = friendList.foreach(friend => println(friend))
  def numberOfFriends:Int = friendList.length

}

object CollectionsExercise extends App{

  val person1 = SocialNetwork("Akshay")
  val person2 = SocialNetwork("Lucky")
  val person3 = SocialNetwork("Arushi")
  person1.addToSocialNetwork()
  person1.friend("Lucky");person1.friend("Arushi")
 person1.friends
}
