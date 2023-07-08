# Wszystkie listy:

## [Lista 0](/src/main/scala/lista0.sc)
### [Zad 1](/src/main/scala/lista0.sc#L3)
Napisz funkcję rekurencyjną `last[A](xs: List[A]):` A zwracającą ostatni element zadanej listy,

np.
```scala worksheet
last (List(1, 9, 5, 6, 3)) == 3
last(List("Ala", "ma", "kota")) == "kota"
last (List(5)) == 5
last(Nil) =>> java.util.NoSuchElementException: last of empty list
```
Można używać tylko funkcji head i tail!

## [Lista 1](/src/main/scala/lista1.sc)
### [Zad 1](/src/main/scala/lista1.sc#L3)
Napisz funkcję suma: `List[Double] => Double`, zwracającą sumę liczb z podanej listy,

np. 
```scala worksheet
suma(Nil) == 0.0
suma(List(-1, 2, 3)) == 4.0
suma(List(5.6)) == 5.6
```

### [Zad 2](/src/main/scala/lista1.sc#L15)
Napisz funkcję `ends[A](xs: List[A]): (A, A)`, zwracającą parę, zawierającą pierwszy
i ostatni element zadanej listy,

np.
```scala worksheet
ends(List(1, 3, 5, 6, 9)) == (1,9)
ends(List("Ala", "ma", "kota")) == ("Ala", "kota")
ends(List(1)) == (1,1)
ends(Nil) =>> wyjątek NoSuchElementException: empty list
```

### [Zad 3](/src/main/scala/lista1.sc#L30)
Napisz funkcję posortowana: List[Int] => Boolean sprawdzającą, czy dana lista jest
posortowana niemalejąco,

np.
```scala worksheet
posortowana(List(1,3,3,5,6,7)) == true
```

### [Zad 4](/src/main/scala/lista1.sc#L43)
Napisz funkcję `glue: (List[String], String) => String`, która na wejściu przyjmuje listę napisów
oraz napis będący separatorem i zwraca napis będący połączeniem napisów wejściowych
oddzielonych od siebie zadanym separatorem,

np.
```scala worksheet
glue(List("To", "jest", "napis"), "-") == "To-jest-napis"
glue(Nil, "-") == ""
```

## [Lista 2](/src/main/scala/lista2.sc)
### [Zad 1](/src/main/scala/lista2.sc#L4)
Napisz funkcję `take[A](n: Int, xs: List[A]): List[A]`,
gdzie `take(k, List(x1, ..., xn)) == List(x1, ..., xk)`,

np.
```scala worksheet
take(2, List(1,2,3,5,6)) == List(1,2)
take(-2, List(1,2,3,5,6)) == Nil
take(8, List(1,2,3,5,6)) == List(1,2,3,5,6)
```

### [Zad 2](/src/main/scala/lista2.sc#L19)
Napisz funkcję `drop[A](n: Int, xs: List[A]): List[A]`,
gdzie `drop(k, List(x1 , ..., xn)) == List(xk+1, ..., xn)`,

np.
```scala worksheet
drop(2, List(1,2,3,5,6)) == List(3,5,6)
drop(-2, List(1,2,3,5,6)) == List(1,2,3,5,6)
drop(8, List(1,2,3,5,6)) == Nil
```

### [Zad 3](/src/main/scala/lista2.sc#L34)
Napisz funkcję `reverse[A](xs: List[A]): List[A]`, odwracającą zadaną listę w czasie liniowym
(bez użycia metody bibliotecznej reverse!),
np.
```scala worksheet
reverse(List("Ala", "ma", "kota")) == List("kota", "ma", "Ala")
```

### [Zad 4](/src/main/scala/lista2.sc#L50)
Napisz funkcję `replicate: List[Int] => List[Int]`, która z danej listy liczb naturalnych tworzy
listę, w której każdy element wejściowej listy jest tyle razy powtórzony, jaką ma wartość,

np.
```scala worksheet
replicate (List(1,0,4,-2,3)) == List(1, 4, 4, 4, 4, 3, 3, 3)
```

### [Zad 5](/src/main/scala/lista2.sc#L74)
Dla zadanej liczby rzeczywistej `a` oraz dokładności `ε` można znaleźć pierwiastek
trzeciego stopnia z `a` wyliczając kolejne przybliżenia `xi` tego pierwiastka (metoda
Newtona-Raphsona):

dla `a > 1`:
```math
x_0 = \frac{a}{3}
```

dla `a ≤ 1`:
```math
x_0 = a
```

```math
x_{i+1} = x_i + (\frac{a}{x_i^2} – x_i)/3
```
Dokładność (względna) jest osiągnięta, jeśli 
```math
|x_i^3 – a| ≤ ε * |a|
```
Napisz efektywną (**wykorzystującą rekursję ogonową**) funkcję `root3: Double => Double`,
która dla zadanej liczby `a` znajduje pierwiastek trzeciego stopnia z dokładnością względną `ε=10^-15`.

## [Lista 3](/src/main/scala/lista3.sc)
### [Zad 1](/src/main/scala/lista3.sc#L3)
Napisz funkcję `exists[A] (xs: List[A]) (p: A => Boolean): Boolean`.
exists (xs) (p) ma wartość logiczną zdania `∃x∈xs.p(x)”`

np. 
```scala worksheet
exists (List(5,1,2,3)) (_ == 2)
```
Należy napisać trzy wersje tej funkcji:\
a) z wykorzystaniem dopasowania do wzorca i rekursji,\
b) z wykorzystaniem metody `List.foldLeft`, \
c) z wykorzystaniem metody `List.foldRight`.

### [Zad 2](/src/main/scala/lista3.sc#L42)
Napisz funkcję `filter[A](xs: List[A])(p: A => Boolean): List[A]` wykorzystując funkcjonał
`List.foldRight`.
np.
```scala worksheet
filter (List(2,7,1,3,7,8,4,1,6,9)) (_ > 3) == List(7, 7, 8, 4, 6, 9)
```

### [Zad 3](/src/main/scala/lista3.sc#L55)
Napisz funkcję `remove1[A](xs: List[A])(p: A => Boolean): List[A]` zwracającą listę z tymi
samymi wartościami, co lista `xs`, z której usunięto pierwszy element spełniający predykat `p`.

np.
```scala worksheet
remove1(List(1,2,3,2,5)) (_ == 2) == List(1, 3, 2, 5)
```
Należy napisać dwie wersje tej funkcji: \
a) ze zwykłą rekursją, \
b) z możliwie efektywną rekursją ogonową (użyj `List.reverse_:::`).

### [Zad 4](/src/main/scala/lista3.sc#L87)
Napisz funkcję `splitAt[A](xs: List[A])(n: Int): (List[A], List[A])`, zwracającą parę równą
`(xs take n, xs drop n)`, ale bez dwukrotnego przechodzenia listy `xs`.

np.
```scala worksheet
splitAt (List('a','b','c','d','e')) (2) == (List('a', 'b'), List('c', 'd', 'e'))
```

## [Lista 4](/src/main/scala/lista4.sc)
### [Zad 1](/src/main/scala/lista4.sc#L3)
Zdefiniuj swoją klasę dla modyfikowalnej pary polimorficznej `MyPair[A, B]`. Ma ona mieć dwa pola
modyfikowalne `fst` i `snd` z odpowiednimi akcesorami i mutatorami, oraz metodę `toString`, zwracającą
napis w formacie `(fst, snd)`.

### [Zad 2](/src/main/scala/lista4.sc#L14)
Klasa `BankAccount` jest zdefiniowana następująco:
```scala worksheet
class BankAccount(initialBalance: Double):

private var balance = initialBalance
def checkBalance = balance
def deposit(amount: Double) = {
  balance += amount; balance
}
def withdraw(amount: Double) = {
  balance -= amount; balance
}
override def toString = "%.2f".format(balance)
```

W podpunktach a) i b) należy utworzyć odpowiednie konto i przeprowadzić na nim testy.

a) Zdefiniuj klasę `CheckingAccount`, rozszerzającą klasę `BankAccount`, w której pobierana jest opłata w
wysokości 1$ za każdą wpłatę i wypłatę z konta. Zmodyfikuj odpowiednio metody `deposit` i `withdraw`.

b) Zdefiniuj klasę `SavingsAccount`, rozszerzającą klasę `BankAccount`, w której co miesiąc do konta
dodawane jest oprocentowanie 1%, a w przypadku debetu pobierana jest opłata w wysokości 1% . Nie
chodzi tu o korzystanie z kalendarza. Dodaj metodę `earnMonthlyInterest: Unit`; jej użycie oznacza, że
upłynął miesiąc. \
Trzy transakcje miesięcznie są bezpłatne, za pozostałe pobierana jest opłata w wysokości 1$.
Zmodyfikuj odpowiednio metody `deposit` i `withdraw`.

### [Zad 3](/src/main/scala/lista4.sc#L70)
a) Zdefiniuj abstrakcyjną klasę `Zwierz`, z dokładnie jednym niemodyfikowalnym polem imie. Jej
konstruktor(y) ma(ją) umożliwiać tworzenie zwierzęcia z domyślnym imieniem (np. „bez imienia”)
lub z imieniem, podanym jako argument. \
Klasa ma mieć cztery publiczne bezargumentowe metody: `imie`, `rodzaj`, `dajGlos` i `toString`, które
odpowiednio zwracają: imię, rodzaj i charakterystyczny głos zwierzęcia. \
Metoda `toString` ma zwracać informację o zwierzęciu w poniższym formacie: \
`<rodzaj zwierzęcia> <imię zwierzęcia> daje głos <charakterystyczny głos zwierzęcia>!` \
Na przykład (dla psa): \
`Pies Kruczek daje głos Hau, hau!` \
Zdecyduj, które metody muszą być abstrakcyjne.

b) Zdefiniuj klasy publiczne dla kilku rodzajów zwierząt (co najmniej dwóch), dziedziczące
z klasy `Zwierz`. Podobnie jak klasa `Zwierz` mają one umożliwiać tworzenie zwierzęcia na dwa sposoby:
bez podanego imienia i z imieniem. **Nie wolno dodawać żadnych nowych pól ani metod!** Które
metody trzeba zdefiniować, które zastąpić, a które odziedziczyć bez zmian?

