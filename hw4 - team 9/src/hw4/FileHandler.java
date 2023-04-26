package hw4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The FileHandler class is responsible for handling files and extracting lists
 * of sentences and words from them.
 * 
 * @author Maria Hadjichristoforou & Maria Chrysanthou
 * @version 21/04/2023
 *
 */
public class FileHandler {
	/**
	 * This is function 2 of hw4 and it extracts lists of sentences and words from a
	 * list of filenames, using the SentenceParser class.
	 * 
	 * @param filenames a list of filenames to be read and parsed
	 * @return a list of lists, where each inner list represents a sentence and
	 *         contains the words of the sentence
	 * @throws IOException           if an I/O error occurs while reading the files
	 * @throws FileNotFoundException if the specified file is not found
	 */
	public static ArrayList<String[]> get_sentence_lists_from_files(ArrayList<String> filenames)
			throws IOException, FileNotFoundException {
		ArrayList<String[]> sentenceLists = new ArrayList<>();
		SentenceParser sentenceParser = new SentenceParser();
		try {
			for (String filename : filenames) {
				List<String> lines = readLinesFromFile(filename);
				for (String line : lines) {
					List<String[]> sentenceListsForLine = sentenceParser.getSentenceLists(line);
					for (String[] sentence : sentenceListsForLine) {
						sentenceLists.add(sentence);
					}
				}
			}
		} catch (FileNotFoundException fileException) {
			System.err.println("Could not open file.");
			System.exit(1);
		} catch (IOException eIO) {
			System.err.println("Files could not be processed properly");
			System.exit(0);
		}
		return sentenceLists;
	}
	

	/**
	 * Reads lines from a file and returns them as a list of strings.
	 * 
	 * @param filename the name of the file to be read
	 * @return a list of strings representing the lines of the file
	 * @throws IOException if an I/O error occurs while reading the file
	 */
	private static List<String> readLinesFromFile(String filename) throws IOException {
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (FileNotFoundException fileException) {
			System.err.println("Could not open file.");
			System.exit(1);
		} catch (IOException eIO) {
			System.err.println("Files could not be processed properly");
			System.exit(0);
		}
		return lines;
	}
}
