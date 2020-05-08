package project.tools;

import project.mylist.MyList;
import project.mylist.MyListSlot;
import project.point.Point;
import project.point.PointAngleComperator;
import project.point.PointUtilities;


/**
 * Implementation of the Graham Scan.
 * This class can calculate the convex case for an amount of points.
 * 
 * @author jan
 *
 */
public class GrahamScan {
	
	/**
	 * Calculates a list with the points of the convex case.
	 * @param points
	 * @return
	 */
	public static MyList<Point> getConvexCase(MyList<Point> inputPoints) {
		MyList<Point> points = inputPoints.cloneList();
		Point startPoint = getStartPoint(points);
		PointAngleComperator angleComperator = getPointAngleComperator(startPoint);
		points.removeObject(startPoint);
		points = MergeSort.sort(points, angleComperator);
		points = deleteEqualAnglePoints(points, startPoint);
		points.addObjectAsHead(startPoint);
		points = deleteInnerPoints(points);
		return points;
	}
	
	/**
	 * Creates a Point-Angle-Comperator for a given start point.
	 * @param startPoint
	 * @return Comperator
	 */
	private static PointAngleComperator getPointAngleComperator(Point startPoint) {
		return new PointAngleComperator(startPoint);
	}
	
	/**
	 * Finds the start point for the Graham Scan in the list.
	 * @param points: list of points
	 * @return startpoint
	 */
	private static Point getStartPoint(MyList<Point> points) {
		return points.min();
	}
	
	/**
	 * This function deletes all points in list that have the same angle to a given start point.
	 * @param points: list of points
	 * @param startPoint
	 * @return list without two points with an equal angle
	 */
	private static MyList<Point> deleteEqualAnglePoints(MyList<Point> inputPoints, Point startPoint) {
		MyList<Point> points = inputPoints.cloneList();
		PointAngleComperator angleComperator = getPointAngleComperator(startPoint);
		MyListSlot<Point> pointer = points.getHeadSlot();
		while(pointer.getNext() != null) {
			if(hasEqualAngleThanNext(pointer, angleComperator)) {
				points.removeObject(pointer.getNext().getObject());
			} else {
				pointer = pointer.getNext();
			}
		}
		return points;
	}
	
	/**
	 * Checks if two following Points in the list have the same angle.
	 * @param slot: start slot
	 * @param angleComperator
	 * @return true/false
	 */
	private static boolean hasEqualAngleThanNext(MyListSlot<Point> slot, PointAngleComperator angleComperator) {
		Point thisPoint = slot.getObject();
		Point followingPoint = slot.getNext().getObject();
		if(angleComperator.compare(thisPoint, followingPoint) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This function finds the inner points and removes them from the list.
	 * @param points: list of points
	 * @return list of case points
	 */
	private static MyList<Point> deleteInnerPoints(MyList<Point> inputPoints) {
		MyList<Point> points = inputPoints.cloneList();
		MyListSlot<Point> pointer = points.getHeadSlot().getNext();
		while(pointer != null) {
			Point pointA = pointer.getPrevious().getObject();
			Point pointB = pointer.getObject();
			Point pointC = pointer.getNext() != null ? pointer.getNext().getObject() : points.getHeadSlot().getObject();
			double determinant = PointUtilities.calculateDeterminant(pointA, pointB, pointC);
			if (determinant > 0 ) {
				pointer = pointer.getNext();
			} else {
				pointer = pointer.getPrevious();
				points.removeObject(pointer.getNext().getObject());
				if(pointer.getPrevious() == null) {
					pointer = pointer.getNext();
				}
			}
		}
		return points;
	}
}
