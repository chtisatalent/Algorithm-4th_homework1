import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private int numofCub;
  private int topPoint;
  private boolean[] fl;
  private int numofOpen;
  private WeightedQuickUnionUF acube;

  private int xyTo1D(int row, int col) {
    return (numofCub * (row - 1) + col - 1);
  }
  
  public Percolation(int n) {
    if (n <= 0) {
      throw new java.lang.IllegalArgumentException("n > 0");
    } else {
      numofCub = n;
      topPoint = numofCub * numofCub;
      acube = new WeightedQuickUnionUF(numofCub * numofCub + 1 + numofCub);
      fl = new boolean[numofCub * numofCub];
      for (int i = 0; i < numofCub ; i++) {
        fl[i] = false;
      }
      numofOpen = 0;
    }
  }

  public void open(int row, int col) {
    if (row <= 0 || col <= 0 || row > numofCub || col > numofCub) {
      throw new java.lang.IndexOutOfBoundsException("row or col index i out of bounds");
    } else {
      int location = xyTo1D(row,col);  
      if (!isOpen(row,col)) {
        fl[location] = true;
        numofOpen += 1;
      }
      
      if(numofCub == 1) {
        acube.union(location,topPoint);
        acube.union(location,location + 1 + numofCub);
      } else {     
        if (row == 1) {
          acube.union(location, topPoint);
        }
        if (row < numofCub) {
          if (isOpen(row + 1,col)) {
            acube.union(location,location + numofCub);
          } 
        }
        if (row == numofCub) {
          acube.union(location,location + 1 + numofCub);
        }
        if (row > 1 && row <= numofCub) {
          if (isOpen(row - 1,col)) {
            acube.union(location,location - numofCub);
          }
        }
  
        if (col < numofCub && isOpen(row,col + 1)) {
          acube.union(location,location + 1);
        }
  
        if (col > 1 && col <= numofCub && isOpen(row,col - 1)) {
          acube.union(location,location - 1);
        }
      }
    }
  }

  public boolean  isOpen(int row, int col) {
    if (row <= 0 || col <= 0 || row > numofCub || col > numofCub) {
      throw new java.lang.IndexOutOfBoundsException("row or col index i out of bounds");
    } else {
      int location = xyTo1D(row,col);
      return fl[location];
    }
  }
  
  public boolean isFull(int row, int col) {
    if (row <= 0 || col <= 0 || row > numofCub || col > numofCub) {
      throw new java.lang.IndexOutOfBoundsException("row or col index i out of bounds");
    } else {
      int location = xyTo1D(row, col);
      if (acube.connected(location,topPoint)) {
        return true;
      } else {
        return false;
      }
    }
  }
  
  public int numberOfOpenSites() {
    return numofOpen;
  }

  public boolean percolates() {
    for (int i = 1; i <= numofCub; i++)
    {
      if (acube.connected(topPoint,topPoint + i))
      {
        return true;
      }
    }
    return false;
  }
}
