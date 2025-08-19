package learninig

object PatternMatching extends App{

  /*
  switch expression
   */
  val anInteger = 55
  val order = anInteger match{
    case 1 => "First"
    case 2 => "Second"
    case 3 => "Third"
    case _ => anInteger+"th" //default case, if none of the above cases match
  }
  // this pattern matching is a expression, it evaluates to a value
  println(order) // prints "55th"

  //Case class deconstruction ( Pattern matching only available for case classes)
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43) //Person.apply("Bob", 43)

  val personGreeting = bob match {
    case Person(n,a) => s"Hello, my name is $n and I am $a years old."
    case _ => "I don't know who you are."
  }
  println(personGreeting) // prints "Hello, my name is Bob and I am 43 years old."
  // this is a pattern matching on a case class, it extracts the values from the case class
  // and uses them in the string interpolation
  // this is a very powerful feature of Scala, it allows us to match on the structure of the data
  // and extract the values from it, without having to write a lot of boilerplate code
  // this is also called destructuring, it allows us to extract the values from the case class
  // and use them in the pattern matching expression


  //Deconstructing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band is a $genre band."
    case _ => "I don't know this band."
  } //if the tuple confirms into 2 value tuple structure, then it will extract the values

  // decomposing a lust
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_,2,_) => "This is a list with 2 in middle."
    case List(1, _*) => "This is a list that starts with 1."
    case _ => "This is some other list."
  } //the _* is a wildcard that matches any number of elements in the list

  /*
  Pattern matching will try all the cases in sequence until it finds a match.
  If no case matches, it will throw a MatchError.
  It is a good practice to always have a default case (using _)
  to handle unexpected cases, to avoid runtime errors.
  Pattern matching is a powerful feature of Scala that allows us to match on the structure of the
  data and extract the values from it, without having to write a lot of boilerplate code
   */

}
