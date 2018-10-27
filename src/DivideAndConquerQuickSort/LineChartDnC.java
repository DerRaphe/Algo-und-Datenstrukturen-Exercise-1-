package DivideAndConquerQuickSort;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LineChartDnC extends Application {
  //
  private int insertionSortBorder = 5;
  private int baseValueForMultiThreading = 4;
  private int countOfListItems = 100;
  private int countOfThreads = 4;
  Scene scene1, scene2;
  Pane gr;
  HBox pictureRegion1;
  LineChart<Number, Number> lineChart;
  XYChart.Series seriesmSi,seriesmSmI,seriesmSbIPa, seriesmSmIRa,seriesmSmIPa;
  
  
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Line Chart Sample");
    // defining the axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Length of list");
    yAxis.setLabel("ms");

    Label countOfThread = new Label("Thread count: ");
    countOfThread.setLayoutY(5);
    Label muliThreadingBase = new Label("is too small for multithreading: ");
    muliThreadingBase.setLayoutY(35);
    Label borderInsertionSort = new Label("transition point to insertion sort: ");
    borderInsertionSort.setLayoutY(65);
    Label listItemCount = new Label("max list size (10 based): ");
    listItemCount.setLayoutY(95);

    TextField txtCountOfThread = new TextField();
    txtCountOfThread.setLayoutY(5);
    txtCountOfThread.setLayoutX(200);
    TextField txtMuliThreadingBase = new TextField();
    txtMuliThreadingBase.setLayoutY(35);
    txtMuliThreadingBase.setLayoutX(200);
    TextField txtListItemCount = new TextField();
    txtListItemCount.setLayoutY(95);
    txtListItemCount.setLayoutX(200);
    TextField txtBorderInsertionSort = new TextField();
    txtBorderInsertionSort.setLayoutY(65);
    txtBorderInsertionSort.setLayoutX(200);
    
    Button btnSetValues = new Button("show graphic");
    btnSetValues.setLayoutX(260);
    btnSetValues.setLayoutY(170);

    // creating the chart
     lineChart = new LineChart<Number, Number>(xAxis, yAxis);

    lineChart.setTitle("Execution time comparison of sort algorithm");
    lineChart.setPrefWidth(1000);
    seriesmSi = new XYChart.Series();
    seriesmSmI = new XYChart.Series();
    seriesmSbIPa = new XYChart.Series();
    seriesmSmIRa = new XYChart.Series();
    seriesmSmIPa = new XYChart.Series();
    
    txtBorderInsertionSort.setText(String.valueOf(insertionSortBorder));
    txtMuliThreadingBase.setText(String.valueOf(baseValueForMultiThreading));
    txtListItemCount.setText(String.valueOf(countOfListItems));
    txtCountOfThread.setText(String.valueOf(countOfThreads));
    
    btnSetValues.setOnAction((event) -> {
      insertionSortBorder = Integer.parseInt(txtBorderInsertionSort.getText());
      baseValueForMultiThreading = Integer.parseInt(txtMuliThreadingBase.getText());
      countOfListItems = Integer.parseInt(txtListItemCount.getText());
      countOfThreads = Integer.parseInt(txtCountOfThread.getText());
      
      // creating the chart
      lineChart = new LineChart<Number, Number>(xAxis, yAxis);
      
      seriesmSi = new XYChart.Series();
      seriesmSmI = new XYChart.Series();
      seriesmSbIPa = new XYChart.Series();
      seriesmSmIRa = new XYChart.Series();
      seriesmSmIPa = new XYChart.Series();
      
      
      setChartValues(seriesmSi, seriesmSmI, seriesmSbIPa, seriesmSmIRa, seriesmSmIPa);
      // defining a series
      seriesmSi.setName("Mergesort, Insertionsort as base function (iterative, sort both lists-> merge)");
      seriesmSmI.setName("Mergesort, Insertionsort as merge function (iterative)");
      seriesmSbIPa.setName("Mergesort, Insertionsort as base function (parallel)");
      seriesmSmIRa.setName("Mergesort, Insertionsort as base function (iterative, merge -> sort whole list)");
      seriesmSmIPa.setName("Mergesort, Insertionsort as merge function (Parallel)");

      
      lineChart.getData().add(seriesmSi);
      lineChart.getData().add(seriesmSmI);
      lineChart.getData().add(seriesmSbIPa);
      lineChart.getData().add(seriesmSmIRa);
      lineChart.getData().add(seriesmSmIPa);
      HBox pictureRegion1 = new HBox();
      
      lineChart.setPrefWidth(1000);
      
      pictureRegion1.getChildren().add(lineChart);
      pictureRegion1.getChildren().add(gr);
      scene1 = new Scene(pictureRegion1, 1400, 500);
      primaryStage.setScene(scene1);
      primaryStage.show();
    });

    setChartValues(seriesmSi, seriesmSmI, seriesmSbIPa, seriesmSmIRa, seriesmSmIPa);

    // defining a series
    seriesmSi.setName("Mergesort, Insertionsort as base function (iterative, sort both lists-> merge)");
    seriesmSmI.setName("Mergesort, Insertionsort as merge function (iterative)");
    seriesmSbIPa.setName("Mergesort, Insertionsort as base function (parallel)");
    seriesmSmIRa.setName("Mergesort, Insertionsort as base function (iterative, merge -> sort whole list)");
    seriesmSmIPa.setName("Mergesort, Insertionsort as merge function (Parallel)");
    
    lineChart.getData().add(seriesmSi);
    lineChart.getData().add(seriesmSmI);
    lineChart.getData().add(seriesmSbIPa);
    lineChart.getData().add(seriesmSmIRa);
    lineChart.getData().add(seriesmSmIPa);

     gr = new Pane(countOfThread, muliThreadingBase, listItemCount,borderInsertionSort,
        txtCountOfThread ,txtMuliThreadingBase , txtListItemCount,txtBorderInsertionSort, btnSetValues);
     gr.setPrefWidth(10); 
     
     HBox pictureRegion1 = new HBox();
     pictureRegion1.getChildren().add(lineChart);
     pictureRegion1.getChildren().add(gr);
    scene1 = new Scene(pictureRegion1, 1400, 500);
    
    primaryStage.setScene(scene1);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void setChartValues(Series seriesmSi, Series seriesmSmI, Series seriesmSbIPa,
    Series seriesmSmIRa, Series seriesmSmIPa) {
    ListFactory listFactory = new ListFactory(countOfListItems);
    int counter = countOfListItems/10;
    for (int i = counter; i <= countOfListItems; i += counter) {
      // List which has to be sorted
      List<Integer> testList = listFactory.giveListToSort(i);

      // Merge sort with basic Insertion sort
      QuickSortInsertion mSi = new QuickSortInsertion(testList);
      ExecutionTimer<List<Integer>> timerStandard = new ExecutionTimer<List<Integer>>(() -> {
        return mSi.divideAndConquer();
      });
      seriesmSi.getData().add(new XYChart.Data(i, timerStandard.time));


      // Merge Sort with Insertion Sort as Merge function
      MergeSortMergeInsertion mSmI = new MergeSortMergeInsertion(testList);
      timerStandard = new ExecutionTimer<List<Integer>>(() -> {
        return mSmI.divideAndConquer();
      });
      seriesmSmI.getData().add(new XYChart.Data(i, timerStandard.time));

      MergeSortBasicInsertionParallel mSbIPa = new MergeSortBasicInsertionParallel(testList,
          insertionSortBorder, baseValueForMultiThreading, countOfThreads);
      timerStandard = new ExecutionTimer<List<Integer>>(() -> {
        return mSbIPa.divideAndConquer();
      });
      seriesmSbIPa.getData().add(new XYChart.Data(i, timerStandard.time));

      MergeSortBasicInsertionParallel mSmIRa = new MergeSortBasicInsertionParallel(testList,
          insertionSortBorder, baseValueForMultiThreading, countOfThreads);
      timerStandard = new ExecutionTimer<List<Integer>>(() -> {
        return mSmIRa.divideAndConquer();
      });
      seriesmSmIRa.getData().add(new XYChart.Data(i, timerStandard.time));

      MergeSortMergeInsertionParallel mSmIPa = new MergeSortMergeInsertionParallel(testList, baseValueForMultiThreading, countOfThreads);
      timerStandard = new ExecutionTimer<List<Integer>>(() -> {
        return mSmIPa.divideAndConquer();
      });
      seriesmSmIPa.getData().add(new XYChart.Data(i, timerStandard.time));

    }
  }
}
