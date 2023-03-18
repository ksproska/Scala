import scala.annotation.tailrec
// Kamila Sproska

// Zadanie 1
def take[A](n: Int, xss: List[A]): List[A] =
  xss match
    case Nil => Nil
    case h::t => if n <= 0 then Nil else h :: take(n - 1, t)


take(2, List(1,2,3,5,6)) == List(1,2)
take(-2, List(1,2,3,5,6)) == Nil
take(8, List(1,2,3,5,6)) == List(1,2,3,5,6)
take(0, List(1,2,3,5,6)) ==  Nil
take(1, List("a", "b", "c")) == List("a")
take(2, Nil) == Nil
take(-2, Nil) == Nil

// Zadanie 2
@tailrec
def drop[A](n: Int, xs: List[A]): List[A] =
  xs match
    case Nil => Nil
    case _::t => if n <= 0 then xs else drop(n - 1, t)

drop(2, List(1,2,3,5,6)) == List(3,5,6)
drop(-2, List(1,2,3,5,6)) == List(1,2,3,5,6)
drop(8, List(1,2,3,5,6)) == Nil
drop(0, List(1,2,3,5,6)) ==  List(1,2,3,5,6)
drop(1, List("a", "b", "c")) == List("b", "c")
drop(2, Nil) == Nil
drop(-2, Nil) == Nil

// Zadanie 3
def reverse[A](xs: List[A]): List[A] =
  @tailrec
  def reverseR(xsR: List[A], acc: List[A]): List[A] =
    xsR match
      case Nil => acc
      case h::t => reverseR(t, h::acc)
  reverseR(xs, Nil)


reverse(List("Ala", "ma", "kota")) == List("kota", "ma", "Ala")
reverse(List(1, 1, 2, 3)) == List(3, 2, 1, 1)
reverse(List(1)) == List(1)
reverse(Nil) == List()


// Zadanie 4
val replicate = (xss :List[Int]) =>
  @tailrec
  def replR(repetitionsLeft: Int, xssR: List[Int], acc: List[Int]): List[Int] =
    xssR match
      case Nil => acc
      case h::t =>
        if h > 0 && repetitionsLeft > 0 then replR(repetitionsLeft - 1, xssR, acc:::List(h))
        else if t != Nil then replR(t.head, t, acc)
        else acc
  replR(1, xss, Nil)


replicate(List(1,0,4,-2,3)) == List(1, 4, 4, 4, 4, 3, 3, 3)
replicate(List(0, 1, 2, 3)) == List(1, 2, 2, 3, 3, 3)
replicate(List(3)) == List(3, 3, 3)
replicate(List(0)) == Nil
replicate(List(1)) == List(1)
replicate(Nil) == Nil

// do zrobienia zad 4 i 5
