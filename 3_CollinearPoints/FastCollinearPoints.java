import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
	private LineSegment []lineSegment;
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.IllegalArgumentException("Constructor arguments are null");
		}
		
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new java.lang.IllegalArgumentException("Constructor arguments are null");
			}
		}
		
		Point[] pointsCopy = Arrays.copyOf(points, points.length);
		ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

		LineSegment line;
		int end_index, start_index;
		Point P, PStart, PEnd; 
		
		for (int j = 0; j < pointsCopy.length; j++) {
			Arrays.sort(pointsCopy);
			P = pointsCopy[j];
			Arrays.sort(pointsCopy, P.slopeOrder());
			start_index = 0;
			
			while (start_index < pointsCopy.length && P.slopeTo(pointsCopy[start_index]) == Double.NEGATIVE_INFINITY) {
				start_index++;
			}
			
			if (start_index != 1) {
				throw new java.lang.IllegalArgumentException("Duplicate Values found");
			}

			end_index = start_index;
			
			while (start_index < pointsCopy.length) {
				while (end_index < pointsCopy.length && P.slopeTo(pointsCopy[start_index]) == P.slopeTo(pointsCopy[end_index])) {
					end_index++;
				}
				
				if (end_index - start_index >= 3) {
					if (P.compareTo(pointsCopy[start_index]) < 0) {
						PStart = P;
					} else {
						PStart = pointsCopy[start_index];
					}
					
					if (P.compareTo(pointsCopy[end_index-1]) > 0) {
						PEnd = P;
					} else {
						PEnd = pointsCopy[end_index-1];
					}
					
					// To remove the repeated line segments
					if (P == PStart) {
						line = new LineSegment(PStart, PEnd);
						segments.add(line);
					}
				}
				start_index = end_index;
			}
		}
		
		lineSegment = new LineSegment[segments.size()];
		lineSegment = segments.toArray(lineSegment);
	}
	
	// the number of line segments
	public int numberOfSegments() {
		return lineSegment.length;
	}
	
	// the line segments
	public LineSegment[] segments()  {
		return Arrays.copyOf(lineSegment, numberOfSegments());
	}
	
	public static void main(String[] args) {
		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}