//string interpolation
val age = 12
val name = "Kamila"
s"$name is $age"

// blocks
{
  val x1 = 1
  val x = x1 + x1
  val y = 2
  x + x +
    { val x = 10.0
      x+y
    }
} + 1.0

// tuple
val k3 = (3+4, 2.0, 2<4)
k3._1

// list
val x1 = 1
val xs = 1.0 :: x1 :: 2.5 :: Nil
xs.head
xs.tail
xs.length

xs ::: List(9.0,10.0) //append
List(1,2,3).reverse

// functions
val double = (x: Int) => 2*x // function
def twice (x :Int) = 2*x // method
double(6)
double(2 + 3)
double(2) + 3

((x: Int) => 2*x) (6) // anonymous fun

val s = 2.toString
def silnia(n: Int): Int = {
  require(n >= 0, s"negative argument: $n")
  if n==0 then 1
  else n*silnia(n-1) // this is scala 2 - we need scala 3 !!!
  //  else if (n > 0) n*silnia(n-1) // this is scala 2 - we need scala 3 !!!
  //  else throw new IllegalArgumentException(s"ujemny argument: $n")
}
silnia(10)
//silnia(-4)

// polymorphic functions
def id[A](x:A) = x
id((true, 2.5))

def last[A](xs:List[A]):A =
  if xs == Nil then
    throw new NoSuchElementException("last of empty list")
  else xs.reverse.head
last(List(1, 2, 3, 4))