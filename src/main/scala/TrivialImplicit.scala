import scala.language.implicitConversions

import TrivialImplicitClass._
//import TrivialImplicitClass2._

object TrivialImplicit extends App {

  case class MyString(s : String){
    def myStrOp: String ={
      "The string " + s.toUpperCase + " contains '" + s.charAt(0) + "' at pos 0"
    }
  }

  implicit def StringToMyString(s : String):MyString = MyString(s)

  val s = "a trivial string" //String does not have a definition for myStrOp

  println(s.myStrOp) //implicit will allow the compiler to search for a method that can covert a String to
  // MyString which has the definition of myStrOp

  // The compiler will search for the second parameter in the scope if no such input value is explicitly provided
  def myStrFun (counter: Int)(implicit myString: MyString) : Unit = {
    println(for (i <- 1 to counter; MyString(s) = myString) yield myString.myStrOp + " the " + i.NTO + " times. It has " +
      "length of: " + s.length +
      " \n")
  } // Note that NTO is not a method on Int, it is provided by one of the implicit classes imported from else where,
  // in which Int will be 'boxed' or 'lifted' or 'wrapped' into a NumToOrder object.

  implicit val theStringItLooksFor:MyString = MyString("I'm the one you look for") //the compiler will look for this if no
  // explicit argument is given to the second parameter list (group) for the above function

  myStrFun(5)

  myStrFun(5)(MyString("I'm explicitly provided")) //note that 'new' keyword can be omitted for case class, this is a
  // syntactic sugar in Scala

}
