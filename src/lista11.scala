// Kamila Sproska

import scala.concurrent.{Await, Future, Promise}
import concurrent.ExecutionContext.Implicits.global
import concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object Zad1a:
  def pairFutZip[A, B](fut1: Future[A], fut2: Future[B]): Future[(A, B)] = fut1 zip fut2

  def pairFut[A, B](fut1: Future[A], fut2: Future[B]): Future[(A, B)] =
    for
      res1 <- fut1
      res2 <- fut2
    yield (res1, res2)

  def main(args: Array[String]): Unit =
    val result = pairFutZip(Future {"Ala ma kota"}, Future {5})
    println(result)
    println(Await.result(result, 2.seconds))

    val result2 = pairFutZip(Future {Thread.sleep(1000); 10}, Future {5.4})
    println(result2)
    println(Await.result(result2, 2.seconds))

    val result3 = pairFut(Future {"Ala ma kota"}, Future {5})
    println(result3)
    println(Await.result(result3, 2.seconds))

    val result4 = pairFut(Future {Thread.sleep(1000); 10}, Future {5.4})
    println(result4)
    println(Await.result(result4, 2.seconds))


object Zad2:
  extension [T](self: Future[T])
    def existsProm(p: T => Boolean): Future[Boolean] =
      val prom = Promise[Boolean]
      self onComplete {
        case Success(v) => if p(v)
          then prom.success(true)
          else prom.success(false)
        case Failure(_) => prom.success(false)
      }
      prom.future

    def exists(p: T => Boolean): Future[Boolean] =
      self map p recover { case _: Throwable => false }

  def main(args: Array[String]): Unit =
    val fut1 = Future{-1}
    val fut2 = Future{3}
    val fut3 = Future[Int]{throw new Exception()}
    println(Await.result(fut1.existsProm(x => x < 0), 2.second))
    println(Await.result(fut2.existsProm(x => x < 0), 2.second))
    println(Await.result(fut3.existsProm(x => x < 0), 2.second))

    println(Await.result(fut1.exists(x => x < 0), 2.second))
    println(Await.result(fut2.exists(x => x < 0), 2.second))
    println(Await.result(fut3.exists(x => x < 0), 2.second))

object Zad3:

  import scala.concurrent.*
  import ExecutionContext.Implicits.global
  import scala.util.{Success, Failure}
  import scala.io.Source

  def main(args: Array[String]): Unit =
    val path = args(0)
    val promiseOfFinalResult = Promise[Seq[(String, Int)]]

    val future = scanFiles(path) flatMap processFiles
    promiseOfFinalResult.completeWith(future)

    promiseOfFinalResult.future onComplete {
      case Success(result) => result foreach println
      case Failure(t)
      => t.printStackTrace()
    }
    Thread.sleep(500)
  end main

  private def processFiles(fileNames: Seq[String]): Future[Seq[(String, Int)]] =
    Future.sequence(fileNames.map(fileName => processFile(fileName)))

  private def processFile(fileName: String): Future[(String, Int)] =
    Future {
      val f = Source.fromFile(fileName)
      try {
        (fileName, f.getLines.foldLeft(0)((acc, line) => acc + line.split(' ').length))
      } finally f.close()
    }

  private def scanFiles(docRoot: String): Future[Seq[String]] =
    Future {
      new java.io.File(docRoot).list.toIndexedSeq.map(docRoot + _)
    }

end Zad3
