// Kamila Sproska

// Zadanie 1
// a
import scala.annotation.tailrec
@tailrec
def existsA[A](xs: List[A])(p: A => Boolean): Boolean =
  xs match
    case h::t => p(h) || existsA(t)(p)
    case Nil => false



existsA(List(5, 1, 2, 3))(_ == 2)
!existsA(List(5,1,2,3)) (_ == 7)
existsA(List("a", "b", "c")) (_ == "a")
!existsA(Nil) (_ == 7)


// b
def existsB[A] (xs: List[A]) (p: A => Boolean): Boolean =
  xs.foldLeft(false)((res, elem) => res || p(elem))


existsB(List(5,1,2,3)) (_ == 2)
!existsB(List(5,1,2,3)) (_ == 7)
existsB(List("a", "b", "c")) (_ == "a")
!existsB(Nil) (_ == 7)


// c
def existsC[A] (xs: List[A]) (p: A => Boolean): Boolean =
  xs.foldRight(false)((elem, res) => res || p (elem))


existsC(List(5,1,2,3)) (_ == 2)
!existsC(List(5,1,2,3)) (_ == 7)
existsC(List("a", "b", "c")) (_ == "a")
!existsC(Nil) (_ == 7)


// Zadanie 2
def filter[A](xs: List[A])(p: A => Boolean): List[A] =
  xs.foldRight[List[A]](Nil)((elem, acc) =>
    if p (elem) then elem::acc else acc)


filter(List(2,7,1,3,7,8,4,1,6,9)) (_ > 3) == List(7, 7, 8, 4, 6, 9)
filter(List(2,7,1,3,7,8,4,1,6,9)) (_ <= 3) == List(2, 1, 3, 1)
filter(List(2,7,1,3,7,8,4,1,6,9)) (_ <= 0) == Nil
filter(List("c", "b", "c", "d")) (_ != "c") == List("b", "d")
filter(Nil) (_ != "c") == Nil


// Zadanie 3
// a
def remove1A[A](xs: List[A])(p: A => Boolean): List[A] =
  xs match
    case Nil => Nil
    case h::t => if p(h) then t else h::remove1A(t)(p)


remove1A(List(1, 2, 3, 2, 5))(_ == 2) == List(1, 3, 2, 5)
remove1A(List(1, 2, 3, 2, 5))(_ < 2) == List(2, 3, 2, 5)
remove1A(List(1, 2, 3, 2, 5))(_ == 10) == List(1, 2, 3, 2, 5)
remove1A(List("c", "a", "b", "a"))(_ == "a") == List("c", "b", "a")
remove1A(Nil)(_ == "a") == Nil


// b
def remove1B[A](xs: List[A])(p: A => Boolean): List[A] =
  @tailrec
  def removeRec(xss: List[A], acc: List[A]): List[A] =
    xss match
      case Nil => acc.reverse
      case h::t => if p(h) then t.reverse_:::(acc) else removeRec(t, h::acc)

  removeRec(xs, Nil)

remove1B(List(1, 2, 3, 2, 5))(_ == 2) == List(1, 3, 2, 5)
remove1B(List(1, 2, 3, 2, 5))(_ < 2) == List(2, 3, 2, 5)
remove1B(List(1, 2, 3, 2, 5))(_ == 10) == List(1, 2, 3, 2, 5)
remove1B(List("c", "a", "b", "a"))(_ == "a") == List("c", "b", "a")
remove1B(Nil)(_ == "a") == Nil


// Zadanie 4
def splitAt[A](xs: List[A])(n: Int): (List[A], List[A]) =
  @tailrec
  def splitRec(l1: List[A], l2: List[A], count: Int): (List[A], List[A]) =
    l2 match
      case Nil => (l1, l2)
      case h::t => if count < n then splitRec(l1:::List(h), t, count + 1) else (l1, l2)

  splitRec(Nil, xs, 0)


splitAt(List('a', 'b', 'c', 'd', 'e'))(2) == (List('a', 'b'), List('c', 'd', 'e'))
splitAt(List(1, 2, 3, 4, 5, 6, 7, 8))(3) == (List(1, 2, 3), List(4, 5, 6, 7, 8))
splitAt(List(1, 2, 3, 4, 5, 6, 7, 8))(0) == (Nil, List(1, 2, 3, 4, 5, 6, 7, 8))
splitAt(List(1, 2, 3, 4, 5, 6, 7, 8))(-5) == (Nil, List(1, 2, 3, 4, 5, 6, 7, 8))
splitAt(List(1, 2, 3, 4, 5, 6, 7, 8))(100) == (List(1, 2, 3, 4, 5, 6, 7, 8), Nil)
splitAt(Nil)(10) == (Nil, Nil)
