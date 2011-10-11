package fibonacci

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

  def generator[T](body : => Unit @cps[Trampoline[T]]) : Generator[T] = {
    new Generator((Unit) => reset { body ; Done })
  }

  def yld[T](t : T) : Unit @cps[Trampoline[T]] =
    shift { (cont : Unit => Trampoline[T]) => Continue(t, cont) }
}
