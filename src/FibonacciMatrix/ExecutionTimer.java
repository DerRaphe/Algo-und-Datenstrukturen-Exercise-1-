package FibonacciMatrix;



import java.util.List;
import java.util.function.Supplier;
import java.math.BigInteger;

public class ExecutionTimer<T> {

  public T result = null;
  public long time = 0;

  public ExecutionTimer(final Supplier<T> code) {
    long start = System.nanoTime();
    this.result = code.get();
    long end = System.nanoTime();
    this.time = end - start;
  }
}


