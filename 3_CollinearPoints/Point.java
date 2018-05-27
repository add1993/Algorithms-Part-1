import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public final class Point implements Comparable<Point> {
	private final int x;
	private final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}
	
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public int compareTo(Point that) {
		if (that == null) {
			throw new java.lang.NullPointerException("Argument is null");
		}
		
		if (this.y > that.y) {
			return 1;
		} else if (this.y < that.y) {
			return -1;
		} else if (this.y == that.y && this.x > that.x) {
			return 1;
		} else if (this.y == that.y && this.x < that.x) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public double slopeTo(Point that) {
		if (that == null) {
			throw new java.lang.NullPointerException("Argument is null");
		}
		
		double slope;
		if (this.x == that.x) {
			if (this.y == that.y) {
				return Double.NEGATIVE_INFINITY;
			}
			return Double.POSITIVE_INFINITY;
		} else if (this.y == that.y) {
			return 0;
		} else {
			slope = (double)(this.y - that.y)/(double)(this.x - that.x); 
			return slope;
		}
	}
	
	private class BySlopeOrder implements Comparator<Point> {
		public int compare(Point A, Point B) {
			if (A == null || B == null) {
				throw new java.lang.NullPointerException("Argument is null");
			}
			double slopeA, slopeB;
			slopeA = slopeTo(A);
			slopeB = slopeTo(B);
			
			if (slopeA > slopeB) {
				return 1;
			} else if (slopeA < slopeB) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	public Comparator<Point> slopeOrder() {
		return new BySlopeOrder();
	}
	
	public static void main(String args[]) {
		Point p = new Point(411, 45);
		Point q = new Point(295, 359);
		Point r = new Point(436, 311);
		
		StdOut.println("p.compare(q, r) " + p.slopeOrder().compare(q, r));
		StdOut.println("p.slopeTo(q) " + p.slopeTo(q));
		StdOut.println("p.slopeTo(r) " + p.slopeTo(r));
	}
}