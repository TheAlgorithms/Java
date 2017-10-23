/**
 *
 * @author Daniel Caceres (https://github.com/daacaceressa)
 *
 */
class IntegerPointsInsidePolygon {
	
	
	/**
     * Pick's Theorem
     * 
     * A = I + B/2 -1
     * 
     * A - Area of the polygon
     * I - Integer points inside the polygon
     * B - Integer points in the boundary of the polygon 
     * 
     **/
	
	/**
     * This method implements Pick's theorem for finding the amount
     * of integer points inside a simple polygon.
     * The points of the polygon must be integer points in order for this theorem to work.
     *
     * @param points: The points of the polygon given one after the other
     * (it doesn't matter if they are given clockwise or counterclockwise) 
     **/
	
	public static int PicksTheorem( Point points[] ) {
		double polygonArea = Area( points );
		int polygonBoundaryPoints = Boundary( points );
		
		//Apply Pick's theorem
		int polygonInsidePoints = (int) (polygonArea - polygonBoundaryPoints/2.0 + 1);
		
		return polygonInsidePoints;
	}
	
	
	/**
     * This method finds the area of any polygon.
     *
     * @param points: The points of the polygon given one after the other
     * (it doesn't matter if they are given clockwise or counterclockwise) 
     **/
	public static double Area( Point points[] ) {
		double area = 0.0;
		int sz = points.length;
		
		//We add the cross product of every two consecutive points
		for( int i = 0; i < sz; i++ ) {
			area += cross( points[i], points[(i+1)%sz] );
		}
		return Math.abs(area)/2.0;
	}
	
	/**
     * This method finds the amount of integer points inside a simple polygon 
     *
     * @param points: The points of the polygon given one after the other
     * (it doesn't matter if they are given clockwise or counterclockwise) 
     **/
	public static int Boundary( Point points[] ) {
		int boundaryPoints = 0;
		int sz = points.length;
		
		//We add the amount of points in every line of the polygon
		for( int i = 0; i < sz; i++ ) {
			Point A = points[i];
			Point B = points[(i+1)%sz];
			boundaryPoints += GCD( Math.abs(A.x-B.x), Math.abs(A.y-B.y) );
		}
		return boundaryPoints;
	}
	
	
	/**
     * This method implements the cross product between two points
     * 
     * @param A: First point
     * @param B: Second point
     **/
	public static int cross( Point A, Point B ) {
		return A.x*B.y - A.y*B.x;
	}
	
	/**
     * This method finds the GCD(a,b)
     **/
	public static int GCD(int a, int b) {
	   if (b==0) return a;
	   return GCD(b,a%b);
	}
	
	/** Driver Program **/
	public static void main(String[] args) {
		
		//Square example
		Point[] square = new Point[4];
		square[0] = new Point( 0,0 );
		square[1] = new Point( 10,0 );
		square[2] = new Point( 10,10 );
		square[3] = new Point( 0,10 );
		
		System.out.println( PicksTheorem(square) );
		
		
		//Pentagon example
		Point[] pentagon = new Point[6];
		pentagon[0] = new Point( 10,0 );
		pentagon[1] = new Point( 5,10 );
		pentagon[2] = new Point( -5,10 );
		pentagon[3] = new Point( -10,0 );
		pentagon[4] = new Point( -5,-10 );
		pentagon[5] = new Point( 5,-10 );
		
		System.out.println( PicksTheorem(pentagon) );
		
		
		//Non-regular polygon example
		Point[] polygon = new Point[6];
		polygon[0] = new Point( 0,0 );
		polygon[1] = new Point( 0,40 );
		polygon[2] = new Point( 50,25 );
		polygon[3] = new Point( 100,40 );
		polygon[4] = new Point( 100,0 );
		polygon[5] = new Point( 50,15 );
		
		System.out.println( PicksTheorem(polygon) );
	}
}

/**
 * A simple class for handling points
 **/
class Point {
	int x,y;
	Point() {}
	Point( int x, int y ) {
		this.x = x;
		this.y = y;
	}
}
