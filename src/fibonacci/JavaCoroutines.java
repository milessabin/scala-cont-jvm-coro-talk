package fibonacci;

import java.dyn.AsymCoroutine;

public class JavaCoroutines extends AsymCoroutine<Void, Integer> {
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
  
  public static void main(String[] args) {
    JavaCoroutines gen = new JavaCoroutines();
    for(int i = 0; i < 10; ++i) {
      System.out.println(gen.call());
    }
  }
}