c) Napisz program testujący `TestZwierza` (jako obiekt singletonowy), w którym należy utworzyć
kolekcję kilku zwierząt (użyj kolekcji `Vector`) i wypisać informacje o tych zwierzętach (wykorzystaj
pętlę `for ... do`). Zaobserwuj efekt działania polimorfizmu inkluzyjnego i wiązania dynamicznego.
W arkuszu elektronicznym (worksheet) program uruchamia się tak: `TestZwierza.main(Array())`

## [Lista 5](/src/main/scala/lista5.sc)
Na wykładzie zostały zdefiniowane drzewa binarne:
```scala worksheet
enum BT[+A]:
    case Empty
    case Node(elem: A, left: BT[A], right: BT[A])
```
Po zaimportowaniu obiektu towarzyszącego `BT` można zdefiniować drzewo:
```scala worksheet
val t = Node(1, Node(2, Empty, Node(3, Empty, Empty)), Empty)
```
### [Zad 1](/src/main/scala/lista5.sc#L3)
Napisz funkcję `sumBT: BT[Int] => Int`, która oblicza sumę liczb całkowitych,
przechowywanych w węzłach drzewa, np. 
```scala worksheet
sumBT(t) == 6
```
### [Zad 2](/src/main/scala/lista5.sc#L25)
Napisz funkcjonał
```scala worksheet
foldBT[A, B](f: A => (B, B) => B)(acc: B)(bt: BT[A]): B
```
uogólniający funkcję sumowania wartości z węzłów drzewa binarnego tak, jak funkcjonał
`foldRight` uogólnia funkcję sumowania elementów listy. Typ `(B, B)` to typ pary akumulatorów dla
lewego i prawego poddrzewa.

### [Zad 3](/src/main/scala/lista5.sc#L37)
Wykorzystaj `foldBT` do zdefiniowania:

a) sumy liczb całkowitych `sumBTfold: BT[Int] => Int`, np. 
```scala worksheet
sumBTfold(t) == 6
```
b) listy wartości pamiętanych w węzłach drzewa w obejściu: \
infiksowym - `inorderBTfold[A](bt: BT[A]): List[A]` \
prefiksowym - `preorderBTfold[A](bt: BT[A]): List[A]` \
postfiksowym - `postorderBTfold[A](bt: BT[A]): List[A]` \
np. 
```scala worksheet
inorderBTfold(t) == List(2, 3, 1)
preorderBTfold(t) == List(1, 2, 3)
postorderBTfold(t) == List(3, 2, 1)
```
### [Zad 4](/src/main/scala/lista5.sc#L73)
Wykorzystując `foldBT` zdefiniuj funkcjonał
```scala worksheet
mapBT[A, B](f: A => B)(bt: BT[A]): BT[B]
```
aplikujący daną funkcję do wartości we wszystkich węzłach drzewa.
np. 
```scala worksheet
mapBT[Int, Int](v => 2 * v)(t) == Node(2,Node(4,Empty,Node(6,Empty,Empty)),Empty)
```
### [Zad 5](/src/main/scala/lista5.sc#L83)
Na wykładzie zostały zdefiniowane grafy:
```scala worksheet
case class Graph[A](succ: A => List[A])
```
Napisz funkcję: `pathExists[A](g: Graph[A])(from: A, to: A): Boolean` sprawdzającą, czy istnieje
droga pomiędzy zadanymi wierzchołkami grafu. np. dla poniższego grafu g:
```scala worksheet
val g = Graph((i: Int) =>
  i match
    case 0 => List(3)
    case 1 => List(0, 2, 4)
    case 2 => List(1)
    case 3 => List(5)
    case 4 => List(0, 2)
    case 5 => List(3)
    case n => throw new NoSuchElementException(s"Graph g: node $n doesn't exist")
)
pathExists(g)(4, 1)
!pathExists(g)(0, 4)
!pathExists(g)(3, 0)
pathExists(g)(2,2)
!pathExists(g)(0,0)
```
## [Lista 6](/src/main/scala/lista6.sc)
### [Zad 1](/src/main/scala/lista6.sc#L4)
Jedna z pętli w języku Scala 2 ma następującą składnię: `while (warunek) wyrażenie`, np.
```scala worksheet
var count = 0
while (count < 5) {
  println(count)
  count += 1
}
```
Napisz funkcję `whileLoop` (**bez używania efektów obliczeniowych**), która pobiera dwa
argumenty: warunek oraz wyrażenie i dokładnie symuluje działanie pętli `while` (również
składniowo). Jakiego typu (i dlaczego) muszą być argumenty i wynik funkcji? Przetestuj
jej działanie, symulując powyższą pętlę.

