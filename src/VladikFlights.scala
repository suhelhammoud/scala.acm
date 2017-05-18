/**
  * @author Suhel Hammoud
  *         Reference: http://codeforces.com/problemset/problem/743/A
  */

import scala.io.StdIn.readLine

object VladikFlights extends App {

  val Array(n, a, b) = readLine.split("\\s+").map(_.toInt - 1)

  val airports = readLine.map(e => if (e == '0') 0 else 1)

  val sameAirport = airports(a) == airports(b)

  val result = if (sameAirport) 0 else 1

  println(result)

}
