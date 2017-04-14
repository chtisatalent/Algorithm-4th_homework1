import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int N;
	private double number;
	private double threshold[];
	private Percolation per;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
	   N = trials;
	   number = n;
	   threshold = new double[N];

	   for(int i = 0; i<N ; i++)
	   {
	       per = new Percolation(n);
	       while(!per.percolates())
	       {
	    	   int random_row = StdRandom.uniform(1,n+1);
	    	   int random_col = StdRandom.uniform(1,n+1);
	    	   per.open(random_row,random_col);
	       }
	       threshold[i] = ((double)(per.numberOfOpenSites()))/(number * number);
	   }	   
   }
   
   public double mean()        // sample mean of percolation threshold 
   {
	  return StdStats.mean(threshold);
   }                 

   public double stddev()                        // sample standard deviation of percolation threshold
   {
	  return StdStats.stddev(threshold);
   }
   
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
	   return (this.mean() - 1.96*Math.sqrt(this.stddev())/Math.sqrt(N));
   }
   
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
	   return (this.mean() + 1.96*Math.sqrt(this.stddev())/Math.sqrt(N));
   }
   
   public static void main(String[] args)   // test client (described below) 
   {  
	   int n1 = Integer.parseInt(args[0]);
       int n2 = Integer.parseInt(args[1]);
	   PercolationStats stats = new PercolationStats(n1,n2);
	   StdOut.printf("mean = %f\n",stats.mean());
	   StdOut.printf("stddev = %f\n",stats.stddev());
	   StdOut.printf("95%% confidence interval = [%1.6f , %1.6f]\n",stats.confidenceLo(),stats.confidenceHi());
   }  
}