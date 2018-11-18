package BinarySearch;

public class BinarySearchSquareRoot {
  private double numberToSquare;
  private double left;
  private double right;
  private static double tolerance = 0.0000001;
  private static int iterationCount;
  
  public BinarySearchSquareRoot (double numbertoSquare,double left,double right) throws  IllegalArgumentException{
    if (Double.compare(numbertoSquare, 1)<=0)
      throw new IllegalArgumentException("The input has to be bigger or equal to 1");
    this.numberToSquare = numbertoSquare;
    this.left = left;
    this.right = right;
  }
  //Math.abs(c-1.0) <= 0.000001
  
  public double BinarySearch()throws  IllegalArgumentException {
    countUp();
    double mid = (this.right+this.left)/2;
    double midSquared = mid*mid;
    if (Math.abs(this.numberToSquare-midSquared) <=this.tolerance) {
      return mid;
    } else if(Double.compare(this.numberToSquare, midSquared)>0) {
      return new BinarySearchSquareRoot(this.numberToSquare,mid,this.right).BinarySearch();
    }
    return new BinarySearchSquareRoot(this.numberToSquare,this.left,mid).BinarySearch();
  }
  
  private void countUp() {
    this.iterationCount++;
  }
  
  public int getCounter() {
    return this.iterationCount;
  }
  
  public void resetCounter () {
    this.iterationCount = 0;
  }

}
