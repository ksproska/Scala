// Kamila Sproska

// zadanie 1
def suma(xss: List[Double]): Double =
  if xss == Nil then 0
  else suma(xss.tail) + xss.head


suma(Nil) == 0.0
suma(List(-1, 2, 3)) == 4.0
suma(List(5.6)) == 5.6


// zadanie 2
def lastElement[A](xss: List[A], last: A): A =
  if xss == Nil then last
  else lastElement(xss.tail, xss.head)

def ends[A](xss: List[A]): (A, A) =
  if xss == Nil then throw new NoSuchElementException("empty list")
  (xss.head, lastElement(xss.tail, xss.head))


ends(List(1, 3, 5, 6, 9)) == (1,9)
ends(List("Ala", "ma", "kota")) == ("Ala", "kota")
ends(List(1)) == (1,1)
//ends(Nil) //=>> wyjątek NoSuchElementException: empty list


// zadanie 3
def posortowana(xss: List[Int]): Boolean =
  if xss == Nil || xss.tail == Nil then true
  else xss.head <= xss.tail.head && posortowana(xss.tail)


posortowana(List(1,3,3,5,6,7)) == true
posortowana(List(1,3,3,10,6,7)) == false
posortowana(List(1, 1, 1)) == true
posortowana(List(5)) == true
posortowana(Nil) == true


// zadanie 4
def glue(xss: List[String], str: String): String =
  if xss == Nil then ""
  else if xss.tail == Nil then xss.head
  else s"${xss.head}$str${glue(xss.tail, str)}"


glue(List("To", "jest", "napis"), "-") == "To-jest-napis"
glue(Nil, "-") == ""
glue(List("1", "2", "3"), "/") == "1/2/3"
glue(List("1", "2", "3"), "") == "123"
