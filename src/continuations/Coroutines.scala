package continuations

import scala.collection.mutable.Queue
import scala.util.continuations._

object Coroutines {
  sealed trait Trampoline
  case object Done extends Trampoline
  case class Continue(next : Unit => Trampoline) extends Trampoline

  object Scheduler {
    val ready = new Queue[Unit => Trampoline]

    def yld {
      if(!ready.isEmpty) {
        val thunk = ready.dequeue()
        thunk() match {
          case Continue(next) => ready.enqueue(next)
          case _ =>
        }
      }
    }

    def addThunk(thunk : Unit => Trampoline) {
      ready.enqueue(thunk)
    }
  }

  def coroutine(body : => Unit @cps[Trampoline]) : Unit = 
    Scheduler.addThunk((Unit) => reset { body ; Done })

  def yld : Unit @cps[Trampoline] = {
    shift { (cont : Unit => Trampoline) => Continue(cont) } 
  }
}

object TestCoroutines {
  import Coroutines._
  
  def main(args: Array[String]) {
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

    for(_ <- 1 until 20)
      Scheduler.yld
  }
}
