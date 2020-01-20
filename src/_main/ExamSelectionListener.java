package _main;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Item listener for selection changes on the exam combo-box.
 * @author Tim Hunter
 */
public class ExamSelectionListener implements ItemListener {

	/** The input panel to update fields */
	private JPanel inputPanel;
	/** The pulled data of a selected exam */
	private DataParser dataParser;
	/** Input fields genereted using the pulled section data */
	private List<JTextField> fields;
	
	/**
	 * Listens for changes to a combo-box and generates fields in the given panel.
	 * @param inputPanel The panel to add input fields to.
	 */
	public ExamSelectionListener(JPanel inputPanel) {
		this.inputPanel = inputPanel;
	}
	
	/**
	 * Exam data container of the chosen exam.
	 * @return The exam data.
	 */
	public DataParser getDataParser() {
		return dataParser;
	}
	
	/**
	 * Fields generated using the exam section data.
	 * @return The input fields for an exam's sections.
	 */
	public List<JTextField> getFields() {
		return fields;
	}
	
	@Override
    public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			//Retrieve the data of interest from the selection and tell the ExamResultListener
			String value = (String) event.getItem();
			dataParser = new DataParser(value + ".json");
			
			
			//Clear out current entries and set the panel sizing to match new batch of selections
			inputPanel.removeAll();
			inputPanel.setPreferredSize(new Dimension(ExamCalculatorGUI.width, 31 * dataParser.getSectionNames().size()));
			
			//Loops through the sections names from the data and create the input fields
			fields = new ArrayList<>();  
			for(String section : dataParser.getSectionNames()) {
				JPanel sectionPanel = new JPanel();
				Dimension sectionSize = new Dimension(ExamCalculatorGUI.width, 25);
				sectionPanel.setPreferredSize(sectionSize);
				JLabel sectionLabel = new JLabel(section);
				JTextField sectionInput = new JTextField("", 3);
				fields.add(sectionInput);
				
				sectionPanel.add(sectionLabel);
				sectionPanel.add(sectionInput);
				inputPanel.add(sectionPanel);
			}
			
			//Redraw the new field inputs
			inputPanel.repaint();
			inputPanel.revalidate();
		}
	} 
	
}
