package learninig

object ObjectOrientation extends App{

  // This extends App is a Scala idiom that allows us to run the code inside this object as a main method
  //java equivalent is public static void main(String[] args)
  // here what happen is there is an App triat with a main method, and this object extends that trait
  // so the code inside this object is executed when we run the program

  //class and instance
  class Animal {
    val age:Int = 0

    def eat(): Unit = println("I'm eating")
  }
  val animal =  new Animal()

  //inheritance
  //scala has constructor definitions in the class definition
  class Dog(val name:String) extends Animal{}
  val aDog = new Dog("Rex")
  //constructor arguments are not fields, they are just parameters
  //to make them fields, we need to use val or var in front of the constructor parameter
  print(aDog.name)

  //subtype polymorphism
  val aDeclaredAnimal:Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() //The most derived implementation is called at runtime, which is the Dog's eat method

  //abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // all class fields and methods are public by default in Scala
    // you can restrict the access level using private or protected keywords

    def walk():Unit //abstract method, no implementation
  }

  class Cat extends WalkingAnimal {
    override def walk():Unit = println("Cat is walking")
  }

  //interface = ultimate abstract class
  //in Scala, we use traits to define interfaces
  //traits can have abstract methods and concrete methods (with implementation)
  trait Carnivore {
    def eat(animal: Animal): Unit //abstract method, no implementation
  }

  trait Philosopher {
    def ?!(thought: String): Unit // this '?! is a valid method name in Scala, it's called an operator method
  }

  //just like java scala offers single class inheritance, and multiple traits inheritance
  //single class inheritance and multi-trait inheritance(Also called "mixing")
  class Crocodile extends Animal with Carnivore with Philosopher{
    override def eat(animal: Animal): Unit = println("Crocodile is eating " + animal)

    override def ?!(thought: String): Unit = println(s"Crocodile is thinking: $thought")
  }

  val aCrocodile =  new Crocodile()
  // following 2 lines are equivalent
  aCrocodile.eat(aDog)
  aCrocodile eat aDog //infix notation = object method argument, only availabe for methods with ONE argument

  aCrocodile ?! "What if I could fly?" //using operator notation, this is a valid method name in Scala

  //operators in Scala are actually methods
  val basicMath = 1+ 2
  val anotherBasicMath = 1.+(2) //this is the same as the above line, just using method notation

  //anonymous classes
  //in java,c++ we cannot instantiate an abstract class by themselves, we need to create a concrete class that extends the abstract class
  //in scala, we can create an anonymous class that extends the abstract class or trait
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("Dinosaur is eating " + animal)
  }
  /*
    When I write `new Carnivore`, I am creating an instance of an anonymous class that extends the Carnivore trait.
    What I'm telling to the compiler is: "Create a new class that extends Carnivore, and implement the eat method in it."
    under the hood it like this:
        class Carnivore_Anonymous_35778 extends Carnivore {
          override def eat(animal: Animal): Unit = println("Dinosaur is eating " + animal)
        }
        val dinosaur = new Carnivore_Anonymous_35778()
   */

  // singleton object - a class with only one instance
  // in scala, we can define a singleton object using the `object` keyword
  object MySingleton { //the only instance of this class is the singleton object itself
    val myValue = 42
    def myMethod(): Unit = println("Hello from MySingleton")

    def apply(x: Int): Int = x+ 1 //apply method, allows us to call the object like a function
    // this apply methods can be used in any class or object, not just in singleton objects
  }

  println(MySingleton.myValue)
  MySingleton.myMethod()

  //presence of apply method allows us to call the object like a function
  MySingleton(10) // this is equivalent to MySingleton.apply(10) and returns 11

  // In scala, you can define singleton objects and classes with the same name
  // this is called companion object, and it is used to define static members of a class
  // companion object is a singleton object with the same name as the class
  //this can cn also be applied to traits
  object Animal {
    //companions can access each other's private fields and methods
    //singleton animal and instances of Animal class are different things
    // you normally use singleton objects to define static members of a class
    val canLiveIndefinitely = false // this is a static field, like a static variable in Java, C, C++, etc.
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely //accessing the static field of the companion object
  // this is same as you access a static field in Java, C, C++, etc.


  /*
    Case classes - a special kind of class that is used to define immutable data structures
    they are lightweight data structures with some boilerplate
    when defines a case class, the compiler automatically generates
      - sensible equals and hashCode methods
      - serialization
      - companion with apply method
      - pattern matching support
  */
  case class Person(name:String, age:Int)

  // we can create instances of case classes without using the `new` keyword
  // it is because case classes have a companion object with an apply method
  // this is the same as writing new Person("Bob", 30)
  val Bob = Person("Bob", 30) //equivalent to Person.apply("Bob", 30)


  /*
    Exceptions
  */
  try{
    val x: String = null
    x.length
  } catch {
    case e: Exception => "some faulty error Message"
  } finally {
    println("this is always executed, no matter what")
  }


  /*
   Generics - a way to define classes, traits, and methods that can work with any type
    Generics allow us to write code that is type-safe and reusable
  */
  //this MyList is a generic class that can hold any type T
  abstract class MyList[T] {
    //in this class we can define methods that operate on the type T
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type

  val aList: List[Int] = List(1, 2, 3) //equivalent to List.apply(1, 2, 3)
  val first = aList.head
  val rest = aList.tail //rest is a List[Int] with the first element removed
  val aStringList = List("Hello", "World") //this is a List[String] with two elements
  val firstString = aStringList.head
  val restString = aStringList.tail //rest is a List[String] with the first element removed

  // as in above examples the whole purpose of Scala is reuse the same functionality and apply it to multiple types
  // you can see we can use the same List class for Int, and String types, and use the same methods on them
  // this is the power of generics in Scala, and it is used extensively in the Scala standard library
  // and in most of the libraries you will use in Scala

  // we can also define generic methods
  def genericMethod[T](value: T): T = value //this is a generic method
  // this method takes a value of type T and returns a value of type T
  val aGenericValue = genericMethod(42) // this is an Int
  val aGenericString = genericMethod("Hello") // this is a String

  /*
    - Point 1 : In Scala, we usually operate with immutable values/objects
       - any modification to an object of a class, must return a new object of the class with the modification
       - this is called immutability, and it is a key concept in functional programming
       - Benefits
          1) Works miracles in multithreaded environments, as we don't have to worry about shared mutable state
          2) Makes reasoning about code easier (as we can be sure that the values/objects we are working with
             are not changed by other parts of the code, and we can rely on them to be the same throughout the code)
   */
  val reversedList = aList.reverse // this returns a new List[Int] with the elements reversed

  /*
    - Point 2 : Scala is the closest to the Object-Oriented ideal
      Scala is marketed as a mixed between Object-Oriented and Functional programming
      Scala is a very good Object-Oriented programming language, because all the code are inside a class or an object
      Given everything is an object, we can say that Scala is an Object-Oriented programming language
   */

}
