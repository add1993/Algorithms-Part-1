public class BruteCollinearPoints {
	
	int numberOfSegments;
	LineSegment lineSegment[];
	
	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.IllegalArgumentException("Constructor arguments are null");
		}
		
		double slopes[] = new double[4];
		lineSegment = new LineSegment[25];
		numberOfSegments = 0;
		
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				for (int k = 0; k < points.length; k++) {
					for (int l = 0; l < points.length; l++) {
						if (i == j || j == k || k == l || i == l) {
							continue;
						}
						slopes[0] = points[i].slopeTo(points[j]);
						slopes[1] = points[j].slopeTo(points[k]);
						slopes[2] = points[k].slopeTo(points[l]);
						slopes[3] = points[l].slopeTo(points[i]);
						
						if (slopes[0] == slopes[1] && slopes[1] == slopes[2] && slopes[3] == slopes[0]) {
							lineSegment[numberOfSegments++] = new LineSegment(points[i], points[l]);
						}
					}
				}
			}
		}
		
	}    // finds all line segments containing 4 points
	
	public int numberOfSegments() {
		return numberOfSegments;
	}// the number of line segments
	
	public LineSegment[] segments() {
		return lineSegment;
	}// the line segments
}