package DailyCodingProblems

import scala.util.control.Breaks._

object SentenceOperations{

  private val separators: List[Char] = List(',',';',':')
  private val terminalMark: List[Char] = List('.','?','!')

  private var stringToCheck: String = ""

  private def startWithCapitalCheck: Boolean =
    if(this.stringToCheck(0).isUpper) true
    else false

  private def singleSpaceBwWordsCheck: Boolean = {
    for (i <- 1 until this.stringToCheck.length - 1) {
      val char = this.stringToCheck.charAt(i)
      val nextChar =  this.stringToCheck.charAt(i+1)
      if (char.equals(' ') && nextChar.equals(' ')) return false
    }
  true
  }

  private def terminalMarkCheck: Boolean = {
    val endWord = this.stringToCheck.split(" ").toList.last
    if(endWord.length ==1 && terminalMark.contains(endWord.charAt(0))) false
    else{
      for (i <- 0 until endWord.length){
        if(i == endWord.length-1) {
          if(! terminalMark.contains(endWord.charAt(i))) return false
        }
        else if (endWord.charAt(i).isUpper) return false
       }
      true
    }
  }

  private def allOtherCharCheck: Boolean ={
      val allCharSet = separators.toBuffer.++=(terminalMark).toList
      val string = this.stringToCheck
      for (i <- 1 until string.length) {
        val char = string.charAt(i)
        breakable{
          if (char == ' ') break
          else if(char.isLetter) {
            if(char.isUpper) return false
          }else {
            if(!allCharSet.contains(char)) return false
          }
        }
      }
    true
  }

  private def set(sentence: String): Unit = this.stringToCheck = sentence

  def isStringValid(sentence: String): Boolean ={
    this.set(sentence)
    val isStartingWithCapital = this.startWithCapitalCheck
    val isSingleSpaceInSentence = this.singleSpaceBwWordsCheck
    val isEndingWithTerminalNoSpace = this.terminalMarkCheck
    val isAllThingsGood = this.allOtherCharCheck
    if(isStartingWithCapital && isSingleSpaceInSentence && isEndingWithTerminalNoSpace && isAllThingsGood) true
    else false
  }

}

object SentenceChecker  extends App{
  val sent1 = "Hi there this is good one."
  val sent2 = "Terminal missing string"
  val sent3 = "More than  one space."
  val sent4 = "Inside char is Capital check."
  val sent5 = "Last char should end with terminal without space ."

  val l = List(sent1,sent2,sent3,sent4,sent5)
  l.foreach(item => println(s"is this sentence is valid ${SentenceOperations.isStringValid(item)}"))
}
