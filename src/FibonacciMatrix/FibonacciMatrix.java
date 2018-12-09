package FibonacciMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FibonacciMatrix {
  private List <List<Integer>> fM;
  private List <List<Integer>> fS;
  private int fiboNumber;
  
  public FibonacciMatrix (int n) {
    fM = new ArrayList<List<Integer>>();
    fS = new ArrayList<List<Integer>>();
    this.fM.add(Arrays.asList(0,1));
    this.fM.add(Arrays.asList(1,1));
    this.fS.add(Arrays.asList(0,1));
    this.fS.add(Arrays.asList(1,1));
    this.fiboNumber = n;
    computeMatrixProduct();
  }
  
  private void computeMatrixProduct() {
    for(int i = 0 ;i<this.fiboNumber-2;i++) {
        int zeroZero = fS.get(0).get(0)*fM.get(0).get(0) + fS.get(0).get(1)*fM.get(1).get(0);
        int zeroOne = fS.get(0).get(0)*fM.get(0).get(1) + fS.get(0).get(1)*fM.get(1).get(1);
        int oneZero = fS.get(1).get(0)*fM.get(0).get(0) + fS.get(1).get(1)*fM.get(1).get(0);
        int oneOne = fS.get(1).get(0)*fM.get(0).get(1) + fS.get(1).get(1)*fM.get(1).get(1);
        fS.get(0).set(0,zeroZero);
        fS.get(0).set(1, zeroOne);
        fS.get(1).set(0, oneZero);
        fS.get(1).set(1, oneOne);
    }
  }
  
  public int fibreturn() {
    if (this.fiboNumber== 0) {
      return fS.get(0).get(0);
    } else if(this.fiboNumber== 0) {
      return fS.get(0).get(1);
    }
    return fS.get(1).get(1);
  }
  

}
