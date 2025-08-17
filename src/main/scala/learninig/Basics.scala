package learninig

object Basics extends App{
  //defining a values

  //defining a value
  val meaningOfLife: Int = 42; //const int meaningOfLife =42;

  // Int, Boolean, Char, Double, Float, String
  val asBoolean = true; //type is optional in Scala, but it's good practice to define it

  //strings and string operations
  val aString = "I love scala"
  val aComposedString = "I"+" "+"love"+" "+ "Scala" //string concatenation
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  //expressions = structures that can be reduced to a value
  val anExpression = 2 + 3 // this is an expression, it evaluates/reduced to 5

  //in most other common programming languages like Java, javascript, C, C++, etc.
  //We think in terms of statements, which are instructions to the computer. We tell the computer to
  // add this and that, and then store the result in a variable, or print this and that.
  //In Scala, we think in terms of expressions, which are structures that can be reduced to a value.
  //Even the if statement is an expression, that is called the if expression.

  //if expression
  val ifExpression = if (meaningOfLife > 40) 56 else 999 //if expression, it evaluates to 56 or 999
  val chainedIfExpression = {
    if (meaningOfLife > 40 ) 56
    else if (meaningOfLife < 38) 999
    else 42 //this is also an expression, it evaluates to either 56, 999 or 42
    //here we are not doing something based on conditions, rather we are assigning a value based on conditions
  }

  // code blocks - you can define values, methods, or another code block inside a code block
  // but at the end of the code block, it evaluates to the last expression (i.e. return a value)
  val aCodeBlock: Int  = {
    //definitions
    val aLocalValue = 69
    aLocalValue + 3 //this is the last expression, so this code block evaluates to 72
    //this is the value of the entire code block
  }

  //functions - we can define functions in Scala
  def aFunction(x: Int, y: Int): Int = y+x //you can define a function like this, but if the content of the function is large,
  // it's better to use a code block
  def aFunctionAsACodeBlock(x: Int, y: Int): Int = {
    //this is a code block that defines a function
    y + x //this is the last expression, so this code block evaluates to the sum of x and y
  } //this is fine because the code block is also an expression, so it evaluates to a value

  // in scala functions are usually recursive in practice
  //recursive functions - a function that calls itself
  def factorial(n: Int):Int = {
    if (n<=1) 1
    else n * factorial (n-1)
  }
  //in scala we don't use loops or iterations, we use RECURSION!

  // the unit type is the no meaningful value === "void" in other languages (like Java, C, C++, etc.)
  print("Hello, Scala!") // this doesn't return anything, it returns a unit type
  //so we think unit type as the type of SIDE EFFECTS - SIDE EFFECTS are operations that don't return a value,(doesn't compute a meaninigful information)
  //side effects are things like printing to the console, writing to a file, etc.
  //similar to println in Java, C, C++, etc, that returns void

  def aSideEffectFunction(): Unit = {
    println("I don't love returning Unit")
  }
  
  val theUnit = ()





}
