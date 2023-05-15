//// Kamila Sproska
//
//// Zad 1
//// a
//import scala.reflect.ClassTag
//import scala.reflect.ClassTag.{Any, Null}
//class FullException(msg: String) extends Exception(msg)
//
//abstract class MyQueue[E]:
//  @throws[FullException]
//  def enqueue(x: E): Unit
//
//  def dequeue: Unit
//
//  @throws[NoSuchElementException]
//  def first: E
//
//  def isEmpty: Boolean
//
//  def isFull: Boolean
//    override def toString: String
//
//
//class QueueMut[E](private val size: Int,
//                  private var queue: List[E],
//                  private var firstInx: Int,
//                  private var endInx: Int
//                 ) extends MyQueue[E] {
//  override def isFull: Boolean = (endInx + 1) % size == firstInx
//
//  override def isEmpty: Boolean = endInx == firstInx
//  override def enqueue(x: E): Unit =
//    if !isFull then {
//      queue = queue.updated(endInx, x)
//      endInx = (endInx + 1) % size
//    }
//
//  override def dequeue: Unit =
//    if !isEmpty then {
//      queue = queue.updated(firstInx, ClassTag.Null.asInstanceOf[E])
//      firstInx = (firstInx + 1) % size
//    } else throw new NoSuchElementException("Queue is empty")
//
//  override def first: E =
//    if isEmpty then throw new NoSuchElementException("Queue is empty")
//    else queue(firstInx)
//
//  override def toString: String =
//    s"$queue; firstElem: $first; firstInx: $firstInx; endInx: $endInx, size: ${size - 1}+1"
//}
//
//// b
//object QueueMut:
//  def apply[E: ClassTag](xs: E*): QueueMut[E] = new QueueMut[E](
//    xs.size + 1, xs.toList ::: ClassTag.Null.asInstanceOf[E] :: Nil, 0, xs.size
//  )
////  def empty[E: ClassTag](capacity: Int = 1000): QueueMut[E] = new QueueMut[E](capacity + 1, List[E].fill(capacity + 1, ClassTag.Null.asInstanceOf[E]), 0)
//
//
//val x = QueueMut(1, 2, 3)
//x.isFull
//x.first == 1
//x.dequeue
//!x.isFull
//!x.isEmpty
//x.first == 2
//x.dequeue
//x.first == 3
//x
//x.dequeue
//x.isEmpty
//x.enqueue(4)
//x.first == 4
//x.enqueue(5)
//
////val e = QueueMut.empty[Int](10)
////e.enqueue(10)
