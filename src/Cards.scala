/**
  * @author Suhel Hammoud
  *         Reference: http://codeforces.com/problemset/problem/743/E
  */


import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer

object Cards extends App {

  def toBits(n: Int) =
    (0 to 7).map(i => ((1 << i) & n) != 0)

  def toFreqPattern(freq: Int, seq: Seq[Boolean]) =
    seq.map(if (_) freq + 1 else freq)

  def frequencyPatternsFor(n: Int) =
    (1 to 255).map(toBits).map(toFreqPattern(n, _))

  def isAcceptable(pattern: Seq[Int], frequency: Int): Boolean = {
    if (frequency * 8 > data.size) return false

    var pos = 0
    var patternPos = 0
    var counter = 0
    var result = 0

    while (true) {
      if (data(pos) == pattern(patternPos)) {
        counter += 1
        if (counter == frequency) {
          patternPos += 1
          result += counter
          counter = 0
        }
      }
      pos += 1

      if (pos == data.size || patternPos == pattern.size)
        return result == (8 * frequency)
    }
    return false
  }

  def getMaxFrequency(pattern: Seq[Int], initFrequency: Int): Int = {
    if (!isAcceptable(pattern, initFrequency)) {
      return 0;
    }
    var frequency = initFrequency
    while (true) {
      frequency += 1
      val canApplyPatternFreq = isAcceptable(pattern, frequency)
      if (!canApplyPatternFreq) return frequency - 1
    }
    return 0; // never reached
  }

  def scanData(pattern: Seq[Int], frequencyPattern: Seq[Int]): Int = {
    var index = 0
    var patternIndex = 0
    var counter = 0
    var result = 0

    while (true) {
      if (data(index) == pattern(patternIndex)) {
        counter += 1
        if (counter == frequencyPattern(patternIndex)) {
          patternIndex += 1
          result += counter
          counter = 0
        }
      }
      index += 1

      if (index == data.size || patternIndex == pattern.size)
        return result
    }
    return 0
  }

  def getMaxFrequencyPatterns = {
    var result: ListBuffer[Seq[Int]] = ListBuffer()
    var maxFreq = 1
    Array(1, 2, 3, 4, 5, 6, 7, 8).permutations
      .foreach { pattern =>
        val frq = getMaxFrequency(pattern, maxFreq)
        if (frq > maxFreq) {
          maxFreq = frq
          result = ListBuffer(pattern)
        } else if (frq == maxFreq) {
          result += pattern
        }
      }
    (maxFreq, result)
  }

  /* Solution */
  val n = readLine.toInt
  val data = readLine.split(" ").map(_.toInt)

  val distinct = data.distinct.size

  val result = if (distinct < 8) {
    distinct
  } else {
    val (maxFrequency, acceptedPatterns) = getMaxFrequencyPatterns
    frequencyPatternsFor(maxFrequency)
      .map(fPattern => acceptedPatterns.map(pattern =>
        scanData(pattern, fPattern)))
      .flatten
      .max max (maxFrequency * 8)
  }

  println(result)
}
