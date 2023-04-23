// Kamila Sproska
import scala.annotation.tailrec

// Zad 1
@tailrec
def whileLoop(condition: => Boolean)(expression: => Unit): Unit =
  if condition
  then
    expression
    whileLoop(condition)(expression)

var count = 0
whileLoop (count < 5) {
  println(count)
  count += 1
}

// Zad 2
def lrepeat[A](k: Int)(xls: LazyList[A]): LazyList[A] =
  def inner(leftRepeats: Int, restOfList: LazyList[A]): LazyList[A] =
    restOfList match
      case LazyList() => LazyList()
      case h #:: t =>
        if leftRepeats == 0
        then inner(k, t)
        else h #:: inner(leftRepeats - 1, restOfList)
  inner(k, xls)


lrepeat(3)(LazyList('a','b','c','d')).toList == List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'd', 'd', 'd')
lrepeat(3)(LazyList.from(1)).take(15).toList == List(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5)
lrepeat(3)(LazyList()).take(15).toList == List()
lrepeat(1)(LazyList.from(5)).take(5).toList == List(5, 6, 7, 8, 9)

// Zad 3
enum BT[+A]:
  case Empty
  case Node(elem: A, left: BT[A], right: BT[A])

import BT.*
val tt = Node(1,Node(2,Node(4,Empty,Empty),Empty),Node(3,Node(5,Empty,Node(6,Empty,Empty)),Empty))
val tt2 = Node("1",Node("2",Empty,Empty),Node("3",Node("5",Empty,Empty), Empty))

enum lBT[+A]:
  case LEmpty
  case LNode(elem: A, left: () => lBT[A], right: () => lBT[A])

import lBT.*

// a
def lBreadth[A](ltree: lBT[A]): LazyList[A] =
  def inner(queue: List[lBT[A]]): LazyList[A] =
    queue match
      case Nil => LazyList()
      case h :: t =>
        h match
          case LEmpty => inner(t)
          case LNode(value, left, right) => value #:: inner(t ::: List(left(), right()))
  inner(List(ltree))

// b
def foldBT[A, B](f: A => (B, B) => B)(acc: B)(bt: BT[A]): B =
  bt match
    case Empty => acc
    case Node(elem, left, right) => f(elem)(foldBT(f)(acc)(left), foldBT(f)(acc)(right))

def BT2lBT[A](bt: BT[A]): lBT[A] =
  foldBT[A, lBT[A]](elem => (left, right) => LNode(elem, () => left, () => right))(LEmpty)(bt)


lBreadth(BT2lBT(tt)).force == LazyList(1, 2, 3, 4, 5, 6)
lBreadth(LEmpty) == LazyList()
lBreadth(BT2lBT(tt2)).force == LazyList("1", "2", "3", "5")

// c
def lTree(n: Int): lBT[Int] = LNode(n, () => lTree(2 * n), () => lTree(2 * n + 1))

lBreadth(lTree(1)).take(20).toList == List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
lBreadth(LEmpty).take(20).toList == List()
lBreadth(lTree(5)).take(10).toList == List(5, 10, 11, 20, 21, 22, 23, 40, 41, 42)
