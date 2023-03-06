import scala.annotation.tailrec

object Kata {
  @tailrec
  private def makePairs(s: String, last: String, acc: List[String]): List[String] =
    if s == "" && last == "" then acc
    else if s == "" then acc ::: List(s"${last}_")
    else if last == "" then makePairs(s.tail, s.head.toString, acc)
    else makePairs(s.tail, "", acc ::: List(s"$last${s.head}"))
  def solution(s: String): List[String] = makePairs(s, "", Nil)

  def solution2(s: String): List[String] = {
    s.grouped(2).map(_.padTo(2, '_')).toList
  }
}

Kata.solution("abcdef") == List("ab", "cd", "ef")
Kata.solution("abcde") == List("ab", "cd", "e_")
