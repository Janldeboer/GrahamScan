package project.point;

import java.util.Comparator;

/**
 * 
 * A Comperator to compare the angles of to points to a origin point that was set before.
 * 
 * @author Jan de Boer
 *
 */
public class PointAngleComperator implements Comparator<Point>{
	Point originPoint;
	
	/**
	 * Constructor taking the origin point.
	 * @param origin
	 */
	public PointAngleComperator(Point origin) {
		originPoint = origin;
	}
	
	/**
	 * compare-method to conform to Comperator-protocol.
	 * @return compare result
	 */
	public int compare(Point a, Point b) 
    {
		double angleA = pseudoangleToPoint(a);
		double angleB = pseudoangleToPoint(b);
		int result;
		if(angleA != angleB) { 
			result = angleA > angleB ? 1 : -1;
		} else {
			result = a.compareTo(b);
		}
		return result;
    }
	
	/**
	 * This function calculates a pseudo angle.
	 * It's faster to calculate than a real angle.
	 * 
	 * angleA < angleB <=> pseudoAngleA < pseudoAngleB
	 * angleA > angleB <=> pseudoAngleA > pseudoAngleB
	 * angleA = angleB <=> pseudoAngleA = pseudoAngleB
	 * 
	 * Therefore it's enough for comparisons.
	 * 
	 * @param p
	 * @return
	 */
	private double pseudoangleToPoint(Point p) {
		if(p == originPoint) {
			return 0;
		}
		double xDif = p.getX() - originPoint.getX();
		double yDif = p.getY() - originPoint.getY();
		return xDif / (Math.abs(xDif) + Math.abs(yDif));
	}
}
