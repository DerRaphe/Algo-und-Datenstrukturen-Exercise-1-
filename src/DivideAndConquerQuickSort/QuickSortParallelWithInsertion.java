package DivideAndConquerQuickSort;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuickSortParallelWithInsertion
    implements DivideAndConquerableParallel<List<Integer>> {
  private int isSmallEnough = 1;
  private List<Integer> listToSort;
  private ForkJoinPool forkJoinPool;
  private int countOfThreads;
  private boolean isInitalised;
  private int isTooSmallForMultiThreading;
  private int insertionSortBorder;


  public QuickSortParallelWithInsertion(List<Integer> listToSort,int insertionSortBorder, int isTooSmallForMultiThreading, int threadCount) {
    this.listToSort = listToSort;
    this.countOfThreads = threadCount;
    this.isTooSmallForMultiThreading = isTooSmallForMultiThreading;
  }

  @Override
  public boolean isBasic() {
    return this.listToSort.size() <= this.insertionSortBorder;
  }

  @Override
  public List<Integer> baseFun() {
    if (this.listToSort.size() > 1) {
      for (int j = 1; j < this.listToSort.size(); j++) { // iterate right
        Integer value = this.listToSort.get(j); // read right element
        int i = j - 1; // define initial left position
        while (i >= 0 && (this.listToSort.get(i) > value)) {
          this.listToSort.set(i + 1, this.listToSort.get(i)); // while left >right , move left right
          i--;
        }
        this.listToSort.set(i + 1, value); // put right into its proper place
      }
    }
    return this.listToSort;
  }

  @Override
  public List<? extends DivideAndConquerable<List<Integer>>> decompose() {
    {
      int left = 0;
      int right = listToSort.size();
      List<QuickSortParallelWithInsertion> decomposed = new ArrayList<QuickSortParallelWithInsertion>();
      List<Integer> leftList = new ArrayList<>();
      List<Integer> rightList = new ArrayList<>();
      int pivot = meadianOfThree(listToSort.get(left),listToSort.get(right/2),listToSort.get(right-1));
      for (int i = 0; i<right;i++) {
        if(listToSort.get(i)>pivot)
          rightList.add(listToSort.get(i));
        else {
          if (i == right-1 && leftList.size() == i) {
            rightList.add(listToSort.get(i));
          }
          else
            leftList.add(listToSort.get(i));
        }
      }
      decomposed.add(new QuickSortParallelWithInsertion(leftList,this.insertionSortBorder,this.isTooSmallForMultiThreading,this.countOfThreads));
      decomposed.add(new QuickSortParallelWithInsertion(rightList,this.insertionSortBorder,this.isTooSmallForMultiThreading,this.countOfThreads));
      return decomposed;
      
    }


  }

  @Override
  public List<Integer> recombine(List<List<Integer>> intermediateResults) {
    List<Integer> recombine = new ArrayList<Integer>();
    recombine.addAll(intermediateResults.get(0));
    recombine.addAll(intermediateResults.get(1));
    return recombine;
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

  private int meadianOfThree (int first, int second, int third) {
    int max = Integer.max(first, Integer.max(second,third));
    int min = Integer.min(first,Integer.min(second,third));
    if(first <max && first > min) 
      return first;
    if(second <max && second > min) 
      return second;    
    return third;
  }
}
