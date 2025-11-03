import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // TODO: Add any necessary instance variables.
    int boardSize;
    boolean[][] universe;
    WeightedQuickUnionUF unionUF;
    int numberOfOpenSite = 0;
    int top;
    int bottom;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        boardSize = N;
        universe = new boolean[boardSize][boardSize];
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                universe[r][c] = false;
            }
        }
        //create extra two boxes to put virtual top and bottom.
        unionUF = new WeightedQuickUnionUF(boardSize*boardSize +2);
        top = boardSize*boardSize;
        bottom = top + 1;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("row or col index out of bounds");
        }

        int siteIndex = xyTo1D(row, col);
        universe[row][col] = true;
        numberOfOpenSite++;

        if (row == 0) unionUF.union(siteIndex, top);
        if (row == boardSize) unionUF.union(siteIndex, bottom);

        int[][] dir = {{1, 0}, {-1,0}, {0, 1}, {0, -1}};

    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return universe[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return numberOfOpenSite;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    public int xyTo1D(int r, int c) {
        return c*boardSize+r;
    }
    public boolean isValid(int row, int col) {
        return (row >= 0 && row < boardSize && col >= 0 && col < boardSize);
    }
}
