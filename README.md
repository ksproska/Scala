# Wszystkie listy:

## [Lista 0](/src/lista0.sc)
### [Zad 1](/src/lista0.sc#L3)
Napisz funkcję rekurencyjną `last[A](xs: List[A]):` A zwracającą ostatni element zadanej listy,

np.
```scala worksheet
last (List(1, 9, 5, 6, 3)) == 3
last(List("Ala", "ma", "kota")) == "kota"
last (List(5)) == 5
last(Nil) =>> java.util.NoSuchElementException: last of empty list
```
Można używać tylko funkcji head i tail!

## [Lista 1](/src/lista1.sc)
### [Zad 1](/src/lista1.sc#L3)
Napisz funkcję suma: `List[Double] => Double`, zwracającą sumę liczb z podanej listy,

np. 
```scala worksheet
suma(Nil) == 0.0
suma(List(-1, 2, 3)) == 4.0
suma(List(5.6)) == 5.6
```

### [Zad 2](/src/lista1.sc#L15)
Napisz funkcję `ends[A](xs: List[A]): (A, A)`, zwracającą parę, zawierającą pierwszy
i ostatni element zadanej listy,

np.
```scala worksheet
ends(List(1, 3, 5, 6, 9)) == (1,9)
ends(List("Ala", "ma", "kota")) == ("Ala", "kota")
ends(List(1)) == (1,1)
ends(Nil) =>> wyjątek NoSuchElementException: empty list
```

### [Zad 3](/src/lista1.sc#L30)
Napisz funkcję posortowana: List[Int] => Boolean sprawdzającą, czy dana lista jest
posortowana niemalejąco,

np.
```scala worksheet
posortowana(List(1,3,3,5,6,7)) == true
```

### [Zad 4](/src/lista1.sc#L43)
Napisz funkcję `glue: (List[String], String) => String`, która na wejściu przyjmuje listę napisów
oraz napis będący separatorem i zwraca napis będący połączeniem napisów wejściowych
oddzielonych od siebie zadanym separatorem,

np.
```scala worksheet
glue(List("To", "jest", "napis"), "-") == "To-jest-napis"
glue(Nil, "-") == ""
```

## [Lista 2](/src/lista2.sc)
### [Zad 1](/src/lista2.sc#L4)
Napisz funkcję `take[A](n: Int, xs: List[A]): List[A]`,
gdzie `take(k, List(x1, ..., xn)) == List(x1, ..., xk)`,

np.
```scala worksheet
take(2, List(1,2,3,5,6)) == List(1,2)
take(-2, List(1,2,3,5,6)) == Nil
take(8, List(1,2,3,5,6)) == List(1,2,3,5,6)
```

### [Zad 2](/src/lista2.sc#L19)
Napisz funkcję `drop[A](n: Int, xs: List[A]): List[A]`,
gdzie `drop(k, List(x1 , ..., xn)) == List(xk+1, ..., xn)`,

np.
```scala worksheet
drop(2, List(1,2,3,5,6)) == List(3,5,6)
drop(-2, List(1,2,3,5,6)) == List(1,2,3,5,6)
drop(8, List(1,2,3,5,6)) == Nil
```

### [Zad 3](/src/lista2.sc#L34)
Napisz funkcję `reverse[A](xs: List[A]): List[A]`, odwracającą zadaną listę w czasie liniowym
(bez użycia metody bibliotecznej reverse!),
np.
```scala worksheet
reverse(List("Ala", "ma", "kota")) == List("kota", "ma", "Ala")
```

### [Zad 4](/src/lista2.sc#L50)
Napisz funkcję `replicate: List[Int] => List[Int]`, która z danej listy liczb naturalnych tworzy
listę, w której każdy element wejściowej listy jest tyle razy powtórzony, jaką ma wartość,

np.
```scala worksheet
replicate (List(1,0,4,-2,3)) == List(1, 4, 4, 4, 4, 3, 3, 3)
```

### [Zad 5](/src/lista2.sc#L74)
Dla zadanej liczby rzeczywistej `a` oraz dokładności `ε` można znaleźć pierwiastek
trzeciego stopnia z `a` wyliczając kolejne przybliżenia `x_i` tego pierwiastka (metoda
Newtona-Raphsona):
```math
x_0 = a/3 dla a > 1

x_0 = a dla a ≤ 1

x_i+1 = x_i + (a/x_i^2 – x_i)/3
```
Dokładność (względna) jest osiągnięta, jeśli 
```math
|x_i^3 – a| <= ε * |a|
```
Napisz efektywną (**wykorzystującą rekursję ogonową**) funkcję `root3: Double => Double`,
która dla zadanej liczby `a` znajduje pierwiastek trzeciego stopnia z dokładnością względną `ε=10^-15`.

## [Lista 3](/src/lista3.sc)
### [Zad 1](/src/lista3.sc#L3)
### [Zad 2](/src/lista3.sc#L42)
### [Zad 3](/src/lista3.sc#L55)
### [Zad 4](/src/lista3.sc#L87)

## [Lista 4](/src/lista4.sc)

## [Lista 5](/src/lista5.sc)

## [Lista 6](/src/lista6.sc)

## [Lista 7](/src/lista7.scala)
