package coroapi
import java.dyn.Coroutine
import Coroutine._

object Coroutines {
  class HiHo(val hiho : String) extends Coroutine {
    override def run {
      while (true) {
        println(hiho)
        `yield`()
      }
    }
  }
  
  def main(args : Array[String]) {
    val hi = new HiHo("Hi!");
    val ho = new HiHo("Ho!");
    
    for(_ <- 0 until 10)
      `yield`()
  }
}
