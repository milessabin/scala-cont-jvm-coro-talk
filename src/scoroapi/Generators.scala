package scoroapi

import scala.annotation.tailrec
import scala.annotation.unchecked.uncheckedVariance
import java.dyn.AsymCoroutine

object Generators {
  
  class Generator[T](private[this] val body : Generator[T] => Unit) extends AsymCoroutine[Nothing, T] @uncheckedVariance with Iterator[T] {
    def next : T = call
    def hasNext = !isFinished
    
    override def run(n : Nothing) : T = { 
      body(this)
      sys.error("Unreachable")
    }
  }
  
  def generator[T](body : Generator[T] => Unit) = new Generator(body) 
  
  def yld[T](r : T)(implicit gen : Generator[T]) : T = gen.ret(r)
}

object TestGenerators {
  import Generators._
  
  def main(args : Array[String]) {
    val fibs = generator[Int] { implicit gen =>
      @tailrec
      def fib(s0 : Int, s1 : Int) {
        yld(s0)
        fib(s1, s0+s1)
      }
      
      fib(0, 1)
    }
    
    for(i <- fibs take 10)
      println(i)
  }
}
