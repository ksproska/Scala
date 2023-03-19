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
  def replicateRec(repetitionsLeft: Int, xssInner: List[Int], acc: List[Int]): List[Int] =
    xssInner match
      case Nil => acc
      case h :: Nil =>
        if repetitionsLeft > 0 then replicateRec(repetitionsLeft - 1, xssInner, acc ::: List(h))
        else acc
      case h :: t =>
        if repetitionsLeft > 0 then replicateRec(repetitionsLeft - 1, xssInner, acc ::: List(h))
        else replicateRec(t.head, t, acc)
  xss match
    case Nil => Nil
    case h::_ => replicateRec(h, xss, Nil)


replicate(List(1,0,4,-2,3)) == List(1, 4, 4, 4, 4, 3, 3, 3)
replicate(List(0, 1, 2, 3)) == List(1, 2, 2, 3, 3, 3)
replicate(List(3)) == List(3, 3, 3)
replicate(List(0)) == Nil
replicate(List(1)) == List(1)
replicate(Nil) == Nil

// Zadanie 5
val root3 = (a: Double) =>
  @tailrec
  def root3R(x: Double): Double =
    if math.abs(x * x * x - a) <= 1.0e-15 * math.abs(a) then x
    else root3R(x + (a / (x * x) - x) / 3)
  if a > 1 then root3R(a/3)
  else root3R(a)

math.abs(root3(0.01) - 0.21544346900319) <= 1.0e-5
math.abs(root3(0.2) - 0.58480354764257) <= 1.0e-5
root3(0) == 0
root3(1) == 1
math.abs(root3(2) - 1.2599210498949) <= 1.0e-5
math.abs(root3(7) - 1.9129311827724) <= 1.0e-5
root3(8) == 2
root3(27) == 3
math.abs(root3(97) - 4.594700892207) <= 1.0e-5
