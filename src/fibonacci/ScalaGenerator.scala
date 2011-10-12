package fibonacci

import scala.util.continuations._
import continuations.Generators._

class ScalaGenerator extends Fibonacci {
  val gen = generator {
    def fib(s0 : Int, s1 : Int) : Unit @Gen[Int] = {
      yld(s0)
      fib(s1, s0+s1)
    }
    
    fib(0, 1)
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
