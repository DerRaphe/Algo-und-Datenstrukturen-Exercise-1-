package DivideAndConquerMergeSort;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MergeSortBasicInsertionParallel
    implements DivideAndConquerableParallel<List<Integer>> {
  private int isToSmallForMergeSort;
  private List<Integer> listToSort;
  private ForkJoinPool forkJoinPool;
  private int countOfThreads;
  private boolean isInitalised;
  private int isTooSmallForMultiThreading;


  public MergeSortBasicInsertionParallel(List<Integer> listToSort, int isToSmallForMergeSort, int isTooSmallForMultiThreading, int threadCount) {
    this.isToSmallForMergeSort = isToSmallForMergeSort;
    this.isTooSmallForMultiThreading = isTooSmallForMultiThreading;
    this.listToSort = listToSort;
    this.countOfThreads =threadCount;
  }

  @Override
  public boolean isBasic() {
    return this.listToSort.size() <= this.isToSmallForMergeSort;
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
      List<MergeSortBasicInsertionParallel> newList = new ArrayList<>();
      int firstHalf = listToSort.size() / 2;
      int secondHalf = listToSort.size();
      newList.add(new MergeSortBasicInsertionParallel(listToSort.subList(0, firstHalf),
          this.isToSmallForMergeSort, this.isTooSmallForMultiThreading, this.countOfThreads));
      newList.add(new MergeSortBasicInsertionParallel(listToSort.subList(firstHalf, secondHalf),
          this.isToSmallForMergeSort, this.isTooSmallForMultiThreading, this.countOfThreads));
      return newList;
    }


  }

  @Override
  public List<Integer> recombine(List<List<Integer>> intermediateResults) {
    List<Integer> recombinedList = new ArrayList<Integer>();
    int i = 0;
    int j = 0;
    int size1 = intermediateResults.get(1).size();
    int size2 = intermediateResults.get(0).size();
    while (i < size1 || j < size2) {
      if (i == size1) {
        recombinedList.addAll(intermediateResults.get(0).subList(j, size2));
        j = size2;
      } else if (j == size2) {
        recombinedList.addAll(intermediateResults.get(1).subList(i, size1));
        i = size1;
      } else {
        if (intermediateResults.get(1).get(i) >= intermediateResults.get(0).get(j)) {
          recombinedList.add(intermediateResults.get(0).get(j++));
        } else {
          recombinedList.add(intermediateResults.get(1).get(i++));
        }
      }
    }

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
