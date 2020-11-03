package FunctionalProgramming

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

trait ImplementMethods{
  // implement method fold which will implement all these
  // functionalities into a method fold.
}

trait DoneImplementation{
  def size[A](tree: Tree[A]): Int // will count number of nodes(leaves & Branches) in a tree
  def max[A](tree: Tree[A]): A // will return the max element in a tree
  def depth[A](tree: Tree[A]): Int // will return the maximum path length from root to any Leaf
  def map[A,B](tree: Tree[A])(f: A => B): Tree[B] // same as list in map
  def leafCount[A](tree: Tree[A]): Int // will return the count of the leaf's
}

object FunctionalProgrammingCaseMatching {

  def size[A](tree: Tree[A]): Int ={
    def go(localTree: Tree[A], acc: Int): Int={
      localTree match {
        case branch: Branch[A] => go(branch.left, acc + 0) + go(branch.right, acc + 0) + 1
        case _: Leaf[A] => acc + 1
        }
    }
    go(tree, 0)
  }

  def depth[A](tree: Tree[A]): Int={
    def go(localTree: Tree[A], depth: Int): Int={
      localTree match {
        case branch: Branch[A] =>
          val leftDepth = go(branch.left, 0) + 1
          val rightDepth = go(branch.right, 0) + 1
          if (leftDepth > rightDepth) leftDepth else rightDepth
        case _: Leaf[A] => depth
      }
    }
    go(tree, 0)
  }

  def max[A](tree: Tree[A]): A ={
    //make this max method generic to compare generic elements
    def go(localTree: Tree[A], maxEle: A): A ={
      localTree match {
        case branch: Branch[A] =>
          val leftMax = go(branch.left,maxEle).asInstanceOf[Int]
          val rightMax = go(branch.right, maxEle).asInstanceOf[Int]
          if (leftMax > rightMax) leftMax.asInstanceOf[A] else rightMax.asInstanceOf[A]
        case leaf: Leaf[A] =>
          if (leaf.value.asInstanceOf[Int] > maxEle.asInstanceOf[Int]) leaf.value else maxEle
      }
    }
    go(tree, (-1).asInstanceOf[A])
  }

  def map[A,B](tree: Tree[A])(f: A => B): Tree[B] ={
    def go(localTree: Tree[A], accTree: Tree[B]): Tree[B] ={
      localTree match {
        case branch: Branch[A] => Branch(go(branch.left, accTree),go(branch.right, accTree))
        case leaf: Leaf[A] => Leaf(f(leaf.value))
      }
    }
    go(tree, Branch[B](null, null))
  }

  def leafCount[A](tree: Tree[A]): Int={
    def go(localTree: Tree[A], acc: Int):Int ={
      localTree match {
        case branch: Branch[A] => go(branch.left, acc + 0) + go(branch.right, acc + 0)
        case _: Leaf[A] => acc + 1
      }
    }
    go(tree, 0)
  }
}

object DriverCode extends App{
  import FunctionalProgrammingCaseMatching._

  val tree = Branch(Branch(Branch(Leaf(1),Leaf(4)),Leaf(5))
                   ,Branch(Leaf(6),Leaf(7)))

  println(tree.left)

  // printing the count of branches and Leaf's
  println("size - " + size(tree))

  // printing the leaf counts in the tree
  println("leafCount - " + leafCount(tree))

  // getting the max amount from the tree
  println("max element in tree - "+ max(tree))

  // making a function to update element with 10
  val update = (x: Int) => x + 10
  println("printing the updated tree")
  println(map(tree)(update))

  // getting the depth of the tree
  println(depth(tree))

}
