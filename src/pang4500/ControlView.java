/**
 * 
 */
package pang4500;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Perlanie Panganiban Date: Monday, December 2, 2013 Student#:
 *         120574500 Email: pang4500@mylaurier.ca
 * 
 */
public class ControlView extends JComponent {
	// sets labels, buttons, text fields, GraphModel, JFrame and GraphView
	private JButton plot = new JButton("Plot");
	private JLabel function = new JLabel("Function:");
	private JLabel variable = new JLabel("Variable:");
	private JLabel start = new JLabel("Start:");
	private JLabel end = new JLabel("End:");
	private JTextField textFunction = new JTextField();
	private JTextField textVariable = new JTextField();
	private JTextField textStart = new JTextField();
	private JTextField textEnd = new JTextField();
	private JTextField errors = new JTextField();
	private GraphModel model = null;
	private JFrame graphWindow = new JFrame("Graph Window");
	private GraphView graph = null;
	
	
	// -------------------------------------------------------------------------------
	/**
	 * 
	 * @param model
	 *            The Model where the ControlView receives data
	 * @param graph
	 *            The graph window which the ControlView graphs the polynomial
	 */
	public ControlView(GraphModel model, GraphView graph) {
		// TODO Auto-generated constructor stub
		super();
		this.LayoutView();
		this.model = model;
		this.RegisterListeners();

		this.graph = graph;
		this.graph.setVisible(true);
		graphWindow.setContentPane(graph);// puts the view on to the frame
		graphWindow.setSize(505, 520);// sets size of window
		graphWindow.setLocation(0, 115);
		graphWindow.setBackground(Color.WHITE);
		graphWindow.setResizable(true);// stops the window from being resized
		graphWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// makes the
																	// window
																	// close
																	// when x is
																	// pressed
		graphWindow.setVisible(false);// makes the window visible

	}
	
	
	// -------------------------------------------------------------------------------
	/**
	 * Initializes the control window view
	 */
	public void LayoutView() {

		JPanel functionPanel = new JPanel();
		functionPanel.setLayout(new GridLayout(2, 1));
		functionPanel.add(this.function);
		functionPanel.add(this.textFunction);

		JPanel variablePanel = new JPanel();
		variablePanel.setLayout(new GridLayout(2, 1));
		variablePanel.add(this.variable);
		variablePanel.add(this.textVariable);

		JPanel startPanel = new JPanel();
		startPanel.setLayout(new GridLayout(2, 1));
		startPanel.add(this.start);
		startPanel.add(this.textStart);

		JPanel endPanel = new JPanel();
		endPanel.setLayout(new GridLayout(2, 1));
		endPanel.add(this.end);
		endPanel.add(this.textEnd);

		JPanel plotPanel = new JPanel();
		JLabel blank = new JLabel("             ");
		plotPanel.setLayout(new BorderLayout());
		plotPanel.add(blank, BorderLayout.NORTH);
		plotPanel.add(this.plot, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 5));
		controlPanel.add(functionPanel);
		controlPanel.add(variablePanel);
		controlPanel.add(startPanel);
		controlPanel.add(endPanel);
		controlPanel.add(plotPanel);

		errors.setEditable(false);

		this.setLayout(new BorderLayout());
		this.add(controlPanel, BorderLayout.CENTER);
		this.add(errors, BorderLayout.SOUTH);

	}
	
	
	
	// -------------------------------------------------------------------------------
	/**
	 * Assigns an actionlistener when a button is pressed
	 */
	public void RegisterListeners() {
		this.plot.addActionListener(new ButtonListener());
	}
	
	
	
	// -------------------------------------------------------------------------------
	/**
	 * 
	 * @author Panganiban
	 * 
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String buttonPlot = ((JButton) e.getSource()).getText();

			if (buttonPlot.equals("Plot")) {
				String polynomial = textFunction.getText();
				
				String start = textStart.getText();
				String end = textEnd.getText();

				String variable = textVariable.getText();

				try {
					model.setEnd(end);
					model.setStart(start);
					Poly p = new Poly(polynomial);
					if (variable.equals(p.getVariable())) {
						model.setP(p);
						model.GraphProcess();
						errors.setText("Successfully Ploted!");
						errors.setForeground(Color.GREEN);
						graphWindow.setVisible(true);// makes the window visible
					} else {
						errors.setText("Error: Variable in Function does not match variable.");
						errors.setForeground(Color.RED);
					}

				} catch (NumberFormatException e2) {
					errors.setText(e2.getMessage());
					errors.setForeground(Color.RED);
		
				} catch (PolyException e1) {
					// TODO Auto-generated catch block
					errors.setText(e1.getMessage());
					errors.setForeground(Color.RED);
				} catch (OperatorException e1) {
					// TODO Auto-generated catch block
					errors.setText(e1.getMessage());
					errors.setForeground(Color.RED);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					errors.setText(e1.getMessage());
					e1.printStackTrace();
					errors.setForeground(Color.RED);
				}
			}
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
