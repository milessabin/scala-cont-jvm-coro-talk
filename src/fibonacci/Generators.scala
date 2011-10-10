package fibonacci

import scala.collection.immutable.Stream
import scala.util.continuations._

object Generators {
  sealed trait Trampoline[+T]
  case object Done extends Trampoline[Nothing]
  case class Continue[T](result : T, next : Unit => Trampoline[T]) extends Trampoline[T]

  class Generator[T](var cont : Unit => Trampoline[T]) {
    def next : Option[T] = {
      cont() match {
        case Continue(r, nextCont) => cont = nextCont; Some(r)
        case _ => None
      }
    }

    def toStream : Stream[T] =
      next match {
        case Some(r) => Stream.cons(r, toStream)
        case None => Stream.empty[T]
      }
  }

  def generator[T](body : => Unit @cps[Trampoline[T]]) : Generator[T] = {
    new Generator((Unit) => reset { body ; Done })
  }

  def yld[T](t : T) : Unit @cps[Trampoline[T]] =
    shift { (cont : Unit => Trampoline[T]) => Continue(t, cont) }
}
