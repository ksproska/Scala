// Kamila Sproska

// Zadanie 1
enum BT[+A]:
  case Empty
  case Node(elem: A, left: BT[A], right: BT[A])

import BT.*

import scala.annotation.tailrec

def sumBT(t: BT[Int]): Int =
  t match
    case Empty => 0
    case Node(elem, left, right) => elem + sumBT(left) + sumBT(right)


val t = Node(1, Node(2, Empty, Node(3, Empty, Empty)), Empty)
sumBT(t) == 6
val t2 = Node(2,Node(4,Empty,Node(6,Empty,Empty)),Empty)
sumBT(t2) == 12
sumBT(Empty) == 0


// Zadanie 2
def foldBT[A, B](f: A => (B, B) => B)(acc: B)(bt: BT[A]): B =
  bt match
    case Empty => acc
    case Node(elem, left, right) => f(elem)(foldBT(f)(acc)(left), foldBT(f)(acc)(right))


foldBT((x: Int) => (y: Int, z: Int) => x + y + z)(0)(t) == 6
foldBT((x: Int) => (y: Int, z: Int) => x + y + z)(0)(t2) == 12
foldBT((x: Int) => (y: Int, z: Int) => x + y + z)(0)(Empty) == 0


// Zadanie 3
// a
def sumBTfold(t: BT[Int]): Int =
  foldBT[Int, Int](x => (y, z) => x + y + z)(0)(t)


sumBTfold(t) == 6
sumBTfold(t2) == 12
sumBTfold(Empty) == 0


// b
def inorderBTfold[A](bt: BT[A]): List[A] =
  foldBT[A, List[A]](x => (l, r) => l ::: x :: r)(Nil)(bt)


inorderBTfold(t) == List(2, 3, 1)
val t3 = Node("A", Node("B", Empty, Empty), Node("C", Empty, Empty))
inorderBTfold(t3) == List("B", "A", "C")
inorderBTfold(Empty) == Nil

def preorderBTfold[A](bt: BT[A]): List[A] =
  foldBT[A, List[A]](x => (l, r) => x :: l ::: r)(Nil)(bt)

preorderBTfold(t) == List(1, 2, 3)
preorderBTfold(t3) == List("A", "B", "C")
preorderBTfold(Empty) == Nil

def postorderBTfold[A](bt: BT[A]): List[A] =
  foldBT[A, List[A]](x => (l, r) => l ::: r ::: List(x))(Nil)(bt)

postorderBTfold(t) == List(3, 2, 1)
postorderBTfold(t3) == List("B", "C", "A")
postorderBTfold(Empty) == Nil


// Zadanie 4
def mapBT[A, B](f: A => B)(bt: BT[A]): BT[B] =
  foldBT[A, BT[B]](x => (y, z) => Node(f(x), y, z))(Empty)(bt)

mapBT[Int, Int](v => 2 * v)(t) == Node(2,Node(4,Empty,Node(6,Empty,Empty)),Empty)
mapBT[Int, Int](v => v * v)(t) == Node(1,Node(4,Empty,Node(9,Empty,Empty)),Empty)
mapBT[String, String](v => s"-$v-")(t3) == Node("-A-", Node("-B-", Empty, Empty), Node("-C-", Empty, Empty))
mapBT[Int, Int](v => v * v)(Empty) == Empty


// Zadanie 5
case class Graph[A](succ: A => List[A])
val g = Graph((i: Int) =>
  i match
    case 0 => List(3)
    case 1 => List(0, 2, 4)
    case 2 => List(1)
    case 3 => List(5)
    case 4 => List(0, 2)
    case 5 => List(3)
    case n => throw new NoSuchElementException(s"Graph g: node $n doesn't exist")
)

def pathExists[A](g: Graph[A])(from: A, to: A): Boolean =
  @tailrec
  def pathExistsRec(current: A, visited: List[A], queue: List[A]): Boolean =
    if current == to && visited != Nil then true
    else queue match
      case Nil => false
      case h::t => if visited.contains(current)
        then pathExistsRec(h, visited, t)
        else pathExistsRec(h, current::visited, t:::g.succ(current))
  pathExistsRec(from, Nil, g.succ(from))


pathExists(g)(4, 1)
!pathExists(g)(0, 4)
!pathExists(g)(3, 0)
pathExists(g)(2,2)
!pathExists(g)(0,0)
