import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private static final double CONFIDENCE_95 = 1.96;
	private final double mean;
	private final double stddev;
	private final double cLow;
	private final double cHigh;
	
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials) {
		int row;
		int col;
		double[] arr;
		Percolation perc;
		
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("PercolationStats : Given value is out of range");
		}
		
		arr = new double[trials];
		
		for (int i = 0; i < trials; i++) {
			perc = new Percolation(n);
			
			while (!perc.percolates()) {
				row = StdRandom.uniform(1, n+1);
				col = StdRandom.uniform(1, n+1);
				perc.open(row, col);
			}
			arr[i] = (double) perc.numberOfOpenSites()/(n * n);
		}
		mean = StdStats.mean(arr);
		stddev = StdStats.stddev(arr);
		
		cLow = mean - CONFIDENCE_95*stddev/(Math.sqrt(trials));
		cHigh = mean + CONFIDENCE_95*stddev/(Math.sqrt(trials));
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return mean;
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return stddev;
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		return cLow;
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return cHigh;
	}
	
	public static void main(String[] args) {
		
		PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println("mean = " + percStats.mean());
		System.out.println("stddev = " + percStats.stddev());
		System.out.println("95% confidence interval = [" + percStats.confidenceLo()+","+percStats.confidenceHi()+"]");
	}
}