// Kamila Sproska

// Zad 1
import java.util.concurrent.Semaphore
import scala.annotation.tailrec
object Zad1:
  var counter = 0 // counter variable
  def readWriteCounter: Unit =
    counter += 1
  def main(args: Array[String]): Unit =
    val p = new Thread(() => for _ <- 0 until 200000 do readWriteCounter)
    val q = new Thread(() => for _ <- 0 until 200000 do readWriteCounter)
    val startTime = System.nanoTime
    p.start; q.start
    p.join; q.join
    val estimatedTime = (System.nanoTime - startTime)/1000000
    println(s"Value of the counter = $counter")
    println(s"Estimated time = ${estimatedTime}ms, Available processors = ${Runtime.getRuntime.availableProcessors}")

// a
// przykładowy scenariusz błędu
// 1: wątek 1 odczytuje wartość n i zostaje wywłaszczony
// 2: wątek 2 odczytuje wartość n i zostaje wywłaszczony
// 3: wątek 1 zapisuje wartość n+1 i zostaje wywłaszczony
// 4: wątek 2 zapisuje wartość n+1 i zostaje wywłaszczony
// zamiast wartości końcowej n+2 otrzymujemy n+1


// b
object Zad1b:
  var counter = 0 // counter variable
  def readWriteCounter: Unit =
    this.synchronized {
      counter += 1
    }

  def main(args: Array[String]): Unit =
    val p = new Thread(() => for _ <- 0 until 200000 do readWriteCounter)
    val q = new Thread(() => for _ <- 0 until 200000 do readWriteCounter)
    val startTime = System.nanoTime
    p.start; q.start
    p.join; q.join
    val estimatedTime = (System.nanoTime - startTime)/1000000
    println(s"Value of the counter = $counter")
    println(s"Estimated time = ${estimatedTime}ms, Available processors = ${Runtime.getRuntime.availableProcessors}")


// c
object Zad1c:
  val semaphore = new Semaphore(1)
  var counter = 0 // counter variable
  def readWriteCounter: Unit =
    semaphore.acquire
    counter += 1
    semaphore.release

  def main(args: Array[String]): Unit =
    val p = new Thread(() => for _ <- 0 until 200000 do readWriteCounter)
    val q = new Thread(() => for _ <- 0 until 200000 do readWriteCounter)
    val startTime = System.nanoTime
    p.start; q.start
    p.join; q.join
    val estimatedTime = (System.nanoTime - startTime)/1000000
    println(s"Value of the counter = $counter")
    println(s"Estimated time = ${estimatedTime}ms, Available processors = ${Runtime.getRuntime.availableProcessors}")


// Zad 2
object Zad2:
  def parallel[A, B](block1: => A, block2: => B): (A, B) =
    var a: Option[A] = None
    var b: Option[B] = None

    val threadA = new Thread(() => a = Some(block1))
    val threadB = new Thread(() => b = Some(block2))
    threadA.start; threadB.start
    threadA.join; threadB.join

    (a.get, b.get)

  def main(args: Array[String]): Unit =
    assert(parallel("a" + 1, "b" + 2) == ("a1", "b2"))
    println(parallel(Thread.currentThread.getName, Thread.currentThread.getName))

    assert(parallel(List(1, 2, 3).foldRight[Int](0)((acc, e) => acc + e), 0) == (6, 0))


// Zad 3
object Zad3:
  def periodically(duration: Long, times: Int)(block: => Unit): Unit =
    @tailrec
    def inner(times: Int): Unit =
      if times > 0 then
        block
        if times > 1 then Thread.sleep(duration)
        inner(times - 1)

    val thread = new Thread(() => inner(times))
    thread.setDaemon(true)
    thread.start

  def main(args: Array[String]): Unit =
    periodically(1000, 5) {
      print("y ")
    }
    periodically(1000, 25) {
      print("x ")
    }
    Thread.sleep(10000)
    println("Done sleeping")
