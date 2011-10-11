package fibonacci

import Generators._

class ScalaGenerator extends Fibonacci {
  val gen = generator {
    var s0 = 0
    var s1 = 1
    while (true) {
      yld(s0)
      val tmp = s0
      s0 = s1
      s1 += tmp
    }
  }
  
  def exercise = {
    for(i <- 0 until 10)
      gen.next
    gen.next  
  }

  def benchmark(iterations : Int) = {
    var i = 0
    while (i < iterations) {
      gen.next
      i += 1
    }
    gen.next
  }
}
