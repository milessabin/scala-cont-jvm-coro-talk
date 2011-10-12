package continuations

import scala.annotation.tailrec
import scala.collection.immutable.Stream
import scala.util.continuations._

object Generators {
  sealed trait Trampoline[+T]
  case object Done extends Trampoline[Nothing]
  case class Continue[T](result : T, next : Unit => Trampoline[T]) extends Trampoline[T]

  class Generator[T](var cont : Unit => Trampoline[T]) extends Iterator[T] {
    def next : T = {
      cont() match {
        case Continue(r, nextCont) => cont = nextCont; r
        case _ => sys.error("Generator exhausted")
      }
    }
    
    def hasNext = cont() != Done
  }

  type Gen[T] = cps[Trampoline[T]]
  
  def generator[T](body : => Unit @Gen[T]) : Generator[T] = {
    new Generator((Unit) => reset { body ; Done })
  }

  def yld[T](t : T) : Unit @Gen[T] =
    shift { (cont : Unit => Trampoline[T]) => Continue(t, cont) }
}

object TestGenerators {
  import Generators._
  
  def main(args : Array[String]) {
    val fibs = generator {
      //@tailrec
      def fib(s0 : Int, s1 : Int) : Unit @Gen[Int] = {
        yld(s0)
        fib(s1, s0+s1)
      }
      
      fib(0, 1)
    }
    
    for(i <- fibs take 10)
      println(i)
  }
}
