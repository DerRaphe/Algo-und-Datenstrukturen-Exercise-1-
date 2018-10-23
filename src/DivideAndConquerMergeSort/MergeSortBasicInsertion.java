package DivideAndConquerMergeSort;


import java.util.ArrayList;
import java.util.List;

public class MergeSortBasicInsertion implements DivideAndConquerable<List<Integer>> {
  private int isToSmallForMergeSort;
  private List<Integer> listToSort;


  public MergeSortBasicInsertion(List<Integer> listToSort, int isToSmallForMergeSort) {
    this.isToSmallForMergeSort = isToSmallForMergeSort;
    this.listToSort = listToSort;
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
      List<MergeSortBasicInsertion> newList = new ArrayList<>();
      int firstHalf = listToSort.size() / 2;
      int secondHalf = listToSort.size();
      newList.add(new MergeSortBasicInsertion(listToSort.subList(0, firstHalf),
          this.isToSmallForMergeSort));
      newList.add(new MergeSortBasicInsertion(listToSort.subList(firstHalf, secondHalf),
          this.isToSmallForMergeSort));
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
}
