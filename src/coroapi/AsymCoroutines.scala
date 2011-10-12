package coroapi

import scala.annotation.tailrec
import scala.collection.JavaConverters._
import java.dyn.AsymCoroutine

object AsymCoroutines {

  class Fibs extends AsymCoroutine[Nothing, Int] {
    override def run(n : Nothing) : Int = {
      @tailrec
      def fib(s0 : Int, s1 : Int) {
        ret(s0)
        fib(s1, s0+s1)
      }
      
      fib(0, 1)
      
      sys.error("Not reached")
    }
  }
  
  def main(args : Array[String]) {
    val fibs = new Fibs
    for(i <- fibs.iterator.asScala take 10)
      println(i)
  }
}