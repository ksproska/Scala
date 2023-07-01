// Kamila Sproska
object Logging {
  def log(msg: String): Unit = println(s"${Thread.currentThread.getName}: $msg")
}

// Zadanie 1
// a
object Zad1a {
  import rx.lang.scala.Observable
  import scala.concurrent.duration._
  import Logging.log

  def main(args: Array[String]): Unit = {
    val fives = Observable.interval(5.seconds).map(n => (n + 1) * 5)
    val twelves = Observable.interval(12.seconds).map(n => (n + 1) * 12)

    fives.merge(twelves)
      .filter(_ % 30 != 0)
      .take(15)
      .subscribe(n => log(s"$n"), e => log(s"unexpected $e"), () => log("Observable completed"))

    Thread.sleep(70000)
  }
}

// b
object Zad1b {
  import rx.lang.scala.Observable
  import scala.concurrent.duration._
  import Logging.log

  def main(args: Array[String]): Unit = {
    Observable.interval(1.seconds)
      .map(n => n + 1)
      .filter(n => (n % 5 == 0 || n % 12 == 0) && n % 30 != 0)
      .take(15)
      .subscribe(n => log(s"$n"), e => log(s"unexpected $e"), () => log("Observable completed"))

    Thread.sleep(70000)
  }
}
