package project.gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import project.mylist.MyListSlot;
import project.mylist.MyList;
import project.point.Point;
import project.tools.GrahamScan;
import project.tools.Utility;


public class GrahamVisualizer {

	private static int windowWidth, windowHeigth;
	private static double xMin, xMax, yMin, yMax;
	private static double borderSize = 20;

	/**
	 * This function calculates the Graham Scan and creates the nodes for plotting it.
	 *
	 * @param points
	 * @param windowWidth
	 * @param windowHeigth
	 * @return
	 */
	public static Node[] visualizeGrahamScan(MyList<Point> points, int windowWidth, int windowHeigth) {
		GrahamVisualizer.windowHeigth = windowHeigth;
		GrahamVisualizer.windowWidth = windowWidth;
		MyList<Point> allPoints = points;
        MyList<Point> convexCasePoints = GrahamScan.getConvexCase(allPoints);
        int numberOfNodes = allPoints.getLength() + convexCasePoints.getLength();
        Node[] nodes = new Node[numberOfNodes];
        int i = 0;
        MyListSlot<Point> pointerSlot = convexCasePoints.getHeadSlot();
        xMin = pointerSlot.getObject().getX();
		xMax = pointerSlot.getObject().getX();
		yMin = pointerSlot.getObject().getY();
		yMax = pointerSlot.getObject().getY();

		pointerSlot = convexCasePoints.getHeadSlot().getNext();
		while(pointerSlot != null) {
			if (pointerSlot.getObject().getX() < xMin) {
				xMin = pointerSlot.getObject().getX();
			}
			if (pointerSlot.getObject().getX() > xMax) {
				xMax = pointerSlot.getObject().getX();
			}
			if (pointerSlot.getObject().getY() < yMin) {
				yMin = pointerSlot.getObject().getY();
			}
			if (pointerSlot.getObject().getY() > yMax) {
				yMax = pointerSlot.getObject().getY();
			}
			pointerSlot = pointerSlot.getNext();
		}

		pointerSlot = allPoints.getHeadSlot();
        while(pointerSlot != null){
            nodes[i++] = drawPoint(pointerSlot.getObject());
            pointerSlot = pointerSlot.getNext();
        }
        pointerSlot = convexCasePoints.getHeadSlot();
		nodes[i++] = drawLine(pointerSlot.getObject(), convexCasePoints.getTailSlot().getObject());
        while(pointerSlot.getNext() != null){
			nodes[i++] = drawLine(pointerSlot.getObject(), pointerSlot.getNext().getObject());
			pointerSlot = pointerSlot.getNext();
		}
        return nodes;
	}

	/**
	 * Wrapper function to draw a point.
	 * @param point
	 * @return
	 */
	private static Node drawPoint(Point point) {
		double x = Utility.normalize(point.getX(),xMin,xMax);
		double y = Utility.normalize(point.getY(),yMin,yMax);
		x = x * (windowWidth - 2 * borderSize) + borderSize;
		y = windowHeigth - y * (windowHeigth - 2 * borderSize) - borderSize;
		Circle circle = new Circle(point.getX(), point.getY(), 3);
		return circle;
	}

	/**
	 * Wrapper function to draw a line.
	 * @param pointA
	 * @param pointB
	 * @return
	 */
	private static Node drawLine(Point pointA, Point pointB) {
		double xA = Utility.normalize(pointA.getX(),xMin,xMax);
		double yA = Utility.normalize(pointA.getY(),yMin,yMax);
		double xB = Utility.normalize(pointB.getX(),xMin,xMax);
		double yB = Utility.normalize(pointB.getY(),yMin,yMax);
		xA = xA * (windowWidth - 2 * borderSize) + borderSize;
		yA = windowHeigth - yA * (windowHeigth - 2 * borderSize) - borderSize;
		xB = xB * (windowWidth - 2 * borderSize) + borderSize;
		yB = windowHeigth - yB * (windowHeigth - 2 * borderSize) - borderSize;
		Line line = new Line(0, 0,   0, 0);
		return line;
	}
}

