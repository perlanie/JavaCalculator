/**
 * 
 */
package pang4500;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Perlanie Panganiban Date: Monday, December 2, 2013 Student#:
 *         120574500 Email: pang4500@mylaurier.ca
 * 
 */
public class GraphView extends JComponent {

	// constants
	private GraphModel model = null;
	private JLabel functionLabel = new JLabel();
	
	

	// -------------------------------------------------------------------------------
	/**
	 * 
	 * @param model
	 *            The model where GraphView receives data
	 * 
	 */
	public GraphView(GraphModel model) {
		// TODO Auto-generated constructor stub
		super();
		this.LayoutView();
		this.setSize(500, 500);
		this.model = model;
		this.RegisterListeners();

	}

	// -------------------------------------------------------------------------------
	/**
	 * sets the layout of the graph view
	 */
	public void LayoutView() {
		JPanel functionPanel = new JPanel();
		this.setLayout(new BorderLayout());
		this.add(functionPanel, BorderLayout.SOUTH);

	}


	// -------------------------------------------------------------------------------
	/**
	 * Registers the listeners of the buttons and Property change listeners
	 */
	public void RegisterListeners() {
		this.model.addPropertyChangeListener(new DiagramListener());
	}

	// -------------------------------------------------------------------------------
	/**
	 * Inner class that displays the current state of the model.
	 */
	private class DiagramListener implements PropertyChangeListener {

		/**
		 * @Override
		 */
		public void propertyChange(final PropertyChangeEvent evt) {
			GraphView.this.repaint();
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * paints the function and the axis of the graph
	 */
	public void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;

		graphics2D.setColor(Color.BLACK);
		if (this.model == null)
			return;

		graphics2D.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		graphics2D.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

		// Plot points for f
		graphics2D.setColor(Color.BLUE);
		graphics2D.setStroke(new BasicStroke(2));
		graphics2D.drawString("f=" + this.model.getP().toString(), 5, 20);
		for (int i = 0; i < this.model.getSize(); i++) {

			Point2D point = new Point2D.Double(this.model.getxArray()[i],
					this.model.getyArray()[i]);

			Double x = ((point.getX()
					/ (Math.abs(this.model.getStart()) + Math.abs(this.model
							.getEnd())) * 2))
					* getWidth();
			int rX = x.intValue() + (getWidth() / 2);
			Double y = ((point.getY()
					/ (Math.abs(this.model.getYmax()) + Math.abs(this.model
							.getYmin())) * 2))
					* getHeight();
			int rY = (getHeight() / 2) - y.intValue();

			graphics2D.drawLine(rX, rY, rX, rY);
		}

		// Plot points for f'
		graphics2D.setColor(Color.GREEN);
		graphics2D.setStroke(new BasicStroke(2));
		graphics2D.drawString("f'=" + this.model.getP().diff().toString(), 5,
				30);
		for (int i = 0; i < this.model.getSize(); i++) {

			Point2D point = new Point2D.Double(this.model.getxArrayDiff1()[i],
					this.model.getyArrayDiff1()[i]);

			Double x = ((point.getX()
					/ (Math.abs(this.model.getStart()) + Math.abs(this.model
							.getEnd())) * 2))
					* getWidth();
			int rX = x.intValue() + (getWidth() / 2);
			Double y = ((point.getY()
					/ (Math.abs(this.model.getYmax()) + Math.abs(this.model
							.getYmin())) * 2))
					* getHeight();
			int rY = (getHeight() / 2) - y.intValue();

			graphics2D.drawLine(rX, rY, rX, rY);
		}

		// Plot points for f''
		graphics2D.setColor(Color.RED);
		graphics2D.setStroke(new BasicStroke(2));
		graphics2D.drawString("f''=" + this.model.getP().diff().diff().toString(), 5,
				40);
		for (int i = 0; i < this.model.getSize(); i++) {

			Point2D point = new Point2D.Double(this.model.getxArrayDiff2()[i],
					this.model.getyArrayDiff2()[i]);

			Double x = ((point.getX()
					/ (Math.abs(this.model.getStart()) + Math.abs(this.model
							.getEnd())) * 2))
					* getWidth();
			int rX = x.intValue() + (getWidth() / 2);
			Double y = ((point.getY()
					/ (Math.abs(this.model.getYmax()) + Math.abs(this.model
							.getYmin())) * 2))
					* getHeight();
			int rY = (getHeight() / 2) - y.intValue();

			graphics2D.drawLine(rX, rY, rX, rY);
		}

	}

	// -------------------------------------------------------------------------------
	/**
	 * 
	 * @author Panganiban
	 * 
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// -------------------------------------------------------------------------------
}
