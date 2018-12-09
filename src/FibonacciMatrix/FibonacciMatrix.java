package FibonacciMatrix;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FibonacciMatrix {
  private List <List<BigInteger>> fM;
  private List <List<BigInteger>> fS;
  private int fiboNumber;
  
  public FibonacciMatrix (int n) {
    fM = new ArrayList<List<BigInteger>>();
    fS = new ArrayList<List<BigInteger>>();
    this.fM.add(Arrays.asList(BigInteger.ZERO,BigInteger.ONE));
    this.fM.add(Arrays.asList(BigInteger.ONE,BigInteger.ONE));
    this.fS.add(Arrays.asList(BigInteger.ZERO,BigInteger.ONE));
    this.fS.add(Arrays.asList(BigInteger.ONE,BigInteger.ONE));
    this.fiboNumber = n;
    computeMatrixProduct();
  }
  
  private void computeMatrixProduct() {
    for(int i = 0 ;i<this.fiboNumber-2;i++) {
      BigInteger zeroZero = fS.get(0).get(0).multiply(fM.get(0).get(0)).add(fS.get(0).get(1).multiply(fM.get(1).get(0)));
      BigInteger zeroOne = fS.get(0).get(0).multiply(fM.get(0).get(1)).add(fS.get(0).get(1).multiply(fM.get(1).get(1)));
      BigInteger oneZero = fS.get(1).get(0).multiply(fM.get(0).get(0)).add( fS.get(1).get(1).multiply(fM.get(1).get(0)));
      BigInteger oneOne = fS.get(1).get(0).multiply(fM.get(0).get(1)).add(fS.get(1).get(1).multiply(fM.get(1).get(1)));
        fS.get(0).set(0,zeroZero);
        fS.get(0).set(1, zeroOne);
        fS.get(1).set(0, oneZero);
        fS.get(1).set(1, oneOne);
    }
  }
  
  public BigInteger fibreturn() {
    if (this.fiboNumber== 0) {
      return fS.get(0).get(0);
    } else if(this.fiboNumber== 0) {
      return fS.get(0).get(1);
    }
    return fS.get(1).get(1);
  }
  

}
