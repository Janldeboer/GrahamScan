package project.point;

/**
 * Implementation of a point with the coordinates x and y.
 * Points are comparable and printable.
 * 
 * @author Jan de Boer
 *
 */
public class Point implements Comparable<Point>{
	private double x;
	private double y;
	
	/**
	 * Constructor taking the coordinates. These can not be changed later.
	 * @param xCord
	 * @param yCord
	 */
	public Point(double xCord, double yCord) {
		x = xCord;
		y = yCord;
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Method to conform to the Comparable-Protocol.
	 * Compares this Point to another Point a.
	 * @param Point a
	 * @return 	-1 	if this < a
	 * 			0	if this = a
	 * 			1	if this > a
	 */
	public int compareTo(Point a) 
    {
		double result;
		if(this.getY() != a.getY()) {
			result = this.getY() - a.getY();
		} else {
			result =  this.getX() - a.getX();
		}
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		} else {
			return 0;
		}
    }
	
	/**
	 * toString-method to make points printable.
	 * @return printable String, representing the point
	 */
	public String toString() {
		return getX() + "," + getY();
		//return "("+ getX() + "|" + getY() + ")";
	}
	
	/**
	 * Calculates the distance to another point
	 * @param pointA
	 * @return
	 */
	public double distanceTo(Point pointA) {
		double xDif = this.getX() - pointA.getX();
		double yDif = this.getY() - pointA.getY();
		return Math.pow(xDif*xDif+yDif*yDif, 0.5);
	}
	
}