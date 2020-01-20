package _main;

/**
 * Main method class to start the application.
 * @author Tim Hunter
 */
public class StartProgram {

	public static void main(String[] args) {
		//Create a thread to run the application
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ExamCalculatorGUI();
			}
		});
	}
}
