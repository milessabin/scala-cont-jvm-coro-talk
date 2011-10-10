package fibonacci

import scala.collection.immutable.Stream
import scala.util.continuations._

object ScalaContinuations {
  import Generators._
  
  def main(args: Array[String]) {
    val gen = generator {
      var f = (0, 1)
      while(true) {
        yld(f._1)
        f = (f._2, f._1+f._2)
      }
    }

    for(i <- gen.toStream take 10)
      println(i)
  }
}
