package _main;

import java.util.List;

/**
 * Used to calculate the exam total score.
 * @author Tim Hunter
 */
public class ExamCalculator {

	/**
	 * Calculate the total exam score by multiplying each row in the given sets, and summing all the calculations into the final score.
	 * @param weights The weight for each of the sections.
	 * @param scores The percent correct for each of the sections.
	 * @return The total exam score.
	 */
	public static double calculate(List<Double> weights, List<Double> scores) {
		if(weights.size() != scores.size()) {
			System.out.println("Paramaters of calculate function must have the same number of values.");
			return 0.0;
		}
		
		double total = 0.0;
		for (int i = 0; i < weights.size(); i++) {
			total += (weights.get(i) * scores.get(i));
		}
		return total;
	}
}
