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

    Conclusion: FunctionX
   */

  /*
  Scala runs on JVM, JVM is the infrastructure on all java programs runs
  there are many languages that compiles to JVM bytecode, like Kotlin, Clojure, Scala, etc.
  Scala is a hybrid language, it is both Object Oriented and Functional Programming language
  But the JVM was fundamentally designed for Java(which is a prototypical Object Oriented Programming language)
   */

}
