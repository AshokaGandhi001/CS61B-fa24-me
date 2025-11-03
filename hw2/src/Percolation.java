import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // TODO: Add any necessary instance variables.
    int boardSize;
    boolean[][] universe;
    WeightedQuickUnionUF unionUF;
    int numberOfOpenSite = 0;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        boardSize = N;
        universe = new boolean[boardSize][boardSize];
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                universe[r][c] = false;
            }
        }
        unionUF = new WeightedQuickUnionUF(boardSize*boardSize);
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        universe[row][col] = true;
        numberOfOpenSite++;
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
}
