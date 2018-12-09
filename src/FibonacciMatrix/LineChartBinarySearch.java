package FibonacciMatrix;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LineChartBinarySearch extends Application {
  LineChart<Number, Number> lineChartSquare;
  LineChart<Number, Number> lineChartLog;
  XYChart.Series seriesLog,seriesSquare;
  Scene scene1, scene2;
  Pane gr;
  Pane pictureRegion1;
  
  


  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Line Chart Binarysearch");
    final NumberAxis xAxisSq = new NumberAxis();
    final NumberAxis yAxisSq = new NumberAxis();
    xAxisSq.setLabel("Number to square");
    yAxisSq.setLabel("iteration count");
    final NumberAxis xAxisLg = new NumberAxis();
    final NumberAxis yAxisLg = new NumberAxis();
    xAxisLg.setLabel("Number to log");
    yAxisLg.setLabel("iteration count");
    
    
    //Linechart settings
    lineChartSquare = new LineChart<Number, Number>(xAxisSq, yAxisSq);
    lineChartSquare.setPrefWidth(850);
    lineChartSquare.setPrefHeight(400);
    lineChartSquare.setLayoutY(50);
    lineChartSquare.setTitle("count of iteration for square");

    
 // defining a series
    seriesSquare = new XYChart.Series();
    seriesLog = new XYChart.Series();
    seriesSquare.setName("number of iterations for sqaure a number");

    lineChartSquare.getData().add(seriesSquare);
    pictureRegion1 = new Pane();
    
    gr = new Pane();
    gr.setPrefWidth(10); 
     
    
    pictureRegion1.getChildren().add(lineChartSquare);
    pictureRegion1.getChildren().add(gr);
    
    scene1 = new Scene(pictureRegion1, 900, 450);
    primaryStage.setScene(scene1);
    primaryStage.show();
    
  }
  public static void main(String[] args) {
    launch(args);
  }
  /*private void SetChartValues(Series seriesSquare, Series seriesLog) {
    int counterSquare = numberToSquare;
 //   if(counterSquare <= 1) {counterSquare = counterSquare + 1;};
    for (int i = 2; i <= numberToSquare; i++) {
      //Square
      BinarySearchSquareRoot squaredNr = new BinarySearchSquareRoot(i,1,i, squareTolerance);
      System.out.println(squaredNr.BinarySearch());
      System.out.println(squaredNr.getCounter());
      seriesSquare.getData().add(new XYChart.Data(i, squaredNr.getCounter()));   
      squaredNr.resetCounter();
    }
    
    int counterLog = numberToLog;
    // if(counterLog <= 1) {counterLog = counterLog + 1;};
     for (int i = 2; i <= numberToLog; i ++) {      
       //Log
       BinarySearchLog logedNr =  new BinarySearchLog(i,1,i, logTolerance);
       System.out.println(logedNr.BinarySearch());
       System.out.println(logedNr.getCounter());
       seriesLog.getData().add(new XYChart.Data(i, logedNr.getCounter()));
       logedNr.resetCounter();
       
     }
     
  }*/
}
