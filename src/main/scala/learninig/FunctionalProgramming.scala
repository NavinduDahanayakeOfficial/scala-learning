package learninig

object FunctionalProgramming extends App{

  //Scala is a OBject Oriented Programming language, but it is also a Functional Programming language
  class Person(name: String) {
    def apply(age: Int): Unit = println(s"$name is $age years old") //apply method allows us to call an instance like a function
  }

  val bob =  new Person("Bob")
  bob.apply(43)
  bob(43)  // INVOKING bob as a function, this is equivalent to bob.apply(43)
  // Instance of a class can be invoked as a function if it has an apply method defined

  /*
    Scala runs on the JVM
    Functional Programming:
     - compose functions
     - pass functions as arguments
     - return functions as results

    Conclusion: FunctionX = Function1, Function2, Function3,..., Function22
    so 22 is the maximum number of arguments a function can take in Scala functionX traits
   */

  /*
    Scala runs on JVM, JVM is the infrastructure on all java programs runs
    there are many languages that compiles to JVM bytecode, like Kotlin, Clojure, Scala, etc.
    Scala is a hybrid language, it is both Object-Oriented and Functional Programming language
    But the JVM was fundamentally designed for Java(which is a prototypical Object Oriented Programming language)
    So, JVM knows what is an object, but it doesn't know what is a function as a first class citizen.
    (In functional programming we want to work with functions as first class elements of programming -
    that means we want to work with functions like we work with any other value - we want to compose funtions, pass as arguments and retuen functions as results)
    Since JVM doesn't know what is a function, we have to use some tricks to make it work.
    So Scala has introduced some very interesting traits called FunctionX, where X is the number of arguments the function takes.
   */

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg+1
  }
  // this is just a plain trait with an apply method
  // Function1 is a trait that takes one argument and returns a value
  // we have instantiated trait called Function1
  //this obeject that we created can be invoked as a function because it has an apply method
  // and only thing it supports is to be invoked like a function
  // so basically, we have defined a function that takes an Int and returns an Int

  simpleIncrementer.apply(23) // 24
  simpleIncrementer(23) // 24, this is equivalent to simpleIncrementer.apply(23)
  //defined a function

  //ALL SCALA FUNCTIONS ARE ACTUALLY INSTANCES OF FUNCTIONX TRAITS

  //function with 2 arguments and String return type
  val stringConcatenator = new Function2[String, String, String]{
    override def apply (arg1: String, arg2:String): String = arg1 + " " + arg2
  }

  stringConcatenator("Hello", "Scala") // "Hello Scala"

  /*
  Syntax sugar - alternative syntax that replace above heavier boilerplate code
   */

  val doubler: Int => Int = (x: Int) => x*2
  doubler(4) //8
  // the above is equivalent to
  // val doubler: Function1[Int, Int] = new Function1[Int, Int] {
    // override def apply(x: Int): Int = x*2
  // }
  val doublerWithoutType = (x: Int) => x*2 // type is optional in Scala, but it's good practice to define it


  /*
    HigherOrder functions - functions that take other functions as arguments or return functions as results or both
   */
  val aMappedList: List[Int] = List(1,2,3).map(x => x+1) // this is a higher order function, it takes a function as an argument
  // map is a method that takes a function as an argument and applies it to each element and returns a new list with the results
  println(aMappedList) // List(2, 3, 4)

  println(List(1,2,3).map(x => List(x, x*2))) // List(List(1, 2), List(2, 4), List(3, 6))

  // for each element, it applies the function and returns a new single list by concatenating all the resulting lists
  val aFlatMappedList = List(1,2,3).flatMap{x =>
    List(x, x*2)
  } //alternative syntax, same as List(1,2,3).map(x => List(x, x*2)).flatten
  println(aFlatMappedList) // List(1, 2, 2, 4, 3, 6)

  //filter is HOF that takes a function that returns a Boolean and returns a new list with the elements that satisfy the condition
  val filteredList = List(1,2,3,4,5).filter(_ <= 3) //equivalent to List(1,2,3,4,5).filter(x => x <= 3)

  // all the pairs between the number 1,2,3 and the letters 'a', 'b', 'c'
  val allPairs = List(1,2,3).flatMap{ number =>
    List('a','b','c').map(letter => s"$number$letter")
  }
  println(allPairs) // List(1a, 1b, 1c, 2a, 2b, 2c, 3a, 3b, 3c)

  //in large codebases, chains such as above one could be hard to read and complex
  // so we can use the for-comprehension syntax to make it more readable
  val alternativePairs = for {
    number <- List(1,2,3) // this is called a generator, it generates the elements of the list
    letter <- List('a', 'b', 'c') // this is another generator, it generates the elements of the list
  } yield s"$number$letter" // this is the result of the for-comprehension, it yields a new list with the results
  // this is equivalent to the above flatMap and map chain
  println(alternativePairs)

  /**
   * Collections - Scala has a rich collection library that provides many useful data structures
   * - List, Set, Map, Vector, etc.
   */

  //lists
  val aList  = List(1,2,3,4,5)
  val firstElement = aList.head // first element of the list
  val restOfList = aList.tail // rest of the list without the first element
  val aPrependedList = 0 :: aList // (0, 1, 2, 3, 4, 5) // prepending an element to the list
  val anExtendedList = 0 +: aList :+ 6 // (0, 1, 2, 3, 4, 5, 6) // extending the list

  //sequences
  val aSequence: Seq[Int] = Seq(1,2,3) // Seq.apply(1,2,3) // a sequence is a more general data structure than a list, it can be mutable or immutable
  val accessedElement  = aSequence(1) // accessing an element by index, this is equivalent to aSequence.apply(1)
  println(accessedElement) // 2

  //vectors : fast sequence implementation
  val aVector: Vector[Int] = Vector(1,2,3,4,5) // Vector.apply(1,2,3,4,5) // a vector is

  // sets = no duplicates, unordered collection
  val aSet: Set[Int] = Set(1,2,3,4,1,2,3)
  println(aSet) // Set(1, 2, 3, 4) // duplicates are removed, and the order is not guaranteed
  val setHas5 = aSet.contains(5) // false, checking if the set contains an element
  val anAddedSet = aSet + 5 // Set(1, 2, 3, 4, 5) // adding an element to the set
  val aRemovedSet = anAddedSet - 3 // Set(1, 2, 4, 5) // removing an element from the set

  //ranges = a range of numbers, useful for loops and iterations
  val aRange: Range = 1 to 1000 // Range(1, 2, 3, ..., 1000) // a range of numbers from 1 to 1000
  val twoByTwo = aRange.map(x => x*2).toList // List(2, 4, 6, ..., 2000) // mapping a function over the range and converting it to a list

  //tuples = a fixed size collection of elements, can hold different types
  val aTuple: (Int, String, Boolean) = (1, "Hello", true) // a tuple with 3 elements of different types
  val firstElementOfTuple = aTuple._1 // 1, accessing the first element
  val secondElementOfTuple = aTuple._2 // "Hello", accessing the second element
  val thirdElementOfTuple = aTuple._3 // true, accessing the third element

  //maps
  val aMap: Map[String, Int] = Map("one" -> 1, "two" -> 2, "three" -> 3) // a map with string keys and int values
  val oneValue = aMap("one") // 1, accessing the value by key


}
