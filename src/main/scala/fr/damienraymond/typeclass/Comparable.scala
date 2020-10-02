package typeclass

trait Comparable[T]:
  extension(x: T) def isBigger(y: T): Boolean


given Comparable[Int] :
  extension(x: Int) def isBigger(y: Int): Boolean = x > y