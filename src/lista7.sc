// Kamila Sproska

// Zadanie 1
class MyQueue[+T] private(private val out: List[T], private val inReversed: List[T]):
  def this() = this(Nil, Nil)

  def isEmpty: Boolean = out == Nil

  def enqueue[S >: T](x: S) =
    out match
      case Nil => new MyQueue[S](List(x), Nil)
      case _ :: _ => new MyQueue[S](out, x :: inReversed)

  def dequeue: MyQueue[T] =
    out match
      case Nil => this
      case _ :: Nil => new MyQueue[T](inReversed.reverse, Nil)
      case _ :: t => new MyQueue[T](t, inReversed)

  def first: T =
    out match
      case Nil => throw new NoSuchElementException("Queue is empty")
      case h :: _ => h

  def firstOption: Option[T] =
    out match
      case Nil => None
      case h :: _ => Some(h)

  override def toString: String = s"out: $out; inReversed: $inReversed"
end MyQueue

object MyQueue:
  def apply[T](elems: T*) = new MyQueue(elems.toList, Nil)

  val empty = new MyQueue(Nil, Nil)
end MyQueue

val qEmpty = MyQueue.empty
qEmpty.isEmpty

val qEmpty2 = MyQueue()
qEmpty2.isEmpty

val qEmpty3 = new MyQueue
qEmpty3.isEmpty

val q1 = MyQueue(1, 2, 3)
!q1.isEmpty
val q2 = q1.enqueue(1.2)
val q3 = q2.enqueue("a")
val q4 = q3.dequeue
val q5 = q4.dequeue
val q6 = q5.dequeue
q6.first == 1.2
q6.firstOption == Option(1.2)
val q7 = q6.dequeue
!q7.isEmpty
val q8 = q7.dequeue
q8.isEmpty
q8.firstOption == Option.empty

val newQueue = MyQueue.empty
newQueue.enqueue(10).first == 10


// zadanie 2
enum BT[+A]:
  case Empty
  case Node(elem: A, left: BT[A], right: BT[A])

import BT.*
object Lista7:
  def main (args: Array[String]): Unit = ???
  def breadthBT[A] = ???

  val t = Node(1,Node(2,Empty,Node(3,Empty,Empty)),Empty)
//  val tt = … drzewo z wykładu 5, str. 18

//object Lista7:
//  def main(args: Array[String]): Unit =
//    println((new MyQueue).isEmpty)
//
//  def breadthBT[A](xt: BT[A]) =
//    def inner(q: MyQueue[BT[A]]): List[A] =
//      if q.isEmpty then Nil
//      else q.first match
//        case Empty => inner(q.dequeue)
//        case Node(e, l, r) => e :: inner(q.dequeue.enqueue(l).enqueue(r))
//
//    inner(MyQueue(xt))
//
//  val t = Node(1, Node(2, Empty, Node(3, Empty, Empty)), Empty)
//  val tt = Node(1, Node(2, Node(4, Empty, Empty), Empty), Node(3, Node(5, Empty, Node(6, Empty, Empty)), Empty))

