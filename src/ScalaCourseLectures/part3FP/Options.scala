package ScalaCourseLectures.part3FP

import java.util.Random

object Options extends App{
  val config: Map[String,String]  = Map(
    "host" -> "127.0.0.1",
    "port" -> "8080",
    "host2" -> null,
    "port2" -> null
  )

  class Connection{
    def connect: String = "connected to a server"
  }

  object Connection{
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }
    //todo :- check why its not getting second value in option
  val host = config.get("host2") orElse config.get("host")
  host.map(h => println(h))
  val port = config.get("port")
  val connectionStatus = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  val checkConnection = connectionStatus.map(c => c.connect)

  checkConnection.foreach(println)
}
