package _main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Used to pull exam data from JSON files and store them locally.
 * @author thunter
 */
public class DataParser {
	
	/** The name of the exam */
	private String examName;
	/** The names of the exam sections */
	private List<String> sectionNames;
	/** The weighted value of each section */
	private List<Double> weights;
	
	/**
	 * Given file name is used to search for a JSON files in the data folder.
	 * @param fileName The name of the JSON file to pull data from.
	 */
	public DataParser(String fileName) {
		try{
			//Pull data from the data folder
			String path = "/data/" + fileName;
			InputStream stream = this.getClass().getResourceAsStream(path);
			InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
			
			//Set data to local variables using Simple JSON library
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(reader);
			JSONArray jsonArray;
			
			examName = (String) obj.get("exam");
			
			jsonArray = (JSONArray) obj.get("sections");
			sectionNames = new ArrayList<>();
			for(Object value : jsonArray) {
				sectionNames.add((String) value);
			}
			
			jsonArray = (JSONArray) obj.get("weights");
			weights = new ArrayList<>();
			for(Object value : jsonArray) {
				weights.add((Double) value);
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The name of the exam.
	 * @return The exam name.
	 */
	public String getExamName() {
		return examName;
	}
	
	/**
	 * The name of each section in the exam, in the same order as the weights.
	 * @return The section names.
	 */
	public List<String> getSectionNames() {
		return sectionNames;
	}
	
	/**
	 * The weighted value of each section, in the same order as the names.
	 * @return The weighted values.
	 */
	public List<Double> getWeights() {
		return weights;
	}
}
