object Order {
  def descendingOrder(num: Int): Int =
    num.toString.toCharArray.sortWith(_ > _).mkString.toInt
}

Order.descendingOrder(15243)
