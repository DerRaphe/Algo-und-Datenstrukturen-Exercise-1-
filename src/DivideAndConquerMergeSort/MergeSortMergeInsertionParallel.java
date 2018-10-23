package DivideAndConquerMergeSort;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MergeSortMergeInsertionParallel
    implements DivideAndConquerableParallel<List<Integer>> {
  private int isSmallEnough = 1;
  private List<Integer> listToSort;
  private ForkJoinPool forkJoinPool;
  private int countOfThreads;
  private boolean isInitalised;
  private int isTooSmallForMultiThreading;


  public MergeSortMergeInsertionParallel(List<Integer> listToSort,int isTooSmallForMultiThreading, int threadCount) {
    this.listToSort = listToSort;
    this.countOfThreads = threadCount;
    this.isTooSmallForMultiThreading = isTooSmallForMultiThreading;
  }

  @Override
  public boolean isBasic() {
    return this.listToSort.size() <= this.isSmallEnough;
  }

  @Override
  public List<Integer> baseFun() {
    return this.listToSort;
  }

  @Override
  public List<? extends DivideAndConquerable<List<Integer>>> decompose() {
    {
      List<MergeSortMergeInsertionParallel> newList = new ArrayList<>();
      int firstHalf = listToSort.size() / 2;
      int secondHalf = listToSort.size();
      newList.add(new MergeSortMergeInsertionParallel(listToSort.subList(0, firstHalf) ,this.isTooSmallForMultiThreading, this.countOfThreads));
      newList.add(new MergeSortMergeInsertionParallel(listToSort.subList(firstHalf, secondHalf), this.isTooSmallForMultiThreading, this.countOfThreads));
      return newList;
    }


  }

  @Override
  public List<Integer> recombine(List<List<Integer>> intermediateResults) {
    List<Integer> recombinedList = new ArrayList<Integer>();
    intermediateResults.forEach(s -> {
      recombinedList.addAll(s);
      if (recombinedList.size() > 1) {
        for (int j = 1; j < recombinedList.size(); j++) { // iterate right
          Integer value = recombinedList.get(j); // read right element
          int i = j - 1; // define initial left position
          while (i >= 0 && (recombinedList.get(i) > value)) {
            recombinedList.set(i + 1, recombinedList.get(i)); // while left >right , move left right
            i--;
          }
          recombinedList.set(i + 1, value); // put right into its proper place
        }
      }
    });

    return recombinedList;
  }

  @Override
  public boolean threadPoolIsInitailised() {
    return this.isInitalised;
  }

  @Override
  public boolean isTooSmallForMultiThreading() {
    return this.listToSort.size() <= this.isTooSmallForMultiThreading;
  }

  @Override
  public void setThreadPool() {
    this.forkJoinPool = new ForkJoinPool(this.countOfThreads);
    this.isInitalised = true;
  }

  @Override
  public ForkJoinPool getThreadPool() {
    return forkJoinPool;
  }

  @Override
  public int numberOfThreadPool() {
    return this.countOfThreads;
  }

}
