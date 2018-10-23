package DivideAndConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FibonacciParallel implements DivideAndConquerableParallel <Integer> {
  
  private Integer fibbNum;
  private boolean isInitialised;
  private ExecutorService executor;
  final private int numberOfThreads;
  private ThreadMonitor threadsUsed;
  
  
  public FibonacciParallel(Integer fibbNum, int numberOfThreads) {
    this.fibbNum = fibbNum;
    this.numberOfThreads = numberOfThreads;
    this.threadsUsed = new ThreadMonitor();
  }
  
  public FibonacciParallel(Integer fibbNum, boolean isInitialised,ExecutorService executor,int numberOfThreads, ThreadMonitor threadsUsed) {
    this.fibbNum = fibbNum;
    this.executor = executor;
    this.isInitialised = isInitialised;
    this.numberOfThreads = numberOfThreads;
    this.threadsUsed = threadsUsed;
  }
  
  @Override
  public boolean isBasic() {
    return (this.fibbNum == 1 || this.fibbNum == 2);  
  }

  @Override
  public Integer baseFun() {
    return 1;
  }

  @Override
  public List decompose() {
    List<FibonacciParallel> subComponents = new ArrayList<>();
    subComponents.add(new FibonacciParallel(this.fibbNum-1,this.isInitialised,this.executor,this.numberOfThreads,this.threadsUsed));
    subComponents.add(new FibonacciParallel(this.fibbNum-2,this.isInitialised,this.executor,this.numberOfThreads,this.threadsUsed));
    return subComponents;
  }

  @Override
  public Integer recombine(List intermediateResults) {
    if(intermediateResults == null)
      return null;
    
     return (Integer)intermediateResults.get(0) + (Integer)intermediateResults.get(1) ;
  }

  @Override
  public boolean threadPoolIsInitailised() {
    return isInitialised;
  }

  @Override
  public boolean allThreadsAreUsed() {
    if (this.threadsUsed.getNumber()<numberOfThreads)
      return false;
    return true;
  }

  @Override
  public void setThreadPool() {
    this.isInitialised = true;
    this.executor =Executors.newFixedThreadPool(numberOfThreads);
  }

  @Override
  public ExecutorService getThreadPool() {
    return this.executor;
  }

  @Override
  public void countUp() {
    this.threadsUsed.setInt();
  }

  @Override
  public boolean isTooSmallForMultiThreading() {
    return true;
  }


}
