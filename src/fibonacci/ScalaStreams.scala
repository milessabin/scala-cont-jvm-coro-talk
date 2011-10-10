package fibonacci

object ScalaStreams {
  def main(args : Array[String]) {
    lazy val fibs: Stream[Int] = 0 #:: 1 #:: fibs.zip(fibs.tail).map(p => p._1 + p._2)

    for(i <- fibs take 10)
      println(i)
  }
}
