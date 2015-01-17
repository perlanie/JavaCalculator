package pang4500;

import java.util.Scanner;

//---------------------------------------------------------------
/**
 * Contains the static utility method <code>process</code> to process a stack of
 * polynomials. This method allows input to be taken from the keyboard, a file,
 * or any other input stream supported by <code>Scanner</code>.
 * 
 * @author Dr. Eugene Zima
 * @author David Brown
 * @version 2012-11-07
 */
public class Manipulate {

    /**
     * Processes polynomials and associated commands from an input
     * <code>Scanner</code> object. Polynomials in the input stream are added to
     * a stack, commands cause the polynomials on the top of the stack to be
     * processed singly or in pairs, depending on the command. Supports the
     * commands:
     * <ul>
     * <li><code>DIFF</code>: differentiate a polynomial</li>
     * <li><code>ADD</code>: add two polynomials</li>
     * <li><code>SUB</code>: subtract one polynomial from another</li>
     * <li><code>MUL</code>: multiplies one polynomial by another</li>
     * <li><code>EVALAT <i>x</i></code>: evaluates a polynomial for a value
     * <i>x</i></li>
     * <li><code>EVALAT <i>s f n</i></code>: evaluates a polynomial for <i>n</i>
     * values from <i>s</i> to <i>f</i></li>
     * <li><code>TEST</code>: compares two polynomials for equality</li>
     * <li><code>ROOTS</code>: prints the roots of a polynomial, if any</li>
     * <li><code>QUIT</code>: stops processing the input stream. Polynomials
     * remaining on the stack are printed.</li>
     * </ul>
     * 
     * @param scanner
     *            The <code>Scanner</code> object to gather the polynomial and
     *            command input from.
     * @throws Exception
     */
    public static void process(final Scanner scanner) {
	// The stack of polynomials to process.
	final GenericStack<Poly> stack = new GenericStack<Poly>();
	Poly p = null;
	Poly q = null;
	String line = null;

	while (scanner.hasNextLine() && !scanner.hasNext("QUIT")) {
	    try {
		// Get the next line of the input.
		line = scanner.nextLine();

		if (line.length() == 0) {
		    System.out.println();
		} else if (line.equals("PRINT")) {
		    // Print the polynomial at the top of the stack.
		    System.out.println(stack.peek());
		} else if (line.equals("DIFF")) {
		    // Differentiate the polynomial.
		    p = stack.pop();
		    stack.push(p.diff());
		} else if (line.equals("ADD")) {
		    // Add together two polynomials.
		    p = stack.pop();
		    q = stack.pop();
		    stack.push(q.add(p));
		} else if (line.equals("SUB")) {
		    // Subtracts one polynomial from another.
		    q = stack.pop();
		    p = stack.pop();
		    stack.push(p.sub(q));
		} else if (line.equals("MUL")) {
		    // Multiplies two polynomials together.
		    p = stack.pop();
		    q = stack.pop();
		    stack.push(q.mult(p));
		} else if (line.startsWith("EVALAT")) {
		    final String[] numbers = line.split(" ");

		    if (numbers.length == 2) {
			// Evaluates a polynomial against a single x value.
			final Rational x = new Rational(numbers[1]);
			p = stack.peek();
			final Rational y = p.evalAt(x);
			System.out.println(y);
		    } else if (numbers.length == 4) {
			// Evaluates a polynomial against a range of
			// rational values.
			final Rational s = new Rational(numbers[1]);
			final Rational f = new Rational(numbers[2]);
			final int n = Integer.parseInt(numbers[3]);

			if (n < 1) {
			    throw new ManipulateException(line,
				    ManipulateException.Flags.INVALID_COUNT);
			}
			final Rational h = f.subtract(s)
				.divide(new Rational(n));
			p = stack.peek();
			// Initialize x.
			Rational x = s;

			for (int i = 0; i < n; i++) {
			    // Evaluate the polynomial at each point in the
			    // range - print the results. (Loop by the number of
			    // points,not by the incremental value of x.)
			    final Rational y = p.evalAt(x);
			    System.out.println(x.toString() + "\t "
				    + y.toString());
			    // Increment x by the interval h.
			    x = x.add(h);
			}
		    } else {
			throw new ManipulateException(line,
				ManipulateException.Flags.INVALID_EVALUATION);
		    }
		} else if (line.equals("TEST")) {
		    // Compares two polynomials for equality. If they are the
		    // same, only one is put back on the stack. If different,
		    // both are returned to the stack.
		    p = stack.pop();
		    q = stack.pop();

		    if (p.equals(q)) {
			stack.push(p);
		    } else {
			stack.push(q);
			stack.push(p);
		    }
		} else if (line.equals("ROOTS")) {
		    // Prints the roots of a polynomial.
		    stack.peek().iroots();
		} else if (line.startsWith("#")) {
		    // Prints a comment.
		    System.out.println(line);
		} else if (Character.isUpperCase(line.charAt(0))) {
		    // Assumes commands start with upper case letters - this
		    // command is not recognized.
		    throw new ManipulateException(line,
			    ManipulateException.Flags.INVALID_COMMAND);
		} else {
		    // Not a command - assumed to be a polynomial definition.
		    // Pushes a new polynomial on the stack.
		    p = new Poly(line);
		    stack.push(p);
		}
	    } catch (Exception e) {
		// Uncomment next line to determine location of error.
		// e.printStackTrace();
		System.out.println(e.getMessage());
	    }
	}
	scanner.nextLine();
	System.out.println("Quitting");

	// Print the remaining polynomials on the stack.
	while (!stack.isEmpty()) {
	    System.out.println(stack.pop());
	}
    }
    // ---------------------------------------------------------------
}
