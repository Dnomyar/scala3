import typeclass.Show

import scala.util.Random
import typeclass._
import typeclass.{given _}

enum BinaryTree[+T]:
  case Node(left: BinaryTree[T], value: T, right: BinaryTree[T])
  case Leaf

object BinaryTree{

  def empty[T]: BinaryTree[T] = BinaryTree.Leaf

  given showBinaryTree[T: Show] as Show[BinaryTree[T]]:
    def show(t: BinaryTree[T]): String = t match {
      case BinaryTree.Leaf => "_"
      case BinaryTree.Node(BinaryTree.Leaf,v,BinaryTree.Leaf) => s"[$v]"
      case BinaryTree.Node(BinaryTree.Leaf,v,r) => s"[${v},${show(r)}]"
      case BinaryTree.Node(l,v,BinaryTree.Leaf) => s"[${show(l)},${v}]"
      case BinaryTree.Node(l,v,r) => s"[${show(l)},$v,${show(r)}]"
    }

  extension[T] (tree: BinaryTree[T]) {
    def add(t: T)(using comparable: Comparable[T], show: Show[T]): BinaryTree[T] =
      tree match {
        case BinaryTree.Leaf => BinaryTree.Node(BinaryTree.Leaf, t, BinaryTree.Leaf)
        case n@BinaryTree.Node(l, v, r) if v.isBigger(t) =>
          n.copy(left = l.add(t))
        case n@BinaryTree.Node(l, v, r) =>
          n.copy(right = r.add(t))
      }

    def show(using show: Show[T]): String = BinaryTree.showBinaryTree.show(tree)

  }
}



@main def treemain(s: String*) = {
  val r = new Random()
  val tree =
    LazyList
      .continually(r.nextInt(20))
      .take(10)
      .foldLeft(BinaryTree.empty[Int])(_.add(_))

  println(tree.show)
  println(BinaryTree.empty[Int].add(5).add(2).show)
}