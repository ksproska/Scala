// Kamila Sproska

// Zad 1
// a
import scala.reflect.ClassTag
import scala.runtime.Null$

class FullException(msg: String) extends Exception(msg)

abstract class MyQueue[E]:
  @throws[FullException]
  def enqueue(x: E): Unit

  def dequeue: Unit

  @throws[NoSuchElementException]
  def first: E

  def isEmpty: Boolean

  def isFull: Boolean

  override def toString: String


class QueueMut[E: ClassTag](val size: Int = 1000) extends MyQueue[E] {
  private var firstInx: Int = 0
  private var endInx: Int = 0
  private val array_size = size + 1
  private val queue: Array[E] = new Array[E](array_size)

  override def isEmpty: Boolean = endInx == firstInx

  override def isFull: Boolean = (endInx + 1) % array_size == firstInx

  override def enqueue(x: E): Unit =
    if isFull then throw new FullException("Queue is full")
    queue(endInx) = x
    endInx = (endInx + 1) % array_size

  override def dequeue: Unit =
    if !isEmpty then {
      //      queue(firstInx) = Nil.asInstanceOf[E]
      firstInx = (firstInx + 1) % array_size
    }

  override def first: E =
    if isEmpty then throw new NoSuchElementException("Queue is empty")
    else queue(firstInx)

  override def toString: String =
    s"${queue.mkString("Array(", ", ", ")")}; firstElem: $first; firstInx: $firstInx; endInx: $endInx, size: ${array_size - 1}+1"
}

// b
object QueueMut {
  def apply[E: ClassTag](xs: E*): QueueMut[E] =
    xs.foldLeft(QueueMut.empty(xs.size))(
      (acc: QueueMut[E], elem: E) => {
        acc.enqueue(elem)
        acc
      }
    )

  def empty[E: ClassTag](capacity: Int = 1000): QueueMut[E] = new QueueMut[E](capacity)
}

object lista8 {
  def main(args: Array[String]): Unit = {
    val x = QueueMut(1, 2, 3)
    println(x.isFull)
    println(x.first == 1)
    x.dequeue
    println(!x.isFull)
    println(!x.isEmpty)
    println(x.first == 2)
    x.dequeue
    println(x.first == 3)
    x.dequeue
    println(x.isEmpty)
    x.enqueue(4)
    println(x.first == 4)
    x.enqueue(5)
  }
}
