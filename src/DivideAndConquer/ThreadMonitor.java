package DivideAndConquer;


//monitors how many threads are started
public class ThreadMonitor {
  private int number;

  public ThreadMonitor() {
    this.number = 0;
  }

  public void setInt() {
    this.number++;
  }

  public int getNumber() {
    return this.number;
  }

}
