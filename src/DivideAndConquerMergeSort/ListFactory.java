package DivideAndConquerMergeSort;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListFactory {
  private int listLength = 300;
  private List<Integer> listToSort;
  private Random rand = new Random();

  public ListFactory(int listLength) {
    this.listLength = listLength;
    listToSort = new ArrayList<Integer>();
    for (int i = 0; i < this.listLength; i++) {
      listToSort.add(rand.nextInt(300) + 1);
    }
  }

  public List<Integer> giveListToSort(int length) {
    return listToSort.subList(0, length);
  }

}
