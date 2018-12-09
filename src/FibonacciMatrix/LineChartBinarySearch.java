package FibonacciMatrix;


import java.util.List;
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
  XYChart.Series fibonacciSerie,logarithmicSerie,linearSerie,quadraticSerie,cubicSerie;
  Scene scene1, scene2;
  Pane gr;
  Pane pictureRegion1;
  
  


  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Line Chart Binarysearch");
    final NumberAxis xAxisSq = new NumberAxis();
    final NumberAxis yAxisSq = new NumberAxis();
    xAxisSq.setLabel("fibonacci of n");
    yAxisSq.setLabel("Time in ms");

    
    
    //Linechart settings
    lineChartSquare = new LineChart<Number, Number>(xAxisSq, yAxisSq);
    lineChartSquare.setPrefWidth(850);
    lineChartSquare.setPrefHeight(400);
    lineChartSquare.setLayoutY(50);
    lineChartSquare.setTitle("Time complexity of Fibonacci Matrix");

    
 // defining a series
    fibonacciSerie = new XYChart.Series();
    logarithmicSerie = new XYChart.Series();
    linearSerie = new XYChart.Series();
    quadraticSerie = new XYChart.Series();
    cubicSerie = new XYChart.Series();
    
    fibonacciSerie.setName("Fibonacci Matrix");
    logarithmicSerie.setName("ln");
    linearSerie.setName("Linear");
    quadraticSerie.setName("Quadratic");
    cubicSerie.setName("Cubic");
    
    addData();
    lineChartSquare.getData().add(fibonacciSerie);
    lineChartSquare.getData().add(logarithmicSerie);
    lineChartSquare.getData().add(linearSerie);
    lineChartSquare.getData().add(quadraticSerie);
    lineChartSquare.getData().add(cubicSerie);
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
  
  private void addData() {
    for (int i = 0 ; i<200;i += 5) {
      FibonacciMatrix fiboComputer = new FibonacciMatrix(i);
      ExecutionTimer<Integer> timerStandard = new ExecutionTimer<Integer>(() -> {
        return fiboComputer.fibreturn();
      });
      if (i>1) {
        fibonacciSerie.getData().add(new XYChart.Data(i, timerStandard.time));
        logarithmicSerie.getData().add(new XYChart.Data(i, 50*Math.log(i)));
        linearSerie.getData().add(new XYChart.Data(i, 5*i));
        quadraticSerie.getData().add(new XYChart.Data(i, 0.2*i*i));
        cubicSerie.getData().add(new XYChart.Data(i, 0.0005*i*i*i));
      }
    }
    
  }
  
}
