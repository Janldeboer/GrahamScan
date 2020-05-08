package project.tools;

public class Utility {
		
	/**
	 * Calculating the distance between two arrays.
	 * @param x
	 * @param y
	 * @return
	 */
	public static double distance(double[] x, double[] y) {
		double sum = 0;
		int p = 2;
		int d = x.length;
		for (int i = 0; i < d; i++) {
			sum += Math.pow(getDifference(x[i], y[i]), p);
		}
		return Math.pow(sum, 1.0 / (double) p);
	}
	
	/**
	 * Calculating the difference between two doubles
	 * @param x
	 * @param y
	 * @return
	 */
	public static double getDifference(double x, double y) {
		return Math.abs(x - y);
	}

	/**
	 * Normalizes a value linear.
	 * @param v
	 * @param min
	 * @param max
	 * @return
	 */
	public static double normalize(double v, double min, double max) {
		double a = v - min;
		double b = max - min;
		return a / b;
	}
}