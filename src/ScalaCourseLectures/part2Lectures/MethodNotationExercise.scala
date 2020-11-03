package ScalaCourseLectures.part2Lectures

object MethodNotationExercise {

  class Person(name: String, movie: String, val age: Int = 0){
    def +(nickname: String) = new Person(s"${name} the ${nickname}", movie, age +1)
    def learns = s"${name} learns scala"
    def learnsScala(subject: String = "Scala") = learns
    def apply(num: Int): String = s"${name} watched ${movie} ${num} times"
  }
}
