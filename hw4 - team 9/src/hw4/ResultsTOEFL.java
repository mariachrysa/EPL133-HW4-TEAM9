package hw4;

import java.io.*;
import java.util.*;

/**
 * 
 * ResultsTOEFL class contains the main method to run the program. It reads in
 * three text files containing sentences, builds semantic descriptors from them,
 * and then runs a similarity test using a text file with test questions. The
 * program prints out the success rate of the test, and the time it took to run
 * the test.
 * 
 * @author Maria Hadjichristoforou & Maria Chrysanthou
 * @version 26/04/2023
 * 
 */
public class ResultsTOEFL {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		long time = 0, endtime = 0;
		time = System.currentTimeMillis();
		System.out.println("Start time in seconds: " + ((double) (time) / 1000) + " In milliseconds: " + time);
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add("brown-train-sentences.txt");
		filenames.add("pg2600.txt");
		filenames.add("pg7178.txt");
		ArrayList<String[]> files = new ArrayList<>();
		files = FileHandler.get_sentence_lists_from_files(filenames);

		HashMap<String, HashMap<String, Integer>> descriptors = new HashMap<String, HashMap<String, Integer>>();
		descriptors = SemanticDescriptorBuilder.build_semantic_descriptors(files);
		SimilarityTester.run_similarity_test("similarity-test.txt", descriptors);
		endtime = System.currentTimeMillis();
		System.out.println("End time in seconds: " + (double) (endtime / 1000) + "  in milliseconds: " + endtime);
		System.out.println(
				"The time needed to invoke the method and the method return the ArrayList is:\nIn milliseconds: "
						+ (endtime - time) + "\nIn seconds: " + ((double) (endtime - time) / 1000));

	}

}