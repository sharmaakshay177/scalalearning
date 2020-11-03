package ScalaCourseLectures.part2Lectures

class Writer(firstName: String, surName: String,val year:Int){
  def fullName:String = s"${this.firstName} ${this.surName}"
}

class Novel(name: String, releaseYear: Int, author: Writer){
  def authorAge():Int = releaseYear - author.year
  def isWrittenBy(author:Writer) = author == this.author
  def copy(newReleaseYear: Int):Novel = new Novel(name, newReleaseYear, author)
}

class Counter(counter: Int) {
  def currentCount(): Int = this.counter
}


