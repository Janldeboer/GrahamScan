package project.point;

/**
 * A class containing utilities for working with points.
 * 
 * @author Jan de Boer
 *
 */
public class PointUtilities {
	/**
	 * Calculates the determinant of A, B and C.
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return determinant
	 */
	public static double calculateDeterminant(Point pointA, Point pointB, Point pointC) {
		return ( (pointB.getX()-pointA.getX()) * (pointC.getY()-pointA.getY())  -  (pointC.getX()-pointA.getX()) * (pointB.getY()-pointA.getY()) );
	}
	
	/**
	 * This method creates a Point with the coordinates given comma seperated in a string. 
	 * @param inputString
	 * @return Point
	 */
	public static Point getPointFromString(String inputString) {
		String[] coordinatesAsStrings = inputString.split(",");
		double x = Double.parseDouble(coordinatesAsStrings[0]);
		double y = Double.parseDouble(coordinatesAsStrings[1]);
		return new Point(x, y);
	}
	
	/**
	 * Parser for string to an array filled with points.
	 * @param inputString
	 * @return array wiht points
	 */
	public static Point[] getPointArrayFromString(String inputString) {
		String[] pointsAsString = inputString.split(System.getProperty("line.separator"));
		Point[] points = new Point[pointsAsString.length];
		for(int i = 0; i < pointsAsString.length; i++) {
			points[i]= getPointFromString(pointsAsString[i]); 
		}
		return points;
	}
}
