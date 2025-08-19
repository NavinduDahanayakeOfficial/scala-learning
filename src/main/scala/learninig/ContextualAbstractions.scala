package learninig

object ContextualAbstractions {

  /*
   1 - Context parameters/arguments
   */

  val aList = List(2,1,3,4)
  val anOrderedList = aList.sorted

  //ordering
  val descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)  // (a,b) => a > b === _ > _

  def main(args: Array[String]): Unit = {
    println(anOrderedList)
  }
}
