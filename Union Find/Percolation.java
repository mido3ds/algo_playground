import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF uf;
    private final int lastCellPos;
    private boolean[] open;
    private int numOfOpen = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException("n cant be <= 0");

        this.n = n;
        this.lastCellPos = n * (n + 2) - 1;

        // array is from 0 to n - 1 and put header and footer
        uf = new WeightedQuickUnionUF(n * (n + 2));

        open = new boolean[n * (n + 2)];
        for (int i = 0; i < open.length; i++) open[i] = false;

        connectHeaderFooter();
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        // check in range
        if (!isValidPosition(row, col)) throw new java.lang.IllegalArgumentException();

        int pos = getPosition(row, col);
        if (open[pos]) return;

        open[pos] = true;
        numOfOpen++;

        int[][] ne = getNeighbours(row, col);
        for (int i = 0; i < ne.length; i++) {
            if (ne[i][0] == -1) break;
            if (isOpen(ne[i][0], ne[i][1])) {
                uf.union(pos, getPosition(ne[i][0], ne[i][1]));
            }
        }

    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return open[getPosition(row, col)];
    }

    // is site (row, col) full?
    // full when connected to header
    public boolean isFull(int row, int col) {
        return uf.connected(0, getPosition(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpen;
    }

    // does the system percolate?
    // is there a connection between last and first ?
    public boolean percolates() {
        return uf.connected(0, lastCellPos);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 4, size = n * (n + 2), first = 0, last = size - 1;
        Percolation p = new Percolation(n);

        p.testNeighbours();

        // test
        assert p.uf.connected(first, n - 1);
        assert p.uf.connected(last, last - n + 1);
        assert !p.uf.connected(first, n);

        assert p.getPosition(1, 1) == 4;
        assert p.getPosition(1, 2) == 5;
        assert p.getPosition(4, 4) == 19;

        assert p.numberOfOpenSites() == 0;

        assert !p.isFull(1, 1);
        p.open(1, 1);
        assert p.isOpen(1, 1);
        assert p.isFull(1, 1);

        System.out.println("all tests are ok");
    }

    // check row,col in range [1, n]
    private boolean isValidPosition(int row, int col) {
        return ((row > 0 && row <= n) && (col > 0 && col <= n));
    }

    // check row in range [0, n+1] and col in range [1, n], for padding
    private boolean isValidPositionWithPadding(int row, int col) {
        return ((row >= 0 && row <= n + 1) && (col > 0 && col <= n));
    }

    // row,col in range [1, n]
    private int getPosition(int row, int col) {
        // check in range
        if (!isValidPositionWithPadding(row, col)) throw new java.lang.IllegalArgumentException();

        // row will stay as it is, because of pading
        // col is 0 indexed
        col--;

        return (row * n + col);
    }

    // return array of neghbours row & col
    private int[][] getNeighbours(int row, int col) {
        final int sizeOfNe = 4;
        int[][] ne = new int[sizeOfNe][2];
        int j = 0;

        // make all rows and cols = -1
        for (int i = 0; i < ne.length; i++) {
            ne[i][0] = -1;
            ne[i][1] = -1;
        }

        // change row
        for (int i = -1; i < 3; i += 2) {
            int row2 = row + i;
            if (isValidPositionWithPadding(row2, col)) {
                ne[j][0] = row2;
                ne[j++][1] = col;
            }
        }

        // change col
        for (int i = -1; i < 3; i += 2) {
            int col2 = col + i;
            if (isValidPositionWithPadding(row, col2)) {
                ne[j][0] = row;
                ne[j++][1] = col2;
            }
        }

        return ne;
    }

    private void testNeighbours() {
        // test getneighbours
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.println("sending: \n" + i + ", " + j);

                int[][] arr = getNeighbours(i, j);

                System.out.println("received: ");
                for (int m = 0; m < arr.length; m++) {
                    System.out.print("(" + arr[m][0] + ", " + arr[m][1] + ")");
                }

                System.out.println("\n***********");
            }
        }
    }

    // connect elements of header and elements of footer
    // and makes them open
    private void connectHeaderFooter() {
        int size = n * (n + 2);

        // header
        int first = 0;
        for (int i = 0; i < n; i++) {
            uf.union(first, i);
            open[i] = true;
        }

        // footer
        int last = size - 1;
        for (int i = last; i > last - n; i--) {
            uf.union(last, i);
            open[i] = true;
        }
    }
}