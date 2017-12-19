object TrivialImplicitClass2 {

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
