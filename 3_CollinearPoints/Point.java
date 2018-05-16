import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public final class Point implements Comparable<Point> {
	private final int x;
	private final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y)
	}
	
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public int compareTo(Point that) {
		if (this.y > that.y) {
			return 1;
		} else if (this.y < that.y) {
			return -1;
		} else if (this.y == that.y && this.x > that.x) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public double slopeTo(Point that) {
		if (this.x == that.x) {
			return (that.y - this.y)*Double.POSITIVE_INFINITY;
		} else {
			return (double)(that.y - this.y)/(that.x - this.x);
		}
	}
	
	private static class bySlopeOrder implements Comparator<Point> {
		public int compare(Point A, Point B) {
			if (A.compareTo(this) > B.compareTo(this)) {
				return 1;
			} else if (A.compareTo(this) < B.compareTo(this)) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	public Comparator<Point> slopeOrder() {
		return new bySlopeOrder();
	}
}