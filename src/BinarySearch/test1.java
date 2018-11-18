package BinarySearch;

public class test1 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    BinarySearchSquareRoot test = new BinarySearchSquareRoot(12,1,12);
    System.out.println(test.BinarySearch());
    System.out.println(test.getCounter());
    BinarySearchLog testLog =  new BinarySearchLog(4,1,4);
    System.out.println(testLog.BinarySearch());
    System.out.println(testLog.getCounter());
  }

}
