import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private WeightedQuickUnionUF uf;
  private int n;
  private boolean[][] open;

  // create n-by-n grid, with all sites blocked
  public Percolation(int n) {
    if (n <= 0)
      throw new java.lang.IllegalArgumentException("n cant be <= 0");

    this.n = n;

    // array is from 0 to n - 1 and put header and footer
    uf = new WeightedQuickUnionUF(n * (n + 2));

    open = new boolean[n + 1][n + 1];
    for (int row = 0; row < n + 1; row++) {
      for (int col = 0; col < n + 1; col++)
        open[row][col] = false;
    }

    _connectHeaderFooter();
  }

  // open site (row, col) if it is not open already
  public void open(int row, int col) { 
    int[][] ne = _getNeighbours(row, col);
    for (int i = 0; i < ne.length; i++) {
      if (isOpen(ne[i][0], ne[i][1])) {
        uf.union(_getPosition(row, col), _getPosition(ne[i][0], ne[i][1]));
        return;
      }
    }

    open[row][col] = true;
  }

  // is site (row, col) open?
  public boolean isOpen(int row, int col) { return open[row][col]; }

  // is site (row, col) full?
  // full when connected to header
  public boolean isFull(int row, int col) {
    return uf.connected(0, _getPosition(row, col));
  }

  // number of open sites
  public int numberOfOpenSites() {
    int open = 0;

    for (int row = 1; row <= n; row++) {
      for (int col = 1; col <= n; col++) {
        if (isOpen(row, col)) {
          open++;
        }
      }
    }

    return open;
  }

  // does the system percolate?
  // is there a connection between last and first ?
  public boolean percolates() { return uf.connected(0, n * (n + 2) - 1); }

  // test client (optional)
  public static void main(String[] args) {
    Percolation p = new Percolation(4);
    p._test_numOfOpen();
    System.out.println("hello debugger");
  }

  private void _test_numOfOpen() { System.out.println(numberOfOpenSites()); }

  // private void _test_getNeighbours() {
  //   // test getneighbours
  //   for (int i = 1; i <= n; i++) {
  //     for (int j = 1; j <= n; j++) {
  //       System.out.println("sending: \n" + i + ", " + j);

  //       int[][] arr = _getNeighbours(i, j);

  //       System.out.println("received: ");
  //       for (int m = 0; m < arr.length; m++)
  //         System.out.print(" " + arr[m]);

  //       System.out.println("\n***********");
  //     }
  //   }
  // }

  private boolean _isValid(int row, int col) {
    return ((row > 0 && row <= n) && (col > 0 && col <= n));
  }

  private int _getPosition(int row, int col)
      throws java.lang.IndexOutOfBoundsException {
    if (!_isValid(row, col))
      throw new java.lang.IndexOutOfBoundsException();

    return (--row * n + col - 1);
  }

  // return array of neghbours row & col
  private int[][] _getNeighbours(int row, int col) {
    int[][] ne = new int[4][2];
    int j = 0;

    // change row
    for (int i = -1; i < 3; i += 2) {
      int row2 = row + i;
      if (_isValid(row2, col)) {
        ne[j][0] = row2;
        ne[j++][1] = col;
      }
    }

    // change col
    for (int i = -1; i < 3; i += 2) {
      int col2 = col + i;
      if (_isValid(row, col2)) {
        ne[j][0] = row;
        ne[j++][1] = col2;
      }
    }

    // get num of zeroes, to delete them from array later
    int num0 = 0;
    for (int i = 0; i < ne.length; i++) {
      if (ne[i][0] == 0) {
        num0++;
      }
    }

    // create new array if zeroes found
    if (num0 > 0) {
      int[][] new_ne = new int[4 - num0][2];
      j = 0;
      // copy
      for (int i = 0; i < ne.length; i++) {
        if (ne[i][0] != 0) {
          new_ne[j][0] = ne[i][0];
          new_ne[j++][1] = ne[i][1];
        }
      }

      // update ne
      ne = new_ne;
    }

    return ne;
  }

  // connect elements of header and elements of footer
  private void _connectHeaderFooter() {
    int size = n * (n + 2);

    // header
    int first = 0;
    for (int i = 1; i < n; i++)
      uf.union(first, i);

    // footer
    int last = size - 1;
    for (int i = last; i > last - n; i--)
      uf.union(last, i);
  }
}