import java.util.Arrays;

public class FastCollinearPoints {
	
	int numberOfSegments;
	LineSegment []lineSegment;
	
	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.IllegalArgumentException("Constructor arguments are null");
		}
		numberOfSegments = 0;
		lineSegment = new LineSegment[points.length];
		int min = 9999999;
		int min_index = 0;
		int slope_index = 0;
		Point origin = points[0];
		double slope[] = new double[3];
		for (int i = 1; i < points.length; i++) {
			if (origin.compareTo(points[i]) == 1) {
				origin = points[i];
				min_index = i;
			} 
		}
		
		Arrays.sort(points, points[min_index].BY_SLOPE_ORDER);
		
		for (int i = 0; i < points.length; i++) {
			if (min_index == i) {
				continue;
			}
			
			slope[slope_index++] = points[min_index].slopeTo(points[i]);
			
			if (slope_index == 3) {
				if (slope[0] == slope[1] && slope[1] == slope[2]) {
					lineSegment[numberOfSegments++] = new LineSegment(points[min_index], points[2]);
				}
				
				slope_index = 0;
			}
		}
	}     // finds all line segments containing 4 or more points
	
	public int numberOfSegments() {
		return numberOfSegments;
	}        // the number of line segments
	
	public LineSegment[] segments()  {
		return lineSegment;
	}               // the line segments
}