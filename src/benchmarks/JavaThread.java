package benchmarks;

import java.util.concurrent.ArrayBlockingQueue;

public class JavaThread extends Thread implements Fibonacci {
  private final ArrayBlockingQueue<Integer> output = new ArrayBlockingQueue<>(1);

  JavaThread() {
    setDaemon(true);
    start();
  }
  
  public int next() {
    try {
      return output.take();
    } catch(InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  public void run() {
    try {
      int s0 = 0;
      int s1 = 1;
      while (true) {
        output.put(s0);
        int tmp = s0;
        s0 = s1;
        s1 += tmp;
      }
    } catch(InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }

  public int exercise() {
    for(int i = 0; i < 10; ++i)
      next();
    return next(); 
  }

  public int benchmark(int iterations) {
    int i = 0;
    while(i < iterations) {
      next();
      i += 1;
    }
    return next();
  }
}
