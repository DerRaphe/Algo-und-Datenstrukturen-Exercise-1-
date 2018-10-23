package DivideAndConquer;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.XYChart;

public class TestMergeSort {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ListFactory listFactory = new ListFactory();
    
    for ( int i = 10;i<=100;i += 10) {
      //List which has to be sorted
      List<Integer> testList = listFactory.giveListToSort(i);
      System.out.println("List that has to be sorted: " + testList);
      
      //Merge sort with basic Insertion sort
      MergeSortBasicInsertion mSi = new MergeSortBasicInsertion(testList,5);
      System.out.println("Sorted List: " + mSi.divideAndConquer());
      ExecutionTimer<List<Integer>> timerStandard = new ExecutionTimer<List<Integer>>(() -> {
        return mSi.divideAndConquer();
      });
      System.out.println("Merge sort with Insertion Sort as basic function: " );
      System.out.println("Execution Time: " + timerStandard.time);
      
      
      //Merge Sort with Insertion Sort as Merge function
      MergeSortMergeInsertion mSmI = new MergeSortMergeInsertion(testList); 
      timerStandard = new ExecutionTimer<List<Integer>>(() -> {
        return mSmI.divideAndConquer();
      });
      System.out.println("Merge Sort with Insertion Sort as Merge function: " );
      System.out.println("Execution Time: " + timerStandard.time);
    }
    
    
    
  //ITERATIV FIBONACCI 
    /*FibonacciStandart dnc = new FibonacciStandart(fibNumber);
    System.out.println("Standard Fibonacci Result: " + dnc.divideAndConquer());
    
    ExecutionTimer<Integer> timerStandard = new ExecutionTimer<Integer>(() -> {
      return dnc.divideAndConquer();
    });
    
    System.out.println(meineListe1);
    MergeSortBasicInsertion test = new MergeSortBasicInsertion(meineListe1,5);
    MergeSortMergeInsertion test2 = new MergeSortMergeInsertion(meineListe1);
    System.out.println(test.divideAndConquer());
    System.out.println(test2.divideAndConquer());*/
  }

}
