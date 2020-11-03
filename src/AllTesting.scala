import java.util.Calendar
import java.lang.Thread
import java.net.URL

object MapWorking{
  val map: Map[Int, String] = Map(1 -> "one",
    2 -> "two",
    3 -> "three")

  val companyUrlMap: Map[Int, String] = Map(1 -> "www.demo1.com",
    2 -> "www.demo2.com",
    3 -> "www.demo3.com",
    4 -> "www.demo4.com",
    5 -> "www.demo5.com",
    6 -> "www.demo6.com",
    7 -> "www.demo7.com",
    8 -> "www.demo8.com",
    9 -> "www.demo9.com",
    10 -> "www.demo10.com")

}

object AllTesting extends App{
  val mapVar = MapWorking.map
  val compMap = MapWorking.companyUrlMap
  val timeNow = Calendar.getInstance().getTime
  println(timeNow)

  var lc = 0
  compMap.map( item => {
    val url = item._2
    val time = Calendar.getInstance().getTime

    if(lc >= 5 ){
      lc = 1
      //println("stopping thread")
      //Thread.sleep(60000)
      //println(s"Hitting ${url} at ${time}")
      //println(lc)
    }else{
      //println(s"Hitting ${url} at ${time}")
      lc = lc + 1
      //println(lc)
    }
  })

  val size = compMap.size
  for(j <- 1 to size){
    if( j % 5 == 0){
      println("printing in thread block for every fifth then making thread sleep")
      println(j)
      println("making thread sleep for 1 for ")
      Thread.sleep(30000)
    }else{
      println(s"printing without sleeping ${j}")
      println(j)
    }
  }

  val demoUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo&datatype=csv"
  val url =new URL(demoUrl)
  val conn = url.openConnection().setConnectTimeout(2)

}
