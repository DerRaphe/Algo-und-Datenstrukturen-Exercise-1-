package DivideAndConquerQuickSort;


import java.util.ArrayList;
import java.util.List;

public class QuickSortInsertion implements DivideAndConquerable<List<Integer>> {
  private int baseCase = 1;
  private List<Integer> listToSort;


  public QuickSortInsertion(List<Integer> listToSort) {
    this.listToSort = listToSort;
  }

  @Override
  public boolean isBasic() {
    return this.listToSort.size() <= this.baseCase;
  }

  @Override
  public List<Integer> baseFun() {
    return this.listToSort;
  }

  @Override
  public List<? extends DivideAndConquerable<List<Integer>>> decompose() 
    {
     int left = 0;
     int right = listToSort.size();
     List<QuickSortInsertion> decomposed = new ArrayList<QuickSortInsertion>();
     int pivot = swap(listToSort.get(left),listToSort.get(right/2),listToSort.get(right-1));
     for (int i = 0; i<right;i++) {
       
     }
     return decomposed;
  }

  @Override
  public List<Integer> recombine(List<List<Integer>> intermediateResults) {
    List<Integer> recombine = new ArrayList<Integer>();
    intermediateResults.forEach(t ->{
      recombine.addAll(t);
    });
    return recombine;
  }
  
  
  private int swap (int first, int second, int third) {
    int ret;
    if (first<=second)
      ret = second;
    else 
      ret = first;
    if (third<=ret)
      return ret;
    return third;
  }
}
