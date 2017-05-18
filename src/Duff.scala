/**
  * @author Suhel Hammoud
  *         Reference: http://codeforces.com/problemset/problem/588/A
  */

object Duff extends App {

  val n = scala.io.StdIn.readLine().toInt

  val inputs = for (ln <- 1 to n)
    yield scala.io.StdIn.readLine()

  val plans = inputs.map(_.split(" ").map(_.toInt).toList)

  val result = plans.map(e => (e(0), e(1), e(0) * e(1)))
    .reduce((e1, e2) => {
      val price = e1._2 min e2._2;
      (e2._1, price, e1._3 + e2._1 * price)
    })

  println(result._3)

}

