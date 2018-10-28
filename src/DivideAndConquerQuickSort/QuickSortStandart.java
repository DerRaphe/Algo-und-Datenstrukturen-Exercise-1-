package DivideAndConquerQuickSort;


import java.util.ArrayList;
import java.util.List;

public class QuickSortStandart implements DivideAndConquerable<List<Integer>> {
  final private int baseCase = 1;
  private List<Integer> listToSort;


  public QuickSortStandart(List<Integer> listToSort) {
    this.listToSort = listToSort;
  }

  @Override
  public boolean isBasic() {
    return this.listToSort.isEmpty() || this.listToSort.size() <= this.baseCase;
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
     List<QuickSortStandart> decomposed = new ArrayList<QuickSortStandart>();
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
     decomposed.add(new QuickSortStandart(leftList));
     decomposed.add(new QuickSortStandart(rightList));
     return decomposed;
  }

  @Override
  public List<Integer> recombine(List<List<Integer>> intermediateResults) {
    List<Integer> recombine = new ArrayList<Integer>();
    recombine.addAll(intermediateResults.get(0));
    recombine.addAll(intermediateResults.get(1));
    return recombine;
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
