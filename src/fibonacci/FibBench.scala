package fibonacci

import scala.collection.mutable.ArrayBuffer

object FibBench {
  def time(body : => Unit) : Long = {
    val start = System.currentTimeMillis()
    body
    val end = System.currentTimeMillis()
    end-start
  }
  
  def main(args : Array[String]) {
    def newFib = Class.forName(args(0)).newInstance.asInstanceOf[Fibonacci] 
    
    args(1) match {
      case "--benchmark" =>
        println("Benchmarking "+args(0))
        val iterations = args(2).toInt
        var acc = 0
        for(i <- 0 until 10) {
          val fib = newFib
          val elapsed = time { acc += (fib.benchmark(iterations)) } 
          println("t: "+((elapsed*1000*1000)/iterations).toInt+"ns")
        }
      
      case "--footprint" =>
        println("Measuring footprint of "+args(0))
        val verbose = args.length > 2 && args(2) == "-v"
        var n = 0
        try {
          val fibs = new ArrayBuffer[Fibonacci]
          var acc = 0
          while(true) {
            val fib = newFib
            acc += fib.exercise
            fibs += fib
            n += 1
            if (verbose && n % 1000 == 0)
              println(n)
          }
        } catch {
          case _ => println(n)
        }
    }
  }
}
