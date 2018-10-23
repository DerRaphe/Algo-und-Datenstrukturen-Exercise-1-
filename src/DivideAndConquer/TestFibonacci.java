package DivideAndConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.omg.CORBA.Environment;

public class TestFibonacci {

  public static void main(String[] args) {
    int numberOfThreads = 4;
    int fibNumber = 10;
    List<Integer> results = new ArrayList<>();

    //ITERATIV FIBONACCI 
    FibonacciStandart dnc = new FibonacciStandart(fibNumber);
    System.out.println("Standard Fibonacci Result: " + dnc.divideAndConquer());

    ExecutionTimer<Integer> timerStandard = new ExecutionTimer<Integer>(() -> {
      return dnc.divideAndConquer();
    });
    //System.out.println("Execution Time: " + timerStandard.time);

    //PARALLEL FIBONACCI 
    FibonacciParallel dncP = new FibonacciParallel(fibNumber, numberOfThreads);
    System.out.println("Parallel Fibonacci Result: " + dncP.divideAndConquer());

    ExecutionTimer<Integer> timerParallel = new ExecutionTimer<Integer>(() -> {
      return dncP.divideAndConquer();
    });
    //System.out.println("Execution Time: " + timerParallel.time);


  }

}
