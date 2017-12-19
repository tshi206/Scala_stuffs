object TrivialImplicitClass {

  implicit class NumToOrder[T] (num : T){
    def NTO: String = num match {
      case _ : Int => num match {
        case 1 => "1st"
        case 2 => "2nd"
        case 3 => "3rd"
        case _ => num + "th"
      }
      case _ => "(actually I don't know lol)"
    }
  }

  //this is equivalent to the following (actually this is what the compiler will do for you behind the scene, hence
  // implicit class is nothing but a syntactic sugar)
  /*

  object TrivialImplicitClass {
    class NumToOrder[T] (num : T){
      def NTO: String = num match {
        case _ : Int => num match {
          case 1 => "1st"
          case 2 => "2nd"
          case 3 => "3rd"
          case _ => num + "th"
        }
        case _ => "(actually I don't know lol)"
      }
    }
    implicit def NumToOrder[T] (num : T) : NumToOrder[T] = new NumToOrder(num)
  }

  */

}
