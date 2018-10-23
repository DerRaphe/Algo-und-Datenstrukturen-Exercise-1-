package DivideAndConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListFactory {
  private int length =300;
  private List<Integer> listToSort;
  private Random rand = new Random();
  
  public ListFactory() {
    listToSort = new ArrayList<Integer>();
    for (int i = 0; i<this.length; i++) {
      listToSort.add(rand.nextInt(300)+1);
    }
  }
  
  public List<Integer> giveListToSort(int length) {
    return listToSort.subList(0, length);
  }
  
}
