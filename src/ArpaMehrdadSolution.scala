/**
  * @author Suhel Hammoud
  *         Reference: http://codeforces.com/problemset/problem/742/B
  */


//failed due to time limit exceeded
object ArpaMehrdadSolution extends App {
  val Array(n, x) = scala.io.StdIn.readLine().split(" ").map(_.toInt)
  val arr = scala.io.StdIn.readLine().split(" ").map(_.toInt)

  val r = (0 until n).combinations(2)
    .filter(e => (arr(e(0)) ^ arr(e(1))) == x).size

  println(r)
}
