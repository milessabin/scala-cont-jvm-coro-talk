package benchmarks

class ScalaStream extends Fibonacci {
  lazy val fibs: Stream[Int] = 0 #:: 1 #:: fibs.zip(fibs.tail).map(p => p._1 + p._2)

  def exercise = (fibs drop 10) head
  
  def benchmark(iterations : Int) = (fibs drop iterations) head
}
