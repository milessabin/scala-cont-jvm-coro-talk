package scoroapi

import java.dyn.Coroutine
import Coroutine._

object Coroutines {
  def coroutine(body : => Unit) = new Coroutine {
    override def run {
      body
    }
  }
  
  def yld = Coroutine.`yield`
}

object TestCoroutines {
  import Coroutines._
  
  def main(args : Array[String]) {
    coroutine {
      while(true) {
        println("Hi!")
        yld
      }
    }

    coroutine {
      while(true) {
        println("Ho!")
        yld
      }
    }

    for(_ <- 0 until 10)
      yld
  }
}
