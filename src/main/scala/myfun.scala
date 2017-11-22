import scala.io.StdIn.{readLine,readInt}
import scala.math._
import scala.collection.mutable.ArrayBuffer
import java.io.PrintWriter
import scala.io.Source

object myfun {
  def main(args: Array[String]) {

    def getSum( num1:Int = 1, num2:Int = 1) : Int = {
      return num1 + num2
    }

    println("5 + 4 = " + getSum(5,4))

    // you can use named arguments
    println("5 + 4 = " + getSum(num2=5, num1=4))

    // A function that returns nothing (Procedure)
    def sayHi() : Unit = {
      println("Hi how are you doing")
    }

    // by convention, functions with side effects e.g. 'Procedures' should call with an empty parenthesis pair. (or
    // you can get rid of them if you really don't like empty parenthesis)
    sayHi()

    // Receive variable number of arguments
    def getSum2(args: Int*) : Int = {
      var sum : Int = 0
      for(num <- args){
        sum += num
      }
      sum
    }

    println("getSum2: " + getSum2(1,2,3,4,5))

    // Recursion example calculating factorials
    def factorial(num : BigInt) : BigInt = {
      if (num <= 1)
        1
      else
        num * factorial(num - 1)
    }

    // 1st: num = 4 * factorial(3) = 4 * 6 = 24
    // 2nd: num = 3 * factorial(2) = 3 * 2 = 6
    // 3rd: num = 2 * factorial(1) = 2 * 1 = 2

    println("Factorial of 4 = " + factorial(4))

    // Functions can be passed like any other variable
    // You need the _ after the function to state you meant the function
    val log10Func = log10 _

    println(log10Func(1000))

    List(1000.0,10000.0).map(log10Func).foreach(println)

    // You can use an anonymous function with map as well
    // Receives an Int x and multiplies every x by 50
    List(1,2,3,4,5).map((x : Int) => x * 50).foreach(println)

    // Filter will pass only those values that meet a condition
    List(1,2,3,4,5,6).filter(_ % 2 == 0).foreach(println)

    // Pass different functions to a function
    def times3(num : Int) = num * 3
    def times4(num : Int) = num * 4

    // Define the function parameter type and return type
    def multIt(func : (Int) => Double, num : Int ) = {
      func(num)
    }

    printf("3 * 100 = %.1f)\n", multIt(times3, 100))

    // A closure is a function that depends on a variable declared outside
    // of the function
    val divisorVal = 5
    val divisor5 = (num : Double) => num / divisorVal
    println("5 / 5 = " + divisor5(5.0))

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

    // ---------- EXCEPTION HANDLING ----------

    def divideNums(num1 : Int, num2 : Int) = try
    {
      (num1 / num2)
    } catch {
      case ex: java.lang.ArithmeticException => "Can't divide by zero"
    } finally {
      // Clean up after exception here
    }

    println("3 / 0 = " + divideNums(3,0))
  }
}
