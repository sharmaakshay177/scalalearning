package ThoughtWorks
import scala.collection.mutable
import scala.math.pow
import scala.collection.mutable.{ListBuffer, Set}
object Solution extends App{

  def calculate( P: Int, Q: Int) ={
      val mod = 998244353
      var expo = mod - 2;
      var p = P
      var q = Q
      // Loop to find the value
      // until the expo is not zero
      while (expo != 0)
      {

        // Multiply p with q
        // if expo is odd
        if ((expo & 1) == 1)
        {
          p = (p * q) % mod;
        }
        q = (q * q) % mod;

        // Reduce the value of
        // expo by 2
        expo >>= 1;
      }
     p
    }

  def getSubstring(string: String, size: Int) ={
    val localList = ListBuffer[String]()
    for( i <- 0 to string.length - size){
      val sub = string.substring(i, i+size)
      localList.+=(sub)
    }
    localList
  }
  def countOccurrences(src: String, tgt: String): Int =
    src.toSeq.sliding(tgt.length).count(window => window == tgt)

  val string = "ababa"
  val comb = 2
  val allCombs = getSubstring(string, comb)
  val map = mutable.HashMap[String, Int]()
  allCombs.map(item => {
    val count = countOccurrences(string, item)
    println(count)
    map.put(item, count)
  })
  map.map(item => println(item._1 + " " + item._2))
//
//  val p = 5
//  val q = 3
//
//  val result =  calculate(5,3)
//  print(result)
}
