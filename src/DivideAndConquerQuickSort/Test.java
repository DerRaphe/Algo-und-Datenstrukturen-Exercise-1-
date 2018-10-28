package DivideAndConquerQuickSort;

import java.util.List;

public class Test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ListFactory listFactory = new ListFactory(12);
    ListFactory listFactory1 = new ListFactory(12);
    List<Integer> testListe = listFactory.giveListToSort(12);
    List<Integer> testListe1 = listFactory1.giveListToSort(5);
    QuickSortParallel sorter = new QuickSortParallel(testListe,5,4, 0,testListe.size()-1);
    //QuickSort sorter1 = new QuickSort(testListe1, 0,testListe1.size()-1);
    //System.out.println(testListe);
    //System.out.println("Index: " + sorter.meadianOfThree(5, testListe.size()-1));
    //System.out.println(testListe1);
    //System.out.println("Index: " + sorter1.meadianOfThree(5, testListe1.size()-1));
    System.out.println("List to sort: " + testListe);
    //QuickSortStandart sorter = new QuickSortStandart(testListe);
    //QuickSortParallel sorter1 = new QuickSortParallel(testListe,5,4);
    System.out.println("Sorted: " + sorter.divideAndConquer());
    //System.out.println("Sorted: " + sorter1.divideAndConquer());
  }

}
