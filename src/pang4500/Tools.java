package pang4500;

//---------------------------------------------------------------
/**
 * A utilities class containing functions to:
 * 
 * <ul>
 * <li>Calculate the GCD of two integers</li>
 * </ul>
 * 
 * (Note that any of the given versions of the GCD are acceptable.)
 * 
 * @author Dr Eugene Zima
 * @author David Brown
 * @version 2012-09-20
 */
public class Tools {

	// ---------------------------------------------------------------
	/**
	 * Uses iteration to calculate the GCD.
	 * 
	 * @param a
	 * @param b
	 * @return the greatest common divisor of two integers.
	 */
	public static double gcd(double a, double b) {
		double t = 0;

		if (a < b) {
			t = a;
			a = b;
			b = t;
		}
		while (b != 0) {
			t = a % b;
			a = b;
			b = t;
		}
		return (a);
	}

	// ---------------------------------------------------------------
	/**
	 * Uses recursion (Euclid's algorithm) to calculate the GCD.
	 * 
	 * @param a
	 * @param b
	 * @return the greatest common divisor of two integers. Based on the fact
	 *         that the gcd from p and q is the same as the gcd from p and p % q
	 *         in case p is larger then q
	 */
	public static double gcd_r(double a, double b) {
		double denom = 0;

		if (b == 0) {
			denom = a;
		} else {
			denom = gcd_r(b, a % b);
		}
		return denom;
	}

	// ---------------------------------------------------------------
	/**
	 * Uses an iterative form of Euclid's algorithm.
	 * 
	 * @param a
	 * @param b
	 * @return the greatest common divisor of two integers.
	 */
	public static double gcd2(double a, double b) {
		double x = 0;
		double y = 0;

		while (a % b != 0) {
			x = b;
			y = a % b;
			a = x;
			b = y;
		}
		return b;
	}

	// ---------------------------------------------------------------
}