### [Zad 2](/src/main/scala/lista6.sc#L18)
Napisz funkcję `lrepeat [A] (k: Int) (xsl: LazyList[A]): LazyList [A]`, która dla danej
dodatniej liczby całkowitej `k` i listy leniwej `LazyList (x0, x1, x2, x3, ... )` zwraca listę leniwą,
w której każdy element `xi` jest powtórzony `k` razy, np.
```scala worksheet
lrepeat(3)(LazyList('a','b','c','d')).toList == List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'd', 'd', 'd')
lrepeat(3)(LazyList.from(1)).take(15).toList == List(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5)
lrepeat(3)(LazyList()).take(15).toList == List()
```
### [Zad 3](/src/main/scala/lista6.sc#L35)
Na wykładzie 5 zostały zdefiniowane polimorficzne drzewa binarne:
```scala worksheet
enum BT[+A]:
    case Empty
    case Node(elem: A, left: BT[A], right: BT[A])
import BT.*
val tt = Node(1,Node(2,Node(4,Empty,Empty),Empty),Node(3,Node(5,Empty,Node(6,Empty,Empty)),Empty))
```
Polimorficzne leniwe drzewa binarne można zdefiniować następująco:
```scala worksheet
enum lBT[+A]:
    case LEmpty
    case LNode(elem: A, left: () => lBT[A], right: () => lBT[A])
```
a) Napisz funkcję `lBreadth[A](ltree: lBT[A]): LazyList [A]`, która tworzy listę leniwą zawierającą
wszystkie wartości węzłów danego leniwego drzewa binarnego. \
_Wskazówka:_ zastosuj obejście drzewa wszerz, reprezentując kolejkę jako zwykłą listę.

b) Wykorzystując `foldBT` z listy 5 zdefiniuj funkcję `BT2lBT[A](bt: BT[A]): lBT[A]`, konwertującą
zwykłe drzewo binarne do leniwego drzewa binarnego. Wykorzystaj ją w testach funkcji
funkcji `lBreadth`, 
```scala worksheet
lBreadth(BT2lBT(tt)).force == LazyList(1, 2, 3, 4, 5, 6)
lBreadth(LEmpty) == LazyList()
```
c) Napisz funkcję `lTree(n: Int): lBT[Int]`, która dla zadanej liczby naturalnej `n` konstruuje
nieskończone leniwe drzewo binarne z korzeniem o wartości `n` i z dwoma poddrzewami
`lTree(2*n)` oraz `lTree(2*n+1)`. \
To drzewo jest przydatne do testowania funkcji `lBreadth`, np.
```scala worksheet
lBreadth(lTree(1)).take(20).toList == List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
lBreadth(LEmpty).take(20).toList == List()
```
## [Lista 7](/src/main/scala/lista7.scala)
Sygnatura
```
empty      : -> Queue
enqueue    : Elem * Queue -> Queue
first      : Queue -> Elem
firstOption: Queue -> Option[Elem]
dequeue    : Queue -> Queue
isEmpty    : Queue -> bool
```
Aksjomaty
```
For all q:Queue, e1,e2: Elem
isEmpty (enqueue (e1,q))               = false
isEmpty (empty)                        = true
dequeue (enqueue(e1,enqueue(e2,q)))    = enqueue(e1,dequeue(enqueue(e2,q)))
dequeue (enqueue(e1,empty))            = empty
dequeue (empty)                        = empty
first (enqueue(e1,enqueue(e2,q)))      = first(enqueue(e2,q))
first (enqueue(e1,empty))              = e1
first (empty)                          = ERROR
firstOption(enqueue(e1,enqueue(e2,q))) = firstOption(enqueue(e2,q))
firstOption(enqueue(e1,empty))         = Some(e1)
firstOption(empty)                     = None
```
### [Zad 1](/src/main/scala/lista7.scala#L3)
Zdefiniuj klasę generyczną dla <ins>kowariantnej kolejki niemodyfikowalnej</ins>, reprezentowanej
przez dwie listy. \
W ten sposób reprezentowane są kolejki niemodyfikowalne w językach czysto
funkcyjnych, a także w Scali (patrz dokumentacja). \
_Wskazówka_. Wzoruj się na klasie dla stosu z wykładu 7 (str. 8 i 27) oraz dokumentacji
`scala.collection.immutable.Queue` (zaimplementuj tylko metody z powyższej specyfikacji).
Zdefiniuj obiekt towarzyszący z metodami `apply` i `empty`. \
Utworzenie nowej kolejki ma być możliwe na cztery sposoby:

```scala worksheet
new MyQueue
MyQueue()
MyQueue.empty
MyQueue('a', 'b', 'c')
```

