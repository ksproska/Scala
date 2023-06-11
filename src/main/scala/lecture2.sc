import scala.annotation.tailrec

// Rekursja ogonowa
def succ(n: Int): Int =
  if n==0 then 1 else 1 + succ(n-1)

//succ(1000000) // StackOverflow

def succTail(n: Int) =
  @tailrec
  def succIter(n:Int, accum:Int): Int =
  if n==0 then accum else succIter(n-1, accum+1)
  succIter(n,1)

succTail(1000000)

// Ewaluacja gorliwa rekursji ogonowej
@tailrec
def factIt(n: Int, ak: Int): Int =
  if n == 0 then ak
  else factIt(n - 1, n * ak)

// Ewaluacja gorliwa rekursji
def factorial(n: Int): Int =
  if n == 0 then 1
  else n*factorial(n-1)

// Dopasowanie do wzorca
val xss = List("Ala", "ma", "kota")
val List(x1, x2, x3) = xss
val h::t = xss: @unchecked

val (z,_) = (false, 10)
val osoba = (("Kowal", 25), true)
val ((n,w),e) = osoba

// Parametry funkcji w Scali są zmiennymi
def f[A,B,C](k: ((A,B),C)) =
  val ((x,y),z) = k
  (x,y,(x,y),z)

f((2, 5.5), List(1,2))

// Match
def imply(pb:(Boolean,Boolean)) =
  pb match
    case (true,false) => false
    case _ => true

imply((true, true))


def zip[A,B](xs: List[A],ys: List[B]): List[(A,B)] =
  (xs,ys) match
    case (h1::t1, h2::t2) => (h1,h2)::zip(t1,t2)
    case _                => Nil

zip(List(1,2,3), List('a','b', 'c'))
zip(List(1,2,3), List('a','b'))

def unzip[A,B](ps: List[(A,B)]): (List[A],List[B]) =
  ps match
    case Nil => (Nil, Nil)
    case (h1,h2)::t => {val (xs1,xs2)=unzip(t); (h1::xs1, h2::xs2)}

unzip(List((1,2),(3,4),(5,6),(7,8)))

// Not implemented
def f(x: Int) = if x>0 then ??? else ???

// Wzorce dozorowanie
def srednia(p:(Double,Double)) =
  p match
    case (x,y) if x == y  => x // pattern guard
    case (x,y)            => (x+y)/2.0

srednia(5.0, 5.0)

// Współdzielenie danych niemodyfikowalnych
val xs = List(1,2)
val ys = xs
xs == ys // równość strukturalna
xs eq ys // równość tożsamości

val yss = {
  val h::t = xs: @unchecked;
  h::t
}
xs == yss // równość strukturalna
xs eq yss // równość tożsamości

// Wzorce warstwowe
val (a@(b,c),d) = (("Kowal", 25), true)
a == ("Kowal", 25)
b == "Kowal"

def f2[A,B,C](k: ((A,B),C)) =
  k match
    case (p@(x,y),z) => (x,y,p,z)

f2((2, 5.5), List(1,2))
