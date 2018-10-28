package DivideAndConquerQuickSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort implements DivideAndConquerable<List<Integer>> {
  private List<Integer> listToSort;
  private int left;
  private int right;


  public QuickSort(List<Integer> listToSort, int left, int right) {
    this.listToSort = listToSort;
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean isBasic() {
    return this.right < this.left;
  }

  @Override
  public List<Integer> baseFun() {
    return this.listToSort;
  }

  @Override
  public List<? extends DivideAndConquerable<List<Integer>>> decompose() 
    {
    Collections.swap(this.listToSort, meadianOfThree(this.left,this.right), this.right);
    int mid = partition ();
    List<QuickSort> ret = new ArrayList<QuickSort>();
    ret.add(new QuickSort(this.listToSort,mid+1,this.right));
    ret.add(new QuickSort(this.listToSort,this.left,mid-1));
    return ret;
  }

  @Override
  public List<Integer> recombine(List<List<Integer>> intermediateResults) {
    return intermediateResults.get(1);
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
