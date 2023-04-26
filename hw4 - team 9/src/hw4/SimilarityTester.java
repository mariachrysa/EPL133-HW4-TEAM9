package hw4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * 
 * A class that tests the semantic similarity between words using the cosine
 * similarity of their respective semantic descriptor vectors. It also
 * calculates the success rate of the test by comparing the predicted most
 * similar word to the correct one.
 * 
 * @author Maria Hadjichristoforou & Maria Chrysanthou
 * @version 26/04/2023
 * 
 */
public class SimilarityTester {
	/**
	 * 
	 * This is function 4 of the hw and basically finds the most similar word from
	 * the given choices based on their semantic descriptors.
	 * 
	 * @param word                 the input word
	 * @param choices              an array of words to choose from
	 * @param semantic_descriptors a HashMap containing the semantic descriptors of
	 *                             words
	 * @return the most similar word from the given choices
	 */
	public static String most_similar_word(String word, String[] choices,
			HashMap<String, HashMap<String, Integer>> semantic_descriptors) {
		double max = -1.0;
		double similarity = 0.0;
		int index = 0;

		for (int i = 0; i < choices.length; i++) {

			if (semantic_descriptors.containsKey(word)) {
				similarity = cosineSimilarity(semantic_descriptors.get(choices[i]), semantic_descriptors.get(word));
			} else
				similarity = -1;

			if (similarity > max) {
				max = similarity;
				index = i;
			}
		}
		return choices[index];
	}

	/**
	 * 
	 * calculates the cosine similarity between two vectors represented by HashMaps.
	 * 
	 * @param vec1 the first vector represented by a HashMap
	 * @param vec2 the second vector represented by a HashMap
	 * @return the cosine similarity between the two vectors
	 */
	public static double cosineSimilarity(HashMap<String, Integer> vec1, HashMap<String, Integer> vec2) {
		if (vec1 == null || vec2 == null || vec1.isEmpty() || vec2.isEmpty()) {
			return -1;
		}
		double dot_product = 0.0;
		for (String w : vec1.keySet()) {
			if (vec2.containsKey(w)) {
				dot_product += vec1.get(w) * vec2.get(w);
			}
		}
		return dot_product / (norm(vec1) * norm(vec2));
	}

	/**
	 * 
	 * calculates the norm of a vector represented by a HashMap.
	 * 
	 * @param vec the vector represented by a HashMap
	 * @return the norm of the vector
	 */
	private static double norm(HashMap<String, Integer> vec) {
		double sum_of_squares = 0.0;

		for (String key : vec.keySet()) {
			int n = vec.get(key);
			sum_of_squares += n * n;
		}
		return Math.sqrt(sum_of_squares);
	}

	/**
	 * 
	 * runs a similarity test on a given file and outputs the results to a file
	 * named "results.txt".
	 * 
	 * @param filename    the name of the file containing the similarity test
	 *                    questions
	 * @param descriptors a HashMap containing the semantic descriptors of words
	 */
	public static void run_similarity_test(String filename, HashMap<String, HashMap<String, Integer>> descriptors) {
		int correct = 0, questions = 0;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = null;
			BufferedWriter output = new BufferedWriter(new FileWriter("results.txt"));
			while ((line = reader.readLine()) != null) {
				String[] parts =  line.split(" ");

				if (parts.length > 0) {
					questions++;
					String[] choices = new String[parts.length - 2];

					for (int i = 0; i < choices.length; i++)
						choices[i] = parts[i + 2];

					String guess = most_similar_word(parts[0], choices, descriptors);
					if (guess.equals(parts[1]))
						correct++;
					if (!guess.equals("-1"))
						output.write("Result of  Q" + questions + " by the program is " + guess
								+ " and the correct answer is " + parts[1] + "\n");
					else
						output.write("Result of Q" + questions + " by the program is " + guess
								+ ". The programme doesn't know some words." + "\n");

				}
			}
			reader.close();

			double successRate = ((double) correct / questions) * 100;

			output.write("Total questions: " + questions + "\n"); // each line of the txt file is the question basically
			output.write("Correct answers: " + correct + "\n");
			output.write("Success rate: " + successRate + "% \n");
			output.write("\nOur programme learned " + descriptors.size() + " different words.");
			output.close();

		} catch (FileNotFoundException fileException) {
			System.out.println("The file could not be found or opened.");

		} catch (IOException e) {
			System.out.println("Problems reading on the file:");
		}
	}
}
