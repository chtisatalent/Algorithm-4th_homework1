import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private int numofTrails;
  private double number;
  private double[] threshold;
  private Percolation per;
  
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new java.lang.IllegalArgumentException("n > 0");
    } else {
      numofTrails = trials;
      number = n;
      threshold = new double[numofTrails];
  
      for (int i = 0;i < numofTrails;i++) {
        per = new Percolation(n);
        while (!per.percolates()) {
          int randomRow = StdRandom.uniform(1,n + 1);
          int randomCol = StdRandom.uniform(1,n + 1);
          per.open(randomRow,randomCol);
        }
        threshold[i] = ((double)(per.numberOfOpenSites())) / (number * number);
      }
    }
  }
   
  public double mean() {
    return StdStats.mean(threshold);
  }                 

  public double stddev() {
    return StdStats.stddev(threshold);
  }
   
  public double confidenceLo() {
    return (this.mean() - (1.96 * this.stddev() / Math.sqrt(numofTrails)));
  }
   
  public double confidenceHi() {
    return (this.mean() + (1.96 * this.stddev() / Math.sqrt(numofTrails)));
  }
   
  public static void main(String[] args) {  
    int n1 = Integer.parseInt(args[0]);
    int n2 = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n1,n2);
    StdOut.printf("mean = %f\n",stats.mean());
    StdOut.printf("stddev = %f\n",stats.stddev());
    StdOut.printf("95%% confidence interval = [%1.6f , %1.6f]\n",
        stats.confidenceLo(),stats.confidenceHi());
  }  
}