import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
	
	private int numberOfSegments;
	private LineSegment []lineSegment;
	
	public FastCollinearPoints(Point[] points) {
		Point[] pointsCopy = Arrays.copyOf(points, points.length);
		ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
		ArrayList<Point> skipList = new ArrayList<Point>();
		ArrayList<Point> tmpList = new ArrayList<Point>();
		
		for (int i = 0; i < pointsCopy.length; i++) {
			if (pointsCopy[i] == null) {
				throw new java.lang.IllegalArgumentException("Constructor arguments are null");
			}
			
			if (i != pointsCopy.length-1 && pointsCopy[i].compareTo(pointsCopy[i+1]) == 0) {
				throw new java.lang.IllegalArgumentException("Duplicated entries in given points.");
			}
		}
		
		numberOfSegments = 0;

		int slope_index;
		// double slope[] = new double[pointsCopy.length];
		
		LineSegment line1, line2;
		double slope;
		int end_index, start_index, isPart, repCount;
		for (int j = 0; j < pointsCopy.length; j++) {
			Arrays.sort(pointsCopy);
			Arrays.sort(pointsCopy, pointsCopy[j].slopeOrder());
			// StdOut.println("For point");
			// StdOut.println(pointsCopy[j].toString());
			for (int i = 1; i < pointsCopy.length; i++) {
				slope_index = 0;
				end_index = 1;
				start_index = i;
				isPart = 0;
				repCount = 0;
				slope = pointsCopy[0].slopeTo(pointsCopy[i]);
				
				// slope[slope_index++] = pointsCopy[0].slopeTo(pointsCopy[i]);
				// if (!skipList.contains(pointsCopy[i])) {
				//	tmpList.add(pointsCopy[i]);
				// }
				while (i < pointsCopy.length && slope == pointsCopy[0].slopeTo(pointsCopy[i])) {
					if (!(skipList.contains(pointsCopy[i]) && repCount > 1)) {
						tmpList.add(pointsCopy[i]);
						end_index = i;
						slope_index++;
					} else {
						repCount++;
						
						if (repCount > 1) {
							isPart = 1;
						}
					}
					i++;
				}
				i--;
				
				if (slope_index >= 3 && isPart == 0) {
					if (!(skipList.contains(pointsCopy[end_index])&&skipList.contains(pointsCopy[0]))) {
						numberOfSegments++;
						//StdOut.println("Equal slope points");
						//StdOut.println("pointsCopy[0] -> "+pointsCopy[0]);
						//for (int l = start_index; l <= end_index; l++) {
						//	StdOut.print(pointsCopy[l].toString()+"->");
						//}
						
						if (pointsCopy[0].compareTo(pointsCopy[start_index]) < 0) {
							start_index = 0;
						}
						
						line1 = new LineSegment(pointsCopy[start_index], pointsCopy[end_index]);
						segments.add(line1);
						skipList.addAll(tmpList);
						//StdOut.print("Created line segment : ");
						//StdOut.println(line1);
					}
				}
				tmpList.clear();
			}
		}
		lineSegment = new LineSegment[segments.size()];
		lineSegment = segments.toArray(lineSegment);
	}     // finds all line segments containing 4 or more points
	
	public int numberOfSegments() {
		return numberOfSegments;
	}        // the number of line segments
	
	public LineSegment[] segments()  {
		return lineSegment;
	}               // the line segments
	
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