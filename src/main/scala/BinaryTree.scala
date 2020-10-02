import scala.util.Random

enum BinaryTree[+T]:
  case Node(left: BinaryTree[T], value: T, right: BinaryTree[T])
  case Leaf


object BinaryTree:
  def empty[T]: BinaryTree[T] = BinaryTree.Leaf

trait Comparable[T]:
  extension(x: T) def isBigger(y: T): Boolean

given Comparable[Int] :
  extension(x: Int) def isBigger(y: Int): Boolean = x > y

given showBinaryTree[T: Show] as Show[BinaryTree[T]]:
  def show(t: BinaryTree[T]): String = t match {
    case BinaryTree.Leaf => "_"
    case BinaryTree.Node(BinaryTree.Leaf,v,BinaryTree.Leaf) => s"[$v]"
    case BinaryTree.Node(BinaryTree.Leaf,v,r) => s"[${v},${show(r)}]"
    case BinaryTree.Node(l,v,BinaryTree.Leaf) => s"[${show(l)},${v}]"
    case BinaryTree.Node(l,v,r) => s"[${show(l)},$v,${show(r)}]"  
  }

given Show[Int]:
  def show(t: Int): String = t.toString 

extension[T: Comparable] (tree: BinaryTree[T]) {
  def add(t: T): BinaryTree[T] =
    tree match {
      case BinaryTree.Leaf => BinaryTree.Node(BinaryTree.Leaf, t, BinaryTree.Leaf)
      case n@BinaryTree.Node(l, v, r) if v.isBigger(t) =>
        n.copy(left = l.add(t)(summon[Comparable[T]]))
      case n@BinaryTree.Node(l, v, r) =>
        n.copy(right = r.add(t)(summon[Comparable[T]]))
    }
 
}


@main def treemain(s: String*) = {
  val r = new Random()
  val tree = 
    LazyList
      .continually(r.nextInt(20))
      .take(10)
      .foldLeft(BinaryTree.empty[Int])(_.add(_))
      
  println(showBinaryTree.show(tree))  
  println(showBinaryTree.show(BinaryTree.empty[Int].add(5).add(2)))  
}