object LowIntSum {
  private def rec(numbers: List[Int], twoSmallest: (Int, Int)): Int =
    if numbers == Nil then
      twoSmallest._1 + twoSmallest._2
    else if twoSmallest._1 == -1 || twoSmallest._1 > numbers.head then
      rec(numbers.tail, (numbers.head, twoSmallest._1))
    else if twoSmallest._2 == -1 || twoSmallest._2 > numbers.head then
      rec(numbers.tail, (twoSmallest._1, numbers.head))
    else rec(numbers.tail, twoSmallest)

  def sumTwoSmallest(numbers: List[Int]): Int =
    rec(numbers, (-1, -1))
}

LowIntSum.sumTwoSmallest(List(25, 42, 12, 18, 22)) == 30
LowIntSum.sumTwoSmallest(List(1, 2, 3, 4, 5))  == 3
LowIntSum.sumTwoSmallest(List(7, 15, 12, 18, 22)) == 19
