package BinarySearch;

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
  
  
  private int numberToSquare = 17;
  private int numberToLog = 10;
  private double squareTolerance = 0.0000001;
  private double logTolerance = 0.0000001;

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
    
    //labels
    Label lblSquareNumber = new Label("Number to square: ");
    lblSquareNumber.setLayoutY(5);
    Label lblSqareTolerance = new Label("tolerance: ");
    lblSqareTolerance.setLayoutY(5);
    lblSqareTolerance.setLayoutX(300);
    Label lblLogNumber = new Label("number to log: ");
    lblLogNumber.setLayoutY(450);
    lblLogNumber.setLayoutX(5);
    Label lblLogTolerance = new Label("tolerance: ");
    lblLogTolerance.setLayoutY(450);
    lblLogTolerance.setLayoutX(300);

    //textfields
    TextField txtSquareNumber = new TextField();
    txtSquareNumber.setLayoutY(5);
    txtSquareNumber.setLayoutX(100);
    TextField txtSqareTolerance = new TextField();
    txtSqareTolerance.setLayoutY(5);
    txtSqareTolerance.setLayoutX(380);
    TextField txtLogNumber = new TextField();
    txtLogNumber.setLayoutY(450);
    txtLogNumber.setLayoutX(100);
    TextField txtLogTolerance = new TextField();
    txtLogTolerance.setLayoutY(450);
    txtLogTolerance.setLayoutX(380);
    
    
    //set defaults 
    txtSquareNumber.setText(String.valueOf(numberToSquare));
    txtSqareTolerance.setText(String.valueOf(squareTolerance));
    txtLogNumber.setText(String.valueOf(numberToLog));
    txtLogTolerance.setText(String.valueOf(logTolerance));
    
    //Buttons
    Button btnSetSquareValues = new Button("show square graphic");
    btnSetSquareValues.setLayoutX(550);
    btnSetSquareValues.setLayoutY(5);
    Button btnSetLogValues = new Button("show log graphic");
    btnSetLogValues.setLayoutX(550);
    btnSetLogValues.setLayoutY(450);

    
    //Button Square
    btnSetSquareValues.setOnAction((event) -> {
      numberToSquare = Integer.parseInt(txtSquareNumber.getText());
      squareTolerance = Double.parseDouble(txtSqareTolerance.getText());
      
      // creating the chart
      lineChartSquare = new LineChart<Number, Number>(xAxisSq, yAxisSq);
      
      seriesSquare = new XYChart.Series();
      seriesLog = new XYChart.Series();

      seriesSquare.setName("number of iterations for sqaure a number");
      seriesLog.setName("number of iterations for calculate log of a number");
      
      SetChartValues(seriesSquare, seriesLog);
      
      //Linechart settings
      lineChartSquare = new LineChart<Number, Number>(xAxisSq, yAxisSq);
      lineChartSquare.setPrefWidth(850);
      lineChartSquare.setPrefHeight(400);
      lineChartSquare.setLayoutY(50);
      lineChartSquare.setTitle("count of iteration for square");

      lineChartLog = new LineChart<Number, Number>(xAxisLg, yAxisLg);
      lineChartLog.setTitle("count of iteration for log (base 2)");
      lineChartLog.setPrefWidth(850);
      lineChartLog.setPrefHeight(400);
      lineChartLog.setLayoutY(500);
      

      lineChartSquare.getData().add(seriesSquare);
      lineChartLog.getData().add(seriesLog);
      Pane pictureRegion1 = new Pane();
      
      pictureRegion1.getChildren().add(lineChartSquare);
      pictureRegion1.getChildren().add(lineChartLog);
      pictureRegion1.getChildren().add(gr);
      
      scene1 = new Scene(pictureRegion1, 900, 900);
      primaryStage.setScene(scene1);
      primaryStage.show();
      
      
    });
    
    //Button LOG
    btnSetLogValues.setOnAction((event) -> {
      numberToLog = Integer.parseInt(txtLogNumber.getText());
      logTolerance = Double.parseDouble(txtLogTolerance.getText());
      
      // creating the chart
      lineChartLog = new LineChart<Number, Number>(xAxisSq, yAxisSq);
      
      seriesSquare = new XYChart.Series();
      seriesLog = new XYChart.Series();
      seriesSquare.setName("number of iterations for sqaure a number");
      seriesLog.setName("number of iterations for calculate log of a number");
      SetChartValues(seriesSquare, seriesLog);
      
      //Linechart settings
      lineChartSquare = new LineChart<Number, Number>(xAxisSq, yAxisSq);
      lineChartSquare.setPrefWidth(850);
      lineChartSquare.setPrefHeight(400);
      lineChartSquare.setLayoutY(50);
      lineChartSquare.setTitle("count of iteration for square");

      lineChartLog = new LineChart<Number, Number>(xAxisLg, yAxisLg);
      lineChartLog.setTitle("count of iteration for log (base 2)");
      lineChartLog.setPrefWidth(850);
      lineChartLog.setPrefHeight(400);
      lineChartLog.setLayoutY(500);
      

      lineChartSquare.getData().add(seriesSquare);
      lineChartLog.getData().add(seriesLog);
      Pane pictureRegion1 = new Pane();
      
      pictureRegion1.getChildren().add(lineChartSquare);
      pictureRegion1.getChildren().add(lineChartLog);
      pictureRegion1.getChildren().add(gr);
      
      scene1 = new Scene(pictureRegion1, 900, 900);
      primaryStage.setScene(scene1);
      primaryStage.show();
      
      
    });
    
    
    //Linechart settings
    lineChartSquare = new LineChart<Number, Number>(xAxisSq, yAxisSq);
    lineChartSquare.setPrefWidth(850);
    lineChartSquare.setPrefHeight(400);
    lineChartSquare.setLayoutY(50);
    lineChartSquare.setTitle("count of iteration for square");

    lineChartLog = new LineChart<Number, Number>(xAxisLg, yAxisLg);
    lineChartLog.setTitle("count of iteration for log (base 2)");
    lineChartLog.setPrefWidth(850);
    lineChartLog.setPrefHeight(400);
    lineChartLog.setLayoutY(500);
    
 // defining a series
    seriesSquare = new XYChart.Series();
    seriesLog = new XYChart.Series();
    seriesSquare.setName("number of iterations for sqaure a number");
    seriesLog.setName("number of iterations for calculate log of a number");
    SetChartValues(seriesSquare, seriesLog);

    lineChartSquare.getData().add(seriesSquare);
    lineChartLog.getData().add(seriesLog);
    pictureRegion1 = new Pane();
    
    gr = new Pane(txtLogTolerance, txtLogNumber, txtSqareTolerance,txtSquareNumber,
        lblLogTolerance ,lblLogNumber , lblSqareTolerance,lblSquareNumber, btnSetSquareValues, btnSetLogValues);
    gr.setPrefWidth(10); 
     
    
    pictureRegion1.getChildren().add(lineChartSquare);
    pictureRegion1.getChildren().add(lineChartLog);
    pictureRegion1.getChildren().add(gr);
    
    scene1 = new Scene(pictureRegion1, 900, 900);
    primaryStage.setScene(scene1);
    primaryStage.show();
    
  }
  public static void main(String[] args) {
    launch(args);
  }
  private void SetChartValues(Series seriesSquare, Series seriesLog) {
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
     
  }
}
