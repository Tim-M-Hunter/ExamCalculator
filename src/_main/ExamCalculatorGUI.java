package _main;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Draws the main body components of the application.
 * @author Tim Hunter
 */
public class ExamCalculatorGUI extends JFrame {

	/** Default app width */
	public static final int width = 380;
	/** Default app height */
	public static final int height = 600;
	
	/** Unique identifier for the app version */
	private static final long serialVersionUID = 6619249498121943470L;
	
	/** Container for exam selection menu */
	private JPanel selectionPanel;
	/** Container for the input fields */
	private JPanel inputPanel;
	/** Container for the results of the calculation */
	private JPanel resultPanel;
	
	/** Listener for exam combo-box selection */
	private ExamSelectionListener selectionListener;
	/** Listener for calculation button */
	private ExamResultListener resultListener;
	
	/**
	 * Creates the application interface.
	 */
	public ExamCalculatorGUI() {
		//Application settings
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setLayout(new BorderLayout());
		
		//Create sections of the app
		selectionPanel = new JPanel();
		inputPanel = new JPanel();
		resultPanel = new JPanel();
		
		//Declare action listeners
		selectionListener = new ExamSelectionListener(inputPanel);
		resultListener = new ExamResultListener(selectionListener);
		
		//Fill in the static selection and result sections
		buildSelectionPanel();
		buildResultPanel();
		
		//Make the dynamic input section able to scroll
		JScrollPane scrollInput = new JScrollPane(inputPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Add the sections to the main application frame
		this.getContentPane().add(selectionPanel, BorderLayout.PAGE_START);
		this.getContentPane().add(scrollInput, BorderLayout.CENTER);
		this.getContentPane().add(resultPanel, BorderLayout.PAGE_END);
		
		this.setVisible(true);
	}
	
	/**
	 * Fills in the selection section.
	 */
	private void buildSelectionPanel() {
		JLabel examLabel = new JLabel("Select Exam: ");
		selectionPanel.add(examLabel);
		
		//Populate combo box with file names in _DataName.txt file
		JComboBox<String> examList = new JComboBox<>(getFileNames());
		examList.setSelectedIndex(-1); //Make initial selection blank
		examList.addItemListener(selectionListener);
		
		selectionPanel.add(examList);
	}
	
	/**
	 * Fills in the result section.
	 */
	private void buildResultPanel() {
		JPanel valuePanel = new JPanel();
		JLabel label = new JLabel("Percentage: ");
		JLabel value = new JLabel();
		valuePanel.add(label);
		valuePanel.add(value);
		
		resultListener.setResultDisplay(value);
		
		JButton button = new JButton("Calculate");
		button.addActionListener(resultListener);
		
		resultPanel.add(button);
		resultPanel.add(valuePanel);
	}
	
	/**
	 * Retrieves the names of the files containing the exam data.
	 * Cited code: https://stackoverflow.com/questions/16953897/how-to-read-a-text-file-inside-a-jar
	 * @return List of file names.
	 */
	private String[] getFileNames(){
		List<String> list = new ArrayList<>();
		try {
			InputStream inputStream = this.getClass().getResourceAsStream("/data/_DataNames.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			for(String line; (line = reader.readLine()) != null;) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] names = new String[list.size()];
		return list.toArray(names);
	}

}
