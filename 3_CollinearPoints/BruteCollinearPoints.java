import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
	
	private int numberOfSegments;
	private LineSegment lineSegment[];
	
	public BruteCollinearPoints(Point[] points) {
		Point[] pointsCopy = Arrays.copyOf(points, points.length);
		Arrays.sort(pointsCopy);
		
		ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
		
		for (int i = 0; i < pointsCopy.length; i++) {
			if (pointsCopy[i] == null) {
				throw new java.lang.IllegalArgumentException("Constructor arguments are null");
			}
			
			if (i != pointsCopy.length-1 && pointsCopy[i].compareTo(pointsCopy[i+1]) == 0) {
				throw new java.lang.IllegalArgumentException("Duplicated entries in given points.");
			}
		}

		double slopes[] = new double[4];
		numberOfSegments = 0;
		
		for (int i = 0; i < pointsCopy.length-3; i++) {
			for (int j = i+1; j < pointsCopy.length-2; j++) {
				for (int k = j+1; k < pointsCopy.length-1; k++) {
					for (int l = k+1; l < pointsCopy.length; l++) {
						slopes[0] = pointsCopy[i].slopeTo(pointsCopy[j]);
						slopes[1] = pointsCopy[j].slopeTo(pointsCopy[k]);
						slopes[2] = pointsCopy[k].slopeTo(pointsCopy[l]);
						slopes[3] = pointsCopy[l].slopeTo(pointsCopy[i]);
						
						if (slopes[0] == slopes[1] && slopes[1] == slopes[2] && slopes[3] == slopes[0]) {
							numberOfSegments++;
							segments.add(new LineSegment(pointsCopy[i], pointsCopy[l]));
						}
					}
				}
			}
		}
		lineSegment = new LineSegment[segments.size()];
		lineSegment = segments.toArray(lineSegment);
	}    // finds all line segments containing 4 points
	
	public int numberOfSegments() {
		return numberOfSegments;
	}// the number of line segments
	
	public LineSegment[] segments() {
		return lineSegment;
	}// the line segments
	
	/* public static void main(String[] args) {

		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		StdOut.println("Output end");
		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	} */
}