package fibonacci;

import java.util.concurrent.ArrayBlockingQueue;

public class JavaThreads implements Runnable {
  private final ArrayBlockingQueue<Integer> output = new ArrayBlockingQueue<>(1);
  
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
  
  public static void main(String[] args) throws InterruptedException {
    JavaThreads gen = new JavaThreads();
    Thread t = new Thread(gen);
    t.setDaemon(true);
    t.start();
    
    for(int i = 0; i < 10; ++i) {
      System.out.println(gen.output.take());
    }
  }
}
