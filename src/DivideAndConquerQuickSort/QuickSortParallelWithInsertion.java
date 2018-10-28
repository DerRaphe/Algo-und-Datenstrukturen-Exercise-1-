package DivideAndConquerQuickSort;


import java.util.ArrayList;
import java.util.Collections;
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
  private int insertionSortBorder;
  private int left;
  private int right;


  public QuickSortParallelWithInsertion(List<Integer> listToSort,int insertionSortBorder, int threadCount, int left, int right) {
    this.listToSort = listToSort;
    this.countOfThreads = threadCount;
    this.insertionSortBorder= insertionSortBorder;
    this.left = left;
    this.right = right;

  }

  @Override
  public synchronized boolean isBasic() {
    return this.right- this.left <= this.insertionSortBorder;
  }

  @Override
  public synchronized List<Integer> baseFun() {
    if (this.listToSort.size() > 1) {
      for (int j = this.left; j < this.right; j++) { // iterate right
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
  public synchronized List<? extends DivideAndConquerable<List<Integer>>> decompose() {
    {
      Collections.swap(this.listToSort, meadianOfThree(this.left,this.right), this.right);
      int mid = partition ();
      List<QuickSortParallelWithInsertion> ret = new ArrayList<QuickSortParallelWithInsertion>();
      ret.add(new QuickSortParallelWithInsertion(this.listToSort,this.insertionSortBorder,this.countOfThreads,mid+1,this.right));
      ret.add(new QuickSortParallelWithInsertion(this.listToSort,this.insertionSortBorder,this.countOfThreads,this.left,mid-1));
      return ret;
      
    }


  }

  @Override
  public List<Integer> recombine(List<List<Integer>> intermediateResults) {
    return intermediateResults.get(1);
  }

  @Override
  public boolean threadPoolIsInitailised() {
    return this.isInitalised;
  }

  @Override
  public boolean isTooSmallForMultiThreading() {
    return false;
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

  public int meadianOfThree (int firstIndex, int thirdIndex) {
    if ((thirdIndex - firstIndex +1)>=3) {
    int first = this.listToSort.get(firstIndex);
    int second = this.listToSort.get((firstIndex+thirdIndex)/2);
    int third = this.listToSort.get(thirdIndex);
    int max = Integer.max(first, Integer.max(second,third));
    int min = Integer.min(first,Integer.min(second,third));
    if(first <max && first > min) 
      return firstIndex;
    if(second <max && second > min) 
      return   (firstIndex+thirdIndex)/2;    
    return thirdIndex;
    } else
      return thirdIndex;
  }
  
  public int partition() {
    int pivot = this.listToSort.get(this.right);
    int i = left ;
    int j = right ;
    while (i <j ){
      while (i <j && this.listToSort.get(i) <pivot)
        i++; // move right ( paint green ) in left partition
      while (j >i && this.listToSort.get(j) >=pivot)
        j--; // move left ( paint orange ) in right partition
      if (i <j) 
         Collections.swap(this.listToSort, i,j);
    }
    Collections.swap(this.listToSort, i,this.right); // "orange - yellow swap "
    return i; // return mid - element

  }
}
