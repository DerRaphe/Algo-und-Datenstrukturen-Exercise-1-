package FibonacciMatrix;

public class testFib {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    for (int i=0; i<40;i++) { 
    FibonacciMatrix test1 = new FibonacciMatrix(i);
    System.out.println(test1.fibreturn());
    }
  }

}
