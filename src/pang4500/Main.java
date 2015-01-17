/**
 * 
 */
package pang4500;

import javax.swing.JFrame;

/**
 * @author Perlanie Panganiban Date: Monday, December 2, 2013 Student#:
 *         120574500 Email: pang4500@mylaurier.ca
 *         
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		GraphModel model = new GraphModel();
		GraphView graph = new GraphView(model);
		ControlView controls = new ControlView(model, graph);
		JFrame window = new JFrame("Expression Grapher");
		window.setContentPane(controls);// puts the view on to the frame
		window.setSize(500, 100);// sets size of window
		window.setResizable(false);// stops the window from being resized
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// makes the
																// window close
																// when x is
																// pressed
		window.setVisible(true);// makes the window visible

		
	}

}
