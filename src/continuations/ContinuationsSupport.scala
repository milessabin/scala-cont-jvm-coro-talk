package continuations

import scala.collection.{ GenTraversableOnce, IterableLike }
import scala.collection.generic.CanBuildFrom
import scala.util.continuations._

object ContinuationsSupport {
  implicit def cpsIterable[A, Repr](xs: IterableLike[A, Repr]) = new {
    def cps = new {
      def foreach[B, C](f: A => Any@cps[C]): Unit@cps[C] = {
        val it = xs.iterator
        while(it.hasNext) f(it.next)
      }
      def map[B, That, C](f: A => B@cps[C])(implicit cbf: CanBuildFrom[Repr, B, That]): That@cps[C] = {
        val b = cbf(xs.repr)
        foreach(b += f(_))
        b.result
      }
      def flatMap[B, That, C](f: A => GenTraversableOnce[B]@cps[C])(implicit cbf: CanBuildFrom[Repr, B, That]): That@cps[C] = {
        val b = cbf(xs.repr)
        for (x <- this) b ++= f(x)
        b.result
      }
    }
  }
}
