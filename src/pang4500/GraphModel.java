package pang4500;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

//---------------------------------------------------------------
/**
 * @author Perlanie Panganiban Date: Monday, December 2, 2013 Student#:
 *         120574500 Email: pang4500@mylaurier.ca
 */
public class GraphModel {

	// Constants.

	public static final String START_CHANGE = "Start Changed";// Used to signal
																// a change in
																// the Start
																// value
																// property of
																// the model.
	public static final String END_CHANGE = "End Changed";// Used to signal a
															// change in the End
															// value property of
															// the model.
	private int start;// The initial size of the start.
	private int end;// The initial size of the end.
	private Poly p;// the polynomial that will be graphed
	private int SIZE;
	private double xArray[];// the array of x cordinates
	private double yArray[];// the array of y cordinates
	private double xArrayDiff1[];
	private double yArrayDiff1[];
	private double xArrayDiff2[];
	private double yArrayDiff2[];
	// ---------------------------------------------------------------

	/**
	 * Allows views to listen to generic changes in the model.
	 */
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	// ---------------------------------------------------------------
	/**
	 * Attaches listeners to the model.
	 * 
	 * @param listener
	 *            The listener to attach to the model.
	 */
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	// ---------------------------------------------------------------
	/**
	 * Attaches listeners to the model for a particular property.
	 * 
	 * @param propertyName
	 *            The name of the property to listen for.
	 * @param listener
	 *            The listener to attach to the model.
	 */
	public void addPropertyChangeListener(final String propertyName,
			final PropertyChangeListener listener) {
			this.pcs.addPropertyChangeListener(propertyName, listener);
	}
	public void  setSize(int a, int b){
		this.SIZE=100*(Math.abs(this.getStart()) + Math.abs(this.getEnd()));
	}

	// ---------------------------------------------------------------
	public int getSize(){
		return this.SIZE;
	}
	// ---------------------------------------------------------------
	/**
	 * Returns the value of the model's start.
	 * 
	 * @return the value of the model's start.
	 */
	public int getStart() {
		return this.start;
	}

	// ---------------------------------------------------------------
	/**
	 * Returns the value of the model's end.
	 * 
	 * @return the value of the model's end.
	 */
	public int getEnd() {
		return this.end;
	}

	// ---------------------------------------------------------------

	/**
	 * 
	 * @param start
	 *            the integer where the interval starts
	 * @throws NumberFormatException
	 *             if the string is not a digit an exception is thrown
	 */
	public void setStart(String start) throws NumberFormatException {
		try {
			this.start = Integer.parseInt(start);
			this.pcs.firePropertyChange(START_CHANGE, null, this.start);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Start/End is Not an Integer");
		}

	}

	// ---------------------------------------------------------------
	/**
	 * 
	 * @param end
	 *            the integer where the interval ends
	 * @throws NumberFormatException
	 */
	public void setEnd(String end) throws NumberFormatException {
		try {
			this.end = Integer.parseInt(end);
			this.pcs.firePropertyChange(END_CHANGE, null, this.end);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Start/End is Not an Integer");
		}
	}

	// ---------------------------------------------------------------
	/**
	 * graphs the function
	 */
	public void GraphProcess() {

		// TODO Auto-generated constructor stub
		int a = this.getStart();
		int b = this.getEnd();
		this.setSize(a,b);
		double increment = (double)(Math.abs(a) + Math.abs(b)) / SIZE;
	
		Rational x = new Rational((double)a);
		
		this.xArray= new double [SIZE];// the array of x cordinates
		this.yArray = new double[SIZE];//the array of the y coordinates
		this.xArrayDiff1= new double [SIZE];// the array of x cordinates for f'
		this.yArrayDiff1 = new double[SIZE];// the array of y cordinates for f'
		this.xArrayDiff2= new double [SIZE];// the array of x cordinates for f''
		this.yArrayDiff2 = new double[SIZE];// the array of y cordinates for f''
		
		
		//calculates the y coordinates according to the x coordinates.
		for (int i= 0; i <SIZE; i++) {
			x = x.add(new Rational(increment));
			this.xArray[i] = x.convertDouble();
			this.yArray[i] = p.evalAt(new Rational(x)).convertDouble();
			
			this.xArrayDiff1[i] = x.convertDouble();
			this.yArrayDiff1[i] = p.diff().evalAt(new Rational(x)).convertDouble();
			
			this.xArrayDiff2[i] = x.convertDouble();
			this.yArrayDiff2[i] = p.diff().diff().evalAt(new Rational(x)).convertDouble();
		}
		
	}
	// ---------------------------------------------------------------
	/**
	 * 
	 * @return
	 * 		the maximum y value
	 */
	public double getYmax(){
		double ymax=yArray[0];
		for (int i=1; i<yArray.length; i++){
			if (yArray[i]>ymax){
				ymax=yArray[i];
			}
		}
		return ymax;
	}
	
	// ---------------------------------------------------------------
	/**
	 * 
	 * @return
	 * 		the minimum y value
	 */
		public double getYmin(){
			double ymin=yArray[0];
			for (int i=1; i<yArray.length; i++){
				if (yArray[i]<ymin){
					ymin=yArray[i];
				}
			}
			return ymin;
		}
	// ---------------------------------------------------------------
	/**
	 * 
	 * @return the polynomial p
	 */
	public Poly getP() {
		return p;
	}

	// ---------------------------------------------------------------
	/**
	 * 
	 * @param p
	 *            the polynomial p
	 */
	public void setP(Poly p) {
		this.p = p;
	}
	// ---------------------------------------------------------------
		/**
		 * 
		 * @return the array of x cordinates for f''
		 */
		public double[] getxArrayDiff2() {
			return xArrayDiff2;
		}
	// ---------------------------------------------------------------
		/**
		 * 
		 * @return the array of x cordinates for f'
		 */
		public double[] getxArrayDiff1() {
			return xArrayDiff1;
		}

	// ---------------------------------------------------------------
	/**
	 * 
	 * @return the array of x cordinates 
	 */
	public double[] getxArray() {
		return xArray;
	}

	// ---------------------------------------------------------------
	/**
	 * 
	 * @param xArray
	 *            the array of x cordinates
	 */
	public void setxArray(double[] xArray) {
		this.xArray = xArray;
	}
	// ---------------------------------------------------------------
		/**
		 * 
		 * @return the array of y cordinates for f''
		 */
		public double[] getyArrayDiff2() {
			return yArrayDiff2;
		}
	// ---------------------------------------------------------------
		/**
		 * 
		 * @return the array of y cordinates for f'
		 */
		public double[] getyArrayDiff1() {
			return yArrayDiff1;
		}
	// ---------------------------------------------------------------
	/**
	 * 
	 * @return the array of y cordinates
	 */
	public double[] getyArray() {
		return yArray;
	}

	// ---------------------------------------------------------------
	/**
	 * 
	 * @param yArray
	 *            the array of y cordinates
	 */
	public void setyArray(double[] yArray) {
		this.yArray = yArray;
	}
	
}