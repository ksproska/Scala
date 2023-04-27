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


// zadanie 2
enum BT[+A]:
  case Empty
  case Node(elem: A, left: BT[A], right: BT[A])

import BT.*
object Lista7:
  def main (args: Array[String]): Unit =
    // proste testy dla kolejki
    val qEmpty = MyQueue.empty
    println(qEmpty.isEmpty)

    val qEmpty2 = MyQueue()
    println(qEmpty2.isEmpty)

    val qEmpty3 = new MyQueue
    println(qEmpty3.isEmpty)

    val q1 = MyQueue(1, 2, 3)
    println(!q1.isEmpty)
    val q2 = q1.enqueue(1.2)
    val q3 = q2.enqueue("a")
    val q4 = q3.dequeue
    val q5 = q4.dequeue
    val q6 = q5.dequeue
    println(q6.first == 1.2)
    println(q6.firstOption == Option(1.2))
    val q7 = q6.dequeue
    println(!q7.isEmpty)
    val q8 = q7.dequeue
    println(q8.isEmpty)
    println(q8.firstOption == Option.empty)

    val newQueue = MyQueue.empty
    println(newQueue.enqueue(10).first == 10)

    // testy dla drzewa
    println(breadthBT(t) == List(1, 2, 3))
    println(breadthBT(tt) == List(1, 2, 3, 4, 5, 6))

  def breadthBT[A](xt: BT[A]) =
    def inner(q: MyQueue[BT[A]]): List[A] =
      q.firstOption match
        case None => Nil
        case Some(Empty) => inner(q.dequeue)
        case Some(Node(e, l, r)) => e :: inner(q.dequeue.enqueue(l).enqueue(r))
    inner(MyQueue(xt))

  val t = Node(1,Node(2,Empty,Node(3,Empty,Empty)),Empty)
  val tt = Node(1, Node(2, Node(4, Empty, Empty), Empty), Node(3, Node(5, Empty, Node(6, Empty, Empty)), Empty))
