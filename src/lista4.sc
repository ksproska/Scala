// Kamila Sproska

// Zadanie 1
class MyPair[A, B](var fst: A, var snd: B):
  override def toString = s"($fst, $snd)"

val pair = MyPair("a", 1)
pair.toString == "(a, 1)"
pair.fst = "b"
pair.snd = 2
pair.toString == "(b, 2)"
pair.toString == s"(${pair.fst}, ${pair.snd})"

// Zadanie 2
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

// a)
class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance):
  override def deposit(amount: Double): Double = super.deposit(amount - 1)

  override def withdraw(amount: Double): Double = super.withdraw(amount + 1)

var checkingAccount = CheckingAccount(100)
checkingAccount.deposit(100)
checkingAccount.withdraw(100)
checkingAccount.checkBalance == 100 - 2

// b)
class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance):
  private var transactions = 0

  def earnMonthlyInterest(): Unit =
    transactions = 0
    if checkBalance > 0 then deposit(checkBalance * 0.01) else withdraw(-checkBalance * 0.01)

  override def deposit(amount: Double): Double =
    transactions += 1
    super.deposit(if transactions <= 3 then amount else amount - 1)

  override def withdraw(amount: Double): Double =
    transactions += 1
    super.withdraw(if transactions <= 3 then amount else amount + 1)


var savingsAccount = SavingsAccount(100)
savingsAccount.withdraw(10) == 90
savingsAccount.withdraw(10) == 80
savingsAccount.deposit(20) == 100
savingsAccount.deposit(100) == 199
savingsAccount.withdraw(98) == 100
savingsAccount.earnMonthlyInterest()
savingsAccount.checkBalance == 101
savingsAccount.withdraw(201) == -100
savingsAccount.earnMonthlyInterest()
savingsAccount.checkBalance == -101

// Zadanie 3
abstract class Zwierze(val imie: String):
  def rodzaj = getClass.getSimpleName
  def dajGlos: String
  override def toString = s"$rodzaj $imie daje głos $dajGlos"

class Pies(imie: String = "bez imienia") extends Zwierze(imie):
  def dajGlos = "Hau hau"

class Kot(imie: String = "bez imienia") extends Zwierze(imie):
  def dajGlos = "Miał miał"

object TestZwierza:
  def main(args: Array[String]): Unit =
    val animals = Vector(Pies(), Pies("Burek"), Kot("Garfild"))
    for animal <- animals do println(animal)
    for animal <- animals do println(animal.imie)
    for animal <- animals do println(animal.rodzaj)
    for animal <- animals do println(animal.dajGlos)

TestZwierza.main(Array())
