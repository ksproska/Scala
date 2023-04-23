enum Kolor:
  case Trefl, Karo, Kier, Pik

Kolor.values

enum Karta:
  case Blotka(kolor: Kolor, wart: Int)
  case Walet(kolor: Kolor)
  case Dama(kolor: Kolor)
  case Król(kolor: Kolor)
  case As(kolor: Kolor)

val k1 = Karta.Król(Kolor.Pik)

val przedzial: Int => Int => List[Int] =
  a => b => if a > b
  then Nil
  else b :: (przedzial(a)(b-1))

def map[A,B](f: A => B)(xs: List[A]): List[B] =
  xs match
    case Nil => Nil
    case x :: xs => f(x) :: map(f)(xs)

import Kolor.*
import Karta.*
def wszystkieKarty(kol: Kolor): List[Karta] =
  val figury = List(As(kol), Król(kol), Dama(kol), Walet(kol))
  val blotki = map ((n: Int) => Blotka(kol,n)) (przedzial(2)(10))
  figury ::: blotki

val kiery = wszystkieKarty(Kier)

//----------------------------------------------------------------

enum AB[+T1, +T2]:
  case A(e: T1)
  case B(e: T2)

import AB.*
val xsStr = List("Ala ", "ma ", "kota")
val xsInts = List(1, 2, 3)
val xsStrInt = (map((x: String) => A(x))(xsStr)) ::: (map((x: Int) => B(x))(xsInts))

def concatAndAdd(xs: List[AB[String,Int]]): (String,Int) =
  xs match
  case Nil => ("", 0)
  case x :: xs => (x, concatAndAdd(xs)) match
  case (A(str), (s,n)) => (str+s, n)
  case (B(num), (s,n)) => (s, num+n)

concatAndAdd(xsStrInt)

def concatAndAdd2(xs: List[Any]): (String,Int) =
  xs match
    case y :: ys => (y, concatAndAdd2(ys)) match
      case (str: String, (s, n)) => (str + s, n)
      case (num: Int, (s, n)) => (s, num + n)
      case _ => throw new IllegalArgumentException("wrong type")
    case Nil => ("", 0)

concatAndAdd2(xsStrInt)

//-------------------------------------------------------------------------

enum BT[+A]:
  case Empty
  case Node(elem: A, left: BT[A], right: BT[A])

import BT.*

val t = Node(1,Node(2,Empty,Node(3,Empty,Empty)),Empty)
t.asInstanceOf[Node[Int]].elem == 1
t.asInstanceOf[Node[Int]].left
t.asInstanceOf[Node[Int]].right

def nodes[A](bt: BT[A]): Int =
  bt match
    case Node(_, tl, tr) => 1 + nodes(tl) + nodes(tr)
    case Empty => 0