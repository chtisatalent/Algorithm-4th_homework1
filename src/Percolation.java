
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private int N;
  private int TOP_POINT;
  private int BOTTOM_POINT;
  private boolean fl[];
  private int num_open;
  private WeightedQuickUnionUF a;

  private int xyTo1D(int row, int col)
  {
	return  (N * (row-1) + col -1);
  }

  public Percolation(int n)
  {
	N = n;
	TOP_POINT = N*N;
	BOTTOM_POINT = N*N+1;
	a = new WeightedQuickUnionUF(N*N+2);
	fl = new boolean[N*N+2];
	for(int i = 0; i<N ; i++)
		fl[i] = false;
	num_open = 0;
  }

  public void open(int row, int col)
  {
	if(row <= 0 || col <= 0) throw  new java.lang.IllegalArgumentException("row or col index i must in (1- n)");
	else if( row >  N|| row > N) throw new java.lang.IndexOutOfBoundsException("row or col index i out of bounds");
	else 
	{
		int location = xyTo1D(row,col);
		if (!isOpen(row,col)) 
		{
			fl[location] = true;
			num_open += 1;
		}
		
		if(row >= 1 && row <N)
		{
			if(isOpen(row+1,col))	a.union(location,location+N);
			if(row == 1)	a.union(location, TOP_POINT);
		}
		
		if(row > 1 && row <= N)
		{
			if(isOpen(row-1,col))	a.union(location,location-N);
			//if(row == N && a.connected(location,TOP_POINT)) 
			if(row == N ) a.union(location,BOTTOM_POINT);
		}
		
		if(col >= 1 && col < N && isOpen(row,col+1))	a.union(location,location+1);

		if(col > 1 && col <= N && isOpen(row,col-1))	a.union(location,location-1);
		
	}
  }

  public boolean  isOpen(int row, int col)
  {
		int location = xyTo1D(row,col);
		return fl[location];
  }

  public boolean isFull(int row, int col)
  {
	if(row <= 0 || col <= 0) throw  new java.lang.IllegalArgumentException("row or col index i must in (1- n)");
	else if( row > N || col > N) throw new java.lang.IndexOutOfBoundsException("row or col index i out of bounds");
	else 
	{
		int location = xyTo1D(row,col);
		if(a.find(location) == a.find(TOP_POINT))	return true;
		else return false;
	}
  }
  
  public int numberOfOpenSites()
  {
	return num_open;
  }

  public boolean percolates()
  {
	if(a.connected(TOP_POINT,BOTTOM_POINT))	return true;
	else return false;
  }


}
