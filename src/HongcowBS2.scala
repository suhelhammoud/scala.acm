/**
  * @author Suhel Hammoud
  *         Reference: http://codeforces.com/problemset/problem/744/A
  */

import scala.collection._
import scala.collection.mutable.ListBuffer

object HongcowBS2 extends App {

  val tokens = scala.io.Source.stdin.getLines
    .flatMap(_ split ' ' filter (_.nonEmpty))

  def nextInt() = tokens.next().toInt

  def getLinksNum(n: Int) = if (n == 1) 0 else n * (n - 1) / 2

  val n, m, k = nextInt()

  if (k == 1) {
    println(getLinksNum(n) - m)
  } else if (m == 0) {
    println(getLinksNum(n - k + 1))
  } else {

    val governments = (0 until k).map { _ =>
      val k = nextInt()
      k -> mutable.BitSet(k)
    }.toMap

    var freeLinks = ListBuffer[Array[Int]]()
    for (_ <- 0 until m) {
      val link = Array(nextInt(), nextInt())
      if (governments.contains(link(0))) {
        governments.get(link(0)).get += link(1)
      } else if (governments.contains(link(1))) {
        governments.get(link(1)).get += link(0)
      } else {
        freeLinks += link
      }
    }

    var continue = true
    while (continue) {
      val remainingLinks = ListBuffer[Array[Int]]()
      for (link <- freeLinks) {
        val entry = governments.find { p =>
          (p._2.contains(link(0))
            || p._2.contains(link(1)))
        }
        if (entry.isEmpty) remainingLinks += link
        else entry.get._2 += (link(0), link(1))
      }
      if (remainingLinks.size == freeLinks.size) continue = false
      else freeLinks = remainingLinks
    }

    val govCounts = governments.values.map(_.size).toArray
    val maxIndex = govCounts.indexOf(govCounts.max)
    val freeCount = n - govCounts.sum
    govCounts(maxIndex) = govCounts(maxIndex) + freeCount
    val result = govCounts.map(getLinksNum).sum - m
    println(result)
  }
}
