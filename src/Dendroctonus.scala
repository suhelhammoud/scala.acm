/**
  * @author Suhel Hammoud
  *         Reference: http://codeforces.com/gym/101177/attachments
  */


//failed on later unknown tests

import scala.io.StdIn

object Dendroctonus extends App {

  trait Point {
    val x: Double
    val y: Double
  }

  case class Tree(x: Double, y: Double, isInfected: Boolean) extends Point

  case class Circle(x: Double, y: Double, r2: Double) extends Point {
    def inCircle(p: Point) = distance2(x, y, p.x, p.y) < r2

    def checkInCircleIsInfected(trees: Seq[Tree]) =
      trees.filter(inCircle).forall(_.isInfected)

  }

  def distance2(x1: Double, y1: Double, x2: Double, y2: Double) = {
    val diffX = x1 - x2
    val diffY = y1 - y2
    diffX * diffX + diffY * diffY
  }

  def getCircle(p1: Point, p2: Point): Circle = {
    val x0 = (p1.x + p2.x) / 2.0
    val y0 = (p1.y + p2.y) / 2.0
    val r2 = distance2(p1.x, p1.y, p2.x, p2.y) / 4
    Circle(x0, y0, r2)
  }

  def getCircle(p1: Point, p2: Point, p3: Point): Circle = {
    case class Param(a: Double, b: Double, c: Double)

    def getParam(p1: Point, p2: Point) = {
      def dst(p: Point) = p.x * p.x + p.y * p.y

      val a = 2 * (p2.x - p1.x)
      val b = 2 * (p2.y - p1.y)
      val c = dst(p2) - dst(p1)
      Param(a, b, c)
    }

    def isMiddle(p1: Point, p2: Point, p3: Point) = {
      ((p1.x >= p2.x && p1.x <= p3.x) || (p1.x <= p2.x && p1.x >= p3.x)) &&
        ((p1.y >= p2.y && p1.y <= p3.y) || (p1.y <= p2.y && p1.y >= p3.y))
    }

    val Param(a1, b1, c1) = getParam(p1, p2)
    val Param(a2, b2, c2) = getParam(p2, p3)
    val d = a1 * b2 - a2 * b1
    //check if all points lay on same line
    if (d == 0) {
      val (t1, t2) = if (isMiddle(p1, p2, p3)) (p2, p3)
      else if (isMiddle(p2, p1, p3)) (p1, p3)
      else (p1, p2)

      getCircle(t1, t2)
    } else {
      val x0 = (b2 * c1 - b1 * c2) / d
      val y0 = (a1 * c2 - a2 * c1) / d
      Circle(x0, y0, distance2(x0, y0, p1.x, p1.y))
    }
  }

  def ofTree(s: String): Tree = {
    val Array(xx, yy, ii) = s.split(" ")
    Tree(xx.toDouble, yy.toDouble, ii.toUpperCase == "I")
  }

  val numTrees = StdIn.readLine().toInt
  val trees = (0 until numTrees).map(e => ofTree(StdIn.readLine.trim.toString))

  //  println(numTrees)
  //  trees.foreach(println)

  val itrees = trees.filter(_.isInfected) //infected trees

  val isOneOutBreak = if (itrees.size <= 1 || itrees.size == trees.size)
    true
  else if (itrees.size == 2) {
    val c = getCircle(itrees(0), itrees(1))
    c.checkInCircleIsInfected(trees)
  } else {
    itrees.combinations(3).map(e => getCircle(e(0), e(1), e(2)))
      .forall(_.checkInCircleIsInfected(trees))
  }

  if (isOneOutBreak) {
    println("No")
  } else {
    println("Yes")
  }

}