Para list `([x1; x2; ...; x m], [y1; y2; ...; yn])` reprezentuje kolejkę `x1 x2 ... xm yn ... y2 y1`. Pierwsza lista
reprezentuje początek kolejki, a druga – koniec kolejki. Elementy w drugiej liście są zapamiętane
w odwrotnej kolejności, żeby wstawianie było wykonywane w czasie stałym (na początek listy). \
`enqueue(y, q)` modyfikuje kolejkę następująco: `(xl, [y1; y2; ...; yn])  (xl, [y;y 1; y2; ...; yn])`. Elementy
w pierwszej liście są pamiętane we właściwej kolejności, co umożliwia szybkie usuwanie pierwszego
elementu. `dequeue(q)` modyfikuje kolejkę następująco: `([x1; x2; ...; xm], yl)  ([x2; ...; xm], yl)`. Kiedy
pierwsza lista zostaje opróżniona, druga lista jest odwracana i wstawiana w miejsce pierwszej: \
`([], [y1; y2; ...; yn ])  ([yn; ... y2; y1], [])`. Reprezentacja kolejki jest w postaci normalnej, jeśli nie
wygląda tak: `([], [y1; y2 ; ...; yn ])` dla n1. Wszystkie operacje kolejki mają zwracać reprezentację
w postaci normalnej, dzięki czemu pobieranie wartości pierwszego elementu nie spowoduje
odwracania listy. Odwracanie drugiej listy po opróżnieniu pierwszej też może się wydawać
kosztowne. Jeśli jednak oszacujemy nie koszt pesymistyczny (oddzielnie dla każdej operacji kolejki),
ale koszt zamortyzowany (uśredniony dla całego czasu istnienia kolejki), to okaże się, że
zamortyzowany koszt operacji wstawiania i usuwania z kolejki jest stały. Para list to
najefektywniejsza reprezentacja kolejki niemutowalnej.

### [Zad 2](/src/main/scala/lista7.scala#L40)
```scala worksheet
enum BT[+A]:
    case Empty
    case Node(elem: A, left: BT[A], right: BT[A])
import BT.*
```
Dla drzew binarnych, zdefiniowanych powyżej, napisz funkcję `breadthBT[A] : BT[A] => List[A]`
obchodzącą drzewo binarne wszerz i zwracającą listę wartości, przechowywanych w węzłach
drzewa. Wykorzystaj kolejkę z zadania 1.

## [Lista 8](/src/main/scala/lista8.scala)
### [Zad 1](/src/main/scala/lista8.scala#L3)
Dana jest następująca klasa abstrakcyjna dla generycznych kolejek modyfikowalnych.
```scala worksheet
class FullException(msg: String) extends Exception(msg)

abstract class MyQueue[E]

@throws[FullException]
def enqueue(x: E): Unit
def dequeue: Unit

@throws[NoSuchElementException]
def first: E
def isEmpty: Boolean
def isFull: Boolean
override def toString: String // = array.mkString("Array(", ", ", ")")
```

a) Napisz klasę generyczną `QueueMut`, rozszerzającą powyższą klasę abstrakcyjną,
w której kolejka jest implementowana przez **tablicę cykliczną** (wszystkie operacje na
indeksach tablicy cyklicznej są wykonywane modulo rozmiar tablicy). Implementacja
ma być zgodna z poniższym rysunkiem, czyli rozmiar tablicy musi być o 1 większy od
pojemności kolejki (dzięki temu indeksy f oraz r wystarczą do sprawdzenia, czy
kolejka jest pusta czy pełna). Metoda `dequeue` dla pustej kolejki ma pozostawiać
pustą kolejkę.

b) Zdefiniuj obiekt towarzyszący z metodami:
```scala worksheet
def apply[E: ClassTag](xs: E*): QueueMut[E] = ???
def empty[E: ClassTag](capacity: Int = 1000) : QueueMut[E] = ???
```
Przeprowadź testy na **małej** kolejce, którą **całkowicie** zapełnisz. Przetestuj przejście przez
„sklejenie” tablicy.Wszystkie definicje oraz proste testy w obiekcie singletonowym `Lista8`
z metodą main umieść w pliku `Lista8.scala`.

**Uwaga.** Tablice w języku Java nie mogą być generyczne (wykład 7, str. 36). W Scali jest to
jednak możliwe, ale w czasie tworzenia tablicy generycznej potrzebna jest informacja
o _wytartym_ typie elementów tej tablicy. Można to zrobić, wykorzystując znacznik typu (ang.
class tag), który sam jest typu _scala.reflect.ClassTag_ (wykład 7, str. 38), co jest zilustrowane
poniżej:
```scala worksheet
import reflect.ClassTag
class QueueMut[E: ClassTag] private(val capacity: Int = 1000)
extends MyQueue[E]
```

## [Lista 9](/src/main/scala/lista9.scala)
### [Zad 1](/src/main/scala/lista9.scala#L3)
Przeanalizuj poniższy program. Dwa wątki zwiększają 200 000 razy wspólny licznik o 1.
Po ich zakończeniu wartość licznika powinna oczywiście wynosić 400 000. Uruchom ten
program kilka razy.
```scala worksheet
object Zad1:
  var counter = 0 // counter variable
  
  def readWriteCounter(): Unit =
    counter += 1

  def main(args: Array[String]): Unit =
    val p = new Thread(() => for _ <- 0 until 200000 do readWriteCounter)
    val q = new Thread(() => for _ <- 0 until 200000 do readWriteCounter)
    val startTime = System.nanoTime
    p.start; q.start
    p.join; q.join
    val estimatedTime = (System.nanoTime - startTime) / 1000000
    println(s"Value of the counter = $counter")
    println(s"Estimated time = ${estimatedTime}ms, Available processors = ${Runtime.getRuntime.availableProcessors}")
```

a) Jak wyjaśnisz różne wartości licznika? W wyjaśnieniu załóż, że mamy tylko jeden procesor,
więc nie ma akcji jednoczesnych.Wskaż fragment kodu, który jest źródłem problemów.
Odpowiedź należy umieścić w postaci komentarza na początku pliku `Lista9.scala`.

b) Popraw powyższy program, wykorzystując mechanizm kodu synchronizowanego (blokada
wewnętrzna, monitory).

c) Popraw powyższy program, wykorzystując mechanizm semaforów (klasa
_java.util.concurrent.Semaphore_).

### [Zad 2](/src/main/scala/lista9.scala#L68)
Zaimplementuj metodę parallel, która jako argumenty bierze dwa bloki kodu, wykonuje je
jednocześnie w osobnych wątkach i zwraca wyniki ich obliczeń w postaci pary.
```scala worksheet
def parallel[A, B](block1: =>A, block2: =>B): (A, B) = ???
```

Przykładowe testy:
```s'
assert(parallel("a"+1, "b"+2) == ("a1", "b2"))
println(parallel(Thread.currentThread.getName, Thread.currentThread.getName))
```

### [Zad 3](/src/main/scala/lista9.scala#L88)
Zaimplementuj metodę `periodically`, która jako argumenty bierze interwał czasowy duration
(w milisekundach), maksymalną liczbę powtórzeń times oraz blok kodu block. Metoda
tworzy wątek demona, który wykonuje podany blok kodu z pauzami trwającymi `duration`
milisekund, maksymalnie `times` razy.
```scala worksheet
def periodically(duration: Long, times: Int)(block: => Unit): Unit = ???
```
Test (na końcu metody main) ma wyglądać tak:
```scala worksheet
periodically(1000, 5){print("y ")}
periodically(1000, 25){print("x ")}
Thread.sleep(10000)
println("Done sleeping")
```
Uruchom ten program kilka razy. Wynik powinien być taki (z dokładnością do przeplotu): \
`y x y x x y x y x y x x x x x Done sleeping`

Dlaczego `x` nie zostało wyświetlone 25 razy?

## [Lista 10](/src/main/scala/lista10.scala)
### [Zad 1](/src/main/scala/lista10.scala#L9)
Na wykładzie 9 (str. 44-45) był przedstawiony program producent/konsument
z ograniczonym buforem cyklicznym.

a) Przepisz ten program, wykorzystując zamiast klasy `BoundedBuffer` klasę biblioteczną
_java.util.concurrent.ArrayBlockingQueue_.

b) W programie z podpunktu a) utwórz kilka producentów i konsumentów. Nadaj im unikatowe nazwy, np. Producer1, Consumer1 itd. Do wyświetlania informacji użyj
metodę `log` (patrz programy z wykładu 10).W jednym z testów utwórz dwa producenty
i trzy konsumenty. Dlaczego program się nie kończy? Odpowiedź umieść w
komentarzu.

c) Z programu w podpunkcie b) usuń definicje klas `Producer` i `Consumer`. Wykorzystaj
`ExecutionContext` do wykonywania odpowiadających im zadań. W jednym z testów
utwórz dwa producenty i trzy konsumenty. Dlaczego program się kończy? Odpowiedź
umieść w komentarzu.

### [Zad 2](/src/main/scala/lista10.scala#L82)
Napisz program, rozwiązujący problem ucztujących filozofów (wykład 9, str. 40) dla N
filozofów za pomocą semaforów (_java.util.concurrent.Semaphore_). Rozwiązanie powinno
spełniać następujące warunki:
1. Każdy filozof ma stałe miejsce przy stole. Filozof je tylko wtedy, gdy ma dwie pałeczki.
2. Dwóch filozofów nie może jednocześnie trzymać tej samej pałeczki.
3. Nie występuje blokada (sytuacja patowa). Może ona wystąpić np. wtedy, gdy wszyscy filozofowie
   podniosą lewe pałeczki i będą czekać na zwolnienie prawych.
4. Nikt nie może być zagłodzony. Oczywista z pozoru strategia, polegająca na poczekaniu, aż obie
   pałeczki będą wolne, może spowodować zagłodzenie dwóch filozofów (dlaczego?).
5. Żaden z filozofów nie zajmuje się tylko jedzeniem. Po zakończeniu posiłku każdy odkłada
   pałeczki i wraca do sali medytacji.
6. Filozofowie podnoszą i odkładają pałeczki po jednej naraz.
7. Nie można wyróżniać żadnego z filozofów (algorytmy ich działania powinny być takie same).
   
Jedno z rozwiązań zakłada, że na początku wszyscy filozofowie medytują w przeznaczonej do
tego sali, natomiast posiłki spożywają w jadalni. Należy zaangażować odźwiernego,
pilnującego drzwi jadalni i pozwalającego przebywać w niej jednocześnie co najwyżej N-1
filozofom. Dzięki temu co najmniej dwom filozofom, siedzącym przy stole, brakuje co
najmniej jednego sąsiada, a zatem co najmniej jeden filozof może jeść.
Każdy filozof ma wyświetlać odpowiednie komunikaty, informujące o: czasie medytacji,
wejściu do jadalni, czasie jedzenia, wyjściu z jadalni.

_Wskazówka._ Przedstaw filozofów jako wątki (każdy filozof w pętli naprzemiennie medytuje i posila
się), sekcją krytyczną jest jedzenie, a zasobami dzielonymi są pałeczki do ryżu. Wątki są
ponumerowane od 0 do N-1 (co odpowiada stałym miejscom filozofów przy stole( i wykonują się
współbieżnie. Użycie każdej pałeczki jest kontrolowane przez semafor binarny, a odźwierny jest
reprezentowany przez semafor ogólny z wartością początkową N-1.

## [Lista 11](/src/main/scala/lista11.scala)
### [Zad 1](/src/main/scala/lista11.scala#L9)
Zdefiniuj dwie funkcje (`pairFut` i `pairFutZip`) z taką samą funkcjonalnością:
```scala worksheet
def pairFut[A, B] (fut1: Future[A], fut2: Future[B]): Future[(A, B)] = ???
```
a) Wykorzystaj metodę `zip` (wykład 11, str. 18) – w metodzie `def pairFutZip`

b) Wykorzystaj `for` (wykład 11, str. 19) ) – w metodzie `def pairFut`

### [Zad 2](/src/main/scala/lista11.scala#L36)
Do typu `Future[T]` dodaj dwie metody (`exists` i `existsProm`) z taką samą funkcjonalnością::
```scala worksheet
def exists(p: T => Boolean): Future[Boolean] = ???
```
Wynikowy obiekt `Future` ma zawierać wartość `true` wtedy i tylko wtedy, kiedy obliczenia obiektu
oryginalnego kończą się pomyślnie i predykat p dla obliczonej wartości zwraca wartość `true`,
w przeciwnym razie wynikowy obiekt `Future` ma zawierać wartość `false`. Wykorzystaj mechanizm
metod rozszerzających (wykład 11, str. 28).

a) Wykorzystaj promesę – w metodzie `existsProm`

b) Nie korzystaj z promesy (użyj `map`) – w metodzie `exists`

Przeprowadź trzy testy: kiedy predykat jest spełniony, kiedy predykat nie jest spełniony,
kiedy rzucany jest wyjątek.

### [Zad 3](/src/main/scala/lista11.scala#L61)
Należy policzyć liczbę słów w każdym pliku tekstowym zadanego folderu i wydrukować
wynik w postaci par (nazwa pliku, liczba słów), posortowanych niemalejąco względem liczby
słów. Możemy założyć dla uproszczenia, że słowa są oddzielone spacjami (wykorzystaj
metodę `split`). Obliczenia należy przeprowadzać asynchronicznie. Pliki testowe są w folderze
_Zad3pliki_. Program ma być napisany funkcyjnie. Poniżej jest jego schemat (plik _Zad3szablon.scala_).
```scala
object Zad3:
    import scala.concurrent.*
    import ExecutionContext.Implicits.global
    import scala.util.{Success, Failure}
    import scala.io.Source
    def main(args: Array[String]): Unit =
        //ścieżka do folderu, pobierana z wiersza poleceń, np. "C:/lista11/pliki/” lub "C:\\lista11\\pliki\\”
        val path = args(0)
        val promiseOfFinalResult = Promise[Seq[(String, Int)]]() // Promesa jest wyłącznie z powodów dydaktycznych
        // Tu oblicz promiseOfFinalResult
        promiseOfFinalResult.future onComplete {
            case Success(result) => result foreach println
            case Failure(t)
            => t.printStackTrace
        }
        Thread.sleep(500)
    end main
    // Oblicza liczbę słów w każdym pliku z sekwencji wejściowej
    private def processFiles(fileNames: Seq[String]): Future[Seq[(String, Int)]] = ???
    // Wskazówka. Wykorzystaj Future.sequence(futures)
    // Oblicza liczbę słów w podanym pliku i zwraca parę: (nazwa pliku, liczba słów)
    private def processFile(fileName: String): Future[(String, Int)] = ???
    // Zwraca sekwencję nazw plików z folderu docRoot
    private def scanFiles(docRoot: String): Future[Seq[String]] =
      Future { new java.io.File(docRoot).list.toIndexedSeq.map(docRoot + _) }
end Zad3
```

## [Lista 12](/src/main/scala/lista12.scala)
Napisz kompletną aplikację (w stylu funkcyjnym, nie wolno używać `var`), w której klienty
zgadują liczbę, wylosowaną przez serwer. Wzoruj się na przykładach „klient/serwer”
i „wykorzystanie sygnałów” z wykładu.

### [Zad 1](/src/main/scala/lista12.scala#L6)
a) Napisz aktora dla strażnika systemu aktorów. Strażnik użytkownika po
otrzymaniu komunikatu `StartGuessing`, który zawiera liczbę klientów (co najmniej
dwa) i górną granicę przedziału zgadywania, ma utworzyć instancję serwera
i instancje klientów, zgadujących liczbę wylosowaną przez serwer.
```scala worksheet
def apply(): Behavior[StartGuessing] = ???
```
b) Dodaj obsługę zakończenia. Po zakończeniu działania przez wszystkie klienty
(sygnały `Terminated`) strażnik ma wyprowadzić odpowiednie komunikaty
i zakończyć swoje działanie, a tym samym działanie systemu aktorów.

### [Zad 2](/src/main/scala/lista12.scala#L42)
Napisz aktora dla serwera (`server: ActorRef[Server.ClientGuess]`), który generuje
losowo liczbę całkowitą z przedziału [0 .. N] (N > 1 ma być parametrem metody
`apply` serwera), zapisuje w dzienniku odpowiednią informację i czeka na
komunikaty od klientów, które próbują zgadnąć tę liczbę. Po otrzymaniu
komunikatu serwer wysyła odpowiedź z informacją, czy liczba klienta była
mniejsza, większa czy równa liczbie wylosowanej.
```scala worksheet
def apply(upper: Int): Behavior[ClientGuess] = ???
```

### [Zad 3](/src/main/scala/lista12.scala#L65)
Napisz aktora dla klienta zgadującego liczbę, wylosowaną przez serwer. Próby
klienta powinny być optymalne (wyszukiwanie binarne) z wyjątkiem pierwszej
próby, która ma być losowa. Klient ma zapisywać w dzienniku informacje,
umożliwiające śledzenia działania aplikacji (patrz przykład poniżej). Po
odgadnięciu liczby klient ma zapisać odpowiedni komunikat i zakończyć swoje
działanie.
```scala worksheet
def apply(upper: Int, server: ActorRef[Server.ClientGuess]): Behavior[ServerInfo] = ?
```


W poniższym przykładowym przebiegu zostały pominięte informacje, dodawane przez
dziennik (ang. logger). Komunikaty serwera i klienta mają być takie, jak poniżej.
```text
Client1. Started
Server. Guess my number from the interval [0..100]      <- od serwera
Client2. Started
Client1. First random try = 30
Client2. First random try = 99
Client1. Response: too big. I'm trying: 14
Client2. Response: too big. I'm trying: 49
Client1. Response: too small. I'm trying: 22
Client2. Response: too big. I'm trying: 24
Client1. Response: too big. I'm trying: 18
Client2. Response: too big. I'm trying: 11
Client1. Response: too small. I'm trying: 20
Client2. Response: too small. I'm trying: 17
Client1. Response: too small. I'm trying: 21
Client2. Response: too small. I'm trying: 20
Client1. I guessed it! 21
Client2. Response: too small. I'm trying: 22
Client2. Response: too big. I'm trying: 21
Client2. I guessed it! 21
```

## [Lista 13](lista13/src/main/scala/lista13.scala)
### [Zad 1](lista13/src/main/scala/lista13.scala#L6)
Zdefiniuj obiekt Observable, emitujący zdarzenie co 5 sekund i co 12 sekund, ale nie
wtedy, kiedy czas jest wielokrotnością 30 sekund. Emitowane zdarzenia to: `5, 10, 12, 15,
20, 24, 25, 35, 36, 40 itd.` Zakończ działanie obserwabli po wyemitowaniu 15 zdarzeń.

a) Zdefiniuj dwie obserwable. Jedna emituje zdarzenie co 5 sekund, a druga co 12 sekund
i utwórz z nich jedną za pomocą metody `merge`. \
Subskrybent ma wyświetlać zdarzenia w poniższym formacie (nazwy wątków należy
dodać przy wyświetlaniu):
```txt
RxComputationScheduler-1: 5
RxComputationScheduler-1: 10
RxComputationScheduler-2: 12
RxComputationScheduler-1: 15
RxComputationScheduler-1: 20
RxComputationScheduler-2: 24
RxComputationScheduler-1: 25
RxComputationScheduler-1: 35
RxComputationScheduler-2: 36
RxComputationScheduler-1: 40
RxComputationScheduler-1: 45
RxComputationScheduler-2: 48
RxComputationScheduler-1: 50
RxComputationScheduler-1: 55
RxComputationScheduler-1: 65
RxComputationScheduler-1: Observable completed
```
b) Zrób to samo, definiując tylko jedną obserwablę (wykorzystaj metodę `filter`). \
Oto początek przykładowego wydruku. Zauważ, że teraz wykorzystywany jest tylko
jeden wątek.
```txt
RxComputationScheduler-1: 5
RxComputationScheduler-1: 10
RxComputationScheduler-1: 12
RxComputationScheduler-1: 15
RxComputationScheduler-1: 20
RxComputationScheduler-1: 24
RxComputationScheduler-1: 25
RxComputationScheduler-1: 35
RxComputationScheduler-1: 36
RxComputationScheduler-1: 40
RxComputationScheduler-1: 45
RxComputationScheduler-1: 48
RxComputationScheduler-1: 50
RxComputationScheduler-1: 55
RxComputationScheduler-1: 65
RxComputationScheduler-1: Observable completed
```
