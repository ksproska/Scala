// Kamila Sproska

import java.util.concurrent.{ArrayBlockingQueue, Semaphore}
import scala.concurrent.ExecutionContext
import scala.util.Random

def log(msg: String): Unit = println(s"${Thread.currentThread.getName}: $msg")

// zadanie 1
// a
object Zad1a:
  class Producer(name: String, buf: ArrayBlockingQueue[Integer]) extends Thread(name):
    override def run(): Unit =
      for i <- 1 to 10 do
        println(s"$getName producing $i"); buf.put(i)


  class Consumer(name: String, buf: ArrayBlockingQueue[Integer]) extends Thread(name):
    override def run(): Unit =
      for i <- 1 to 10 do println(s"$getName consumed ${ buf.take }")

  def main(args: Array[String]): Unit =
    val buf: ArrayBlockingQueue[Integer] = new ArrayBlockingQueue(5)
    new Producer("Producer", buf).start()
    new Consumer("Consumer", buf).start()

// b
object Zad1b:
  private val NUMB_OF_PRODUCERS = 2
  private val NUMB_OF_CONSUMERS = 3

  class Producer(name: String, buf: ArrayBlockingQueue[Integer]) extends Thread(name):
    override def run(): Unit =
      for i <- 1 to 10 do
        log(s"producing $i"); buf.put(i)


  class Consumer(name: String, buf: ArrayBlockingQueue[Integer]) extends Thread(name):
    override def run(): Unit =
      for i <- 1 to 10 do log(s"consumed ${buf.take}")


  def main(args: Array[String]): Unit =
    val buf: ArrayBlockingQueue[Integer] = new ArrayBlockingQueue(5)

    for i <- 1 to NUMB_OF_PRODUCERS do new Producer(s"Producer$i", buf).start()
    for i <- 1 to NUMB_OF_CONSUMERS do new Consumer(s"Consumer$i", buf).start()
    // program się niekończy ponieważ wyprodukowano mniej niż było zapotrzebowanie dla konsumentów
    // i konsumentów (jeden lub wielu) czekają aż pojawi się element w buforze uniemożliwiając zakończenie
    // programu


// c
object Zad1c:
  private val NUMB_OF_PRODUCERS = 2
  private val NUMB_OF_CONSUMERS = 3
  def main(args: Array[String]): Unit =
    val buf: ArrayBlockingQueue[Integer] = new ArrayBlockingQueue(5)

    val execCtx = ExecutionContext.global

    for (p <- 1 to NUMB_OF_PRODUCERS)
      execCtx.execute(() =>
        for i <- 1 to 10 do {
          log(s"Producer$p producing $i")
          buf.put(i)
        }
      )

    for (c <- 1 to NUMB_OF_CONSUMERS)
      execCtx.execute(() =>
        for i <- 1 to 10 do {
          log(s"Consumer$c consumed ${buf.take}")
        }
      )
    Thread.sleep(1000)
    println("Stopped sleeping")
    // metoda pozwala na uruchomienie metody w niezależnym wątku który używa deamona - oznacza to, że wątek
    // jest uruchomiony w tle i nie blokuje zakończenia programu, więc po upływie czasu wszystkie wątki zostają przerwane


// Zadanie 2
object Zad2:
  val DURATION_MIN = 100
  val DURATION_MAX = 1000
  private val N = 10

  val doorman = new Semaphore(N - 1, true)
  val utils: Array[Semaphore] = new Array[Semaphore](N)
  class Philosopher(name: String, leftUtil: Semaphore, rightUtil: Semaphore) extends Thread(name):
    def eat(): Unit =
      doorman.acquire()
      println(s"$name went inside")
      leftUtil.acquire()
      println(s"$name picked up left")
      rightUtil.acquire()
      println(s"$name picked up right")
      val duration = Random.nextInt(DURATION_MAX - DURATION_MIN) + DURATION_MIN
      println(s"$name is eating for $duration ms")
      Thread.sleep(duration)
      println(s"$name finished eating")
      leftUtil.release()
      rightUtil.release()
      doorman.release()
      println(s"$name went outside")

    def meditate(): Unit =
      val duration = Random.nextInt(DURATION_MAX - DURATION_MIN) + DURATION_MIN
      println(s"$name is meditating for $duration ms")
      Thread.sleep(duration)
      println(s"$name finished meditating")

    override def run(): Unit =
      while true do
        meditate()
        eat()

  def main(args: Array[String]): Unit =
    for (i <- 0 until N) utils(i) = new Semaphore(1, true)
    for p <- 0 until N do
      val leftUtensil = utils(p)
      val rightUtensil = utils((p + 1) % N)
      new Philosopher(s"Philosopher$p", leftUtensil, rightUtensil).start()
