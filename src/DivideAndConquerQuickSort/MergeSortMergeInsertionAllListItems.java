package DivideAndConquerQuickSort;


import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class MergeSortMergeInsertionAllListItems implements DivideAndConquerable<List<Integer>> {
  private int isSmallEnough = 1;
  private List<Integer> listToSort;


  public MergeSortMergeInsertionAllListItems(List<Integer> listToSort) {
    this.listToSort = listToSort;
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
      List<MergeSortMergeInsertionAllListItems> newList = new ArrayList<>();
      int firstHalf = listToSort.size() / 2;
      int secondHalf = listToSort.size();
      newList.add(new MergeSortMergeInsertionAllListItems(listToSort.subList(0, firstHalf)));
      newList.add(new MergeSortMergeInsertionAllListItems(listToSort.subList(firstHalf, secondHalf)));
      return newList;
    }
  }

  @Override
  public List<Integer> recombine(List<List<Integer>> intermediateResults) {
    List<Integer> recombinedList = new ArrayList<Integer>();

    intermediateResults.forEach(list -> {
      recombinedList.addAll(list);
    });

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
    } ;

    return recombinedList;
  }
}
