package DivideAndConquerMergeSort;


import java.util.List;
import java.util.ArrayList;

public interface DivideAndConquerable<OutputType> {
  boolean isBasic();

  OutputType baseFun();

  List<? extends DivideAndConquerable<OutputType>> decompose();

  OutputType recombine(List<OutputType> intermediateResults);

  default OutputType divideAndConquer() {
    if (this.isBasic())
      return this.baseFun();

    List<? extends DivideAndConquerable<OutputType>> subcomponents = this.decompose();

    List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());

    subcomponents.forEach(subcomponent -> intermediateResults.add(subcomponent.divideAndConquer()));
    return recombine(intermediateResults);

  }


  default List<? extends DivideAndConquerable<OutputType>> stump() {
    return new ArrayList<DivideAndConquerable<OutputType>>(0);
  }
}
