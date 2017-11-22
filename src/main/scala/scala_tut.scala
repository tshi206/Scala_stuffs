import scala.io.StdIn.{readLine,readInt}
import scala.math._
import scala.collection.mutable.ArrayBuffer
import java.io.PrintWriter
import scala.io.Source

object scala_tut {
  def main(args: Array[String]) {
    /*
    val takeExample = new IOandFoldExample()
    takeExample.showExample();
    */

    println(myOP2(9)(7))
    println(myOP2(-2)(5))


    println(factorial(5))
    println(impuredfactorial(5))
    println(tailFactorial(5))
    println(wrappedTFactorial(5))

  }

  def myOP2(c : Int) = (i : Int) => { //a PURE higher order function returning an anonymous function
    if (c > 0)
      i * 2
    else
      i / 2
  }

  // Recursion example calculating factorials; Head recursion is a problem wrt. performance and memory consumption;
  // See the Tail recursion example below.
  def factorial(num : BigInt) : BigInt = { //a PURE first class function
    if (num <= 1)
      1
    else
      num * factorial(num - 1) // this is a head recursion because the recursion call is NOT the last step in this
    // function; the last operation here is MULTIPLICATION; A tail recursion should have its recursion call been the
    // last operation in the body.
  }

  def tailFactorial(num : BigInt, baseCase : BigInt = 1) : BigInt = {
    if (num <= 1)
      baseCase // Note here the solution is built along way to the recursion, hence the baseCase here will be the
    // accumulated result. It is only returned once num is less than or equal to 1 such that it will be the final
    // result.
    else
      tailFactorial(num - 1, num * baseCase) // here the last operation is the recursion call
  }

  def wrappedTFactorial(num : BigInt) : BigInt = {
    println("You called factorial for: " + num) // this is a side effect hence this is not a PURE function.
    def tailFactorial(num : BigInt, baseCase : BigInt = 1) : BigInt = {
      if (num <= 1)
        baseCase // Note here the solution is built along way to the recursion, hence the baseCase here will be the
      // accumulated result. It is only returned once num is less than or equal to 1 such that it will be the final
      // result.
      else
        tailFactorial(num - 1, num * baseCase) // here the last operation is the recursion call
    }
    return tailFactorial(num, 1)
  }

  // Loop example calculating factorials
  def impuredfactorial(num : BigInt) : BigInt = { //this is not a PURE function
    var i = num
    var j : BigInt = 1 // variable j is mutable; it is the side effect introduced by this function; we should avoid var and
    // use val instead as the best practice of FP
    while (i > 0){
      j *= i
      i -= 1
    }
    j
  }

  class IOandFoldExample {

    def showExample(): Unit ={ //this is a Procedure. it has side-effect. it is not pured.
      // ---------- FILE I/O ----------
      // Use import java.io.PrintWriter to write to a file
      val writer = new PrintWriter("test.txt")
      writer.write("Just some random text\nSome more random text")
      writer.close()

      // Use import scala.io.Source to read from files
      val textFromFile = Source.fromFile("test.txt", "UTF-8")

      // Iterate through each line in the file and print
      val lineIterator = textFromFile.getLines
      println(lineIterator)
      var texts = ArrayBuffer[String]()
      lineIterator.foreach(s => texts.+=(s))
      texts.foreach(text => println(text))

      println(lineIterator)
      println(texts.size)

      val result1 = texts.foldLeft(""){(accumulatedSum, string) => accumulatedSum + string}
      println(s"left folded texts: ${result1}")

      val result2 = texts.foldRight(""){(accumulatedSum, string) => accumulatedSum + string}
      println(s"right folded texts: ${result2}")

      val result3 = texts.fold(""){(accumulatedSum, string) => accumulatedSum + string}
      println(s"folded texts: ${result3}")

      texts.foreach(s => println(s))

      textFromFile.close()
    }
  }
}
