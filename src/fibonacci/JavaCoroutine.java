package fibonacci;

import java.dyn.AsymCoroutine;

public class JavaCoroutine extends AsymCoroutine<Void, Integer> implements Fibonacci {
  
  public Integer run(Void v) {
    int s0 = 0;
    int s1 = 1;
    while (true) {
      ret(s0);
      int tmp = s0;
      s0 = s1;
      s1 += tmp;
    }
  }
  
  public int exercise() {
    for(int i = 0; i < 10; ++i) {
      call();
    }
    return call();
  }

  public int benchmark(int iterations) {
    int i = 0;
    while(i < iterations) {
      call();
      i += 1;
    }
    return call();
  }
}
