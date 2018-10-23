package DivideAndConquer;

import java.util.ArrayList;
import java.util.List;

public class FibonacciStandart implements DivideAndConquerable<Integer> {

  private Integer fibbNum;

  public FibonacciStandart(Integer fibbNum) {
    this.fibbNum = fibbNum;
  }

  public Integer getFibbNum() {
    return this.fibbNum;
  }

  public void setFibbNum(Integer fibbNum) {
    this.fibbNum = fibbNum;
  }

  @Override
  public boolean isBasic() {
    return (this.fibbNum == 1 || this.fibbNum == 2);
  }

  @Override
  public Integer baseFun() {
    return 1;
  }

  @Override
  public List decompose() {
    List<FibonacciStandart> subComponents = new ArrayList<>();
    subComponents.add(new FibonacciStandart(this.fibbNum - 1));
    subComponents.add(new FibonacciStandart(this.fibbNum - 2));
    return subComponents;
  }

  @Override
  public Integer recombine(List intermediateResults) {
    if (intermediateResults == null)
      return null;

    return (Integer) intermediateResults.get(0) + (Integer) intermediateResults.get(1);
  }

}
