package DivideAndConquerQuickSort;


import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

public interface DivideAndConquerableParallel<OutputType> extends DivideAndConquerable<OutputType> {
  boolean threadPoolIsInitailised();

  boolean isTooSmallForMultiThreading();

  int numberOfThreadPool();

  void setThreadPool();

  ForkJoinPool getThreadPool();

  ReentrantLock lock = new ReentrantLock();
  
  @Override
  default OutputType divideAndConquer() {
    OutputType output;
    if (this.isBasic())
      return (OutputType) this.baseFun();
   
    List<? extends DivideAndConquerable<OutputType>> subcomponents = this.decompose();
    
    List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());
    if (!threadPoolIsInitailised())
      setThreadPool();
    for (int i = 0; i < subcomponents.size(); i++) {
      if (getThreadPool().getPoolSize() > numberOfThreadPool() || isTooSmallForMultiThreading()) {
        intermediateResults.add(subcomponents.get(i).divideAndConquer());
      } else {
        DivideAndConquerable<OutputType> giveToPool = subcomponents.get(i);
        getThreadPool().submit(() -> {
          
          intermediateResults.add(giveToPool.divideAndConquer());
          
        });
      }
      
    }
    try {
      getThreadPool().shutdown();
      while (!getThreadPool().awaitTermination(24L, TimeUnit.HOURS)) {

      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
   

    output = recombine(intermediateResults);
    return output;
  }
}
