package DivideAndConquer;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;


public class LineChartDnC extends Application {
  private int insertionSortBorder = 20;
  @Override
	public void start(Stage primaryStage) {
	  primaryStage.setTitle("Line Chart Sample");
      //defining the axes
      final NumberAxis xAxis = new NumberAxis();
      final NumberAxis yAxis = new NumberAxis();
      xAxis.setLabel("Length of list");
      yAxis.setLabel("ms");
      //creating the chart
      final LineChart<Number,Number> lineChart = 
              new LineChart<Number,Number>(xAxis,yAxis);
             
      lineChart.setTitle("Execution time comparison of sort algorithm");
      XYChart.Series seriesmSi = new XYChart.Series();
      XYChart.Series seriesmSmI = new XYChart.Series();
      
      setChartValues(seriesmSi,seriesmSmI);
      
      //defining a series
      seriesmSi.setName("Merge sort basic Insertion (iterative)");
      seriesmSmI.setName("Merge sort merge Insertion (iterative)");
      //populating the series with data
            
      Scene scene  = new Scene(lineChart,800,600);
      lineChart.getData().add(seriesmSi);
      lineChart.getData().add(seriesmSmI);
     
      primaryStage.setScene(scene);
      primaryStage.show();
	}

	public static void main(String[] args) {
	  
		launch(args);
	}
	
	public void setChartValues(Series seriesmSi,Series seriesmSmI) {
	  ListFactory listFactory = new ListFactory();
      for ( int i = 10;i<=300;i += 10) {
        //List which has to be sorted
        List<Integer> testList = listFactory.giveListToSort(i);
        System.out.println("List that has to be sorted: " + testList);
        
        //Merge sort with basic Insertion sort
        MergeSortBasicInsertion mSi = new MergeSortBasicInsertion(testList,insertionSortBorder);
        System.out.println("Sorted List: " + mSi.divideAndConquer());
        ExecutionTimer<List<Integer>> timerStandard = new ExecutionTimer<List<Integer>>(() -> {
          return mSi.divideAndConquer();
        });
        System.out.println("Merge sort with Insertion Sort as basic function: " );
        seriesmSi.getData().add(new XYChart.Data(i, timerStandard.time));
        System.out.println("Execution Time: " + timerStandard.time);
        
        
        //Merge Sort with Insertion Sort as Merge function
        MergeSortMergeInsertion mSmI = new MergeSortMergeInsertion(testList); 
        timerStandard = new ExecutionTimer<List<Integer>>(() -> {
          return mSmI.divideAndConquer();
        });
        System.out.println("Merge Sort with Insertion Sort as Merge function: " );
        seriesmSmI.getData().add(new XYChart.Data(i, timerStandard.time));
        System.out.println("Execution Time: " + timerStandard.time);
      }
	}
}
