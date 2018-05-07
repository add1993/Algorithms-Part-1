import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean[][] grid;
	private final WeightedQuickUnionUF UF;
	private final WeightedQuickUnionUF ufIsFull;
	private int openSites;
	private final int size;

	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Percolation : Given value "+n+" is out of range");
		}
		
		UF = new WeightedQuickUnionUF(n*n + 2);
		ufIsFull = new WeightedQuickUnionUF(n*n + 1);
		size = n;
		openSites = 0;
		grid = new boolean[n][n];
	}   
	
	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		int index1;
		int index2;
		
		validate(row, size);
		validate(col, size);
		
		if (isOpen(row, col)) {
			return;
		}
		
		grid[row - 1][col - 1] = true;
		index1 = getIndex(row, col);
		openSites++;
		
		if (row-1 > 0) {
			if (isOpen(row-1, col)) {
				index2 = getIndex(row-1, col);				
				UF.union(index2, index1);
				ufIsFull.union(index2, index1);
			}
		}
		
		if (col-1 > 0) {
			if (isOpen(row, col-1)) {
				index2 = getIndex(row, col-1);				
				UF.union(index2, index1);
				ufIsFull.union(index2, index1);
			}
		}
		
		if (row+1 <= size) {
			if (isOpen(row+1, col)) {
				index2 = getIndex(row+1, col);				
				UF.union(index2, index1);
				ufIsFull.union(index2, index1);
			}
		}
		
		if (col+1 <= size) {
			if (isOpen(row, col+1)) {
				index2 = getIndex(row, col+1);				
				UF.union(index2, index1);
				ufIsFull.union(index2, index1);
			}
		}
		
		if (row - 1 == 0) {
			UF.union(0, index1);
			ufIsFull.union(0, index1);
		}
		
		if (row - 1 == size-1) {
			UF.union(size*size+1, index1);
		}
		
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		validate(row, size);
		validate(col, size);
		
		if (grid[row-1][col-1]) {
			return true;
		}
		
		return false;
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		validate(row, size);
		validate(col, size);
		
		if (isOpen(row, col)) {
			int index = getIndex(row, col);
			return ufIsFull.connected(0, index);
		}
		
		return false;
	}
	
	// number of open sites
	public int numberOfOpenSites() {
		return openSites;
	}

	// does the system percolate?
	public boolean percolates() {
		if (UF.connected(0, size*size+1)) {
			return true;
		}
		return false;
	}
	
	// get index of weighted union find datastructure
	private int getIndex(int i, int j) {
        return (i - 1) * size + j;
	}

	// validate if values are in range
	private boolean validate(int n, int size) {
		if (n <= 0 || n > size) {
			throw new IllegalArgumentException("Percolation : Given value = "+n+" is out of range = "+size);
		}
		return true;
	}
}