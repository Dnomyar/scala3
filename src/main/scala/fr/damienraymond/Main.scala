//
//object Main {
//
//  def main(args: Array[String]): Unit = {
//    println("Hello world!")
//    println(msg)
//    println(test("qwdqwd"))
//  }
//
//  def msg = "I was compiled by dotty :)"
//
//}
//
//
//case class UserName(name: String)
//case class Password(hash: String)
//
//def help(id: UserName | Password) = 
//  id match 
//    case UserName(name) => "username"
//    case Password(hash) => "password"
//
//
//type Test = [X, Y] =>> Map[Y, X]
//
//def test2(a: Test[String, Int]) = ???
//
//def test(edad: String): String = 
//  if (true) "qwd"
//  else "qwdqwd"