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

## [Lista 9](/src/main/scala/lista9.scala)
### [Zad 1](/src/main/scala/lista9.scala#L3)
### [Zad 2](/src/main/scala/lista9.scala#L68)
### [Zad 3](/src/main/scala/lista9.scala#L88)

## [Lista 10](/src/main/scala/lista10.scala)
### [Zad 1](/src/main/scala/lista10.scala#L9)
### [Zad 2](/src/main/scala/lista10.scala#L82)

## [Lista 11](/src/main/scala/lista11.scala)
### [Zad 1](/src/main/scala/lista11.scala#L9)
### [Zad 2](/src/main/scala/lista11.scala#L36)
### [Zad 3](/src/main/scala/lista11.scala#L61)

## [Lista 12](/src/main/scala/lista12.scala)
### [Zad 1](/src/main/scala/lista12.scala#L6)
### [Zad 2](/src/main/scala/lista12.scala#L42)
### [Zad 3](/src/main/scala/lista12.scala#L65)
