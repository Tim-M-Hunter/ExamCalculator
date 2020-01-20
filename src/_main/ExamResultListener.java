package _main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Action listener for the calculation button.
 * @author Tim Hunter
 */
public class ExamResultListener implements ActionListener {

	/** The listener for the exam combo-box */
	private ExamSelectionListener selectionListener;
	/** The JLabel to display the calculation in */
	private JLabel resultDisplay;
	/** The final exam score based on the input fields */
	private double calculation;
	
	/**
	 * Listens for the button press to calculate the inputs fields and display the results in a set JLabel. <br/>
	 * Be sure to set a JLabel using the setResultDisplay function to see the displayed results.
	 * @param selectionListener The listener for the exam combo-box.
	 */
	public ExamResultListener(ExamSelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}
	
	/**
	 * Set the JLabel to display to calculation in.
	 * @param resultDisplay The JLabel to display the results.
	 */
	public void setResultDisplay(JLabel resultDisplay) {
		this.resultDisplay = resultDisplay;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Loop through fields and build list of inputs
		List<Double> values = new ArrayList<>();
		for(JTextField field : selectionListener.getFields()) {
			if(field.getText().isBlank()) {
				values.add(0.0);
			} else {
				double value = Double.parseDouble(field.getText());
				if(value > 1) {
					value = value / 100;
				}
				values.add(value);
			}
		}
		//Perform calculation using inputs
		calculation = ExamCalculator.calculate(selectionListener.getDataParser().getWeights(), values) * 100;
		updateResultDisplay();
	}
	
	/**
	 * If a display field was set, update the display of the calculation.
	 */
	public void updateResultDisplay() {
		if(resultDisplay != null) {
			resultDisplay.setText(String.format("%.2f", calculation)  + "%");
		}
	}

}
