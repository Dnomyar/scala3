package typeclass

trait Show[T]:
  def show(t: T): String


given Show[Int]:
  def show(t: Int): String = t.toString 
