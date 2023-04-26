package hw4;

import java.util.ArrayList;
import java.util.List;

/**
 * The SentenceParser class is responsible for parsing a text into lists of
 * sentences and words.
 * 
 * @author Maria Hadjichristoforou & Maria Chrysanthou
 * @version 22/04/23
 *
 */
public class SentenceParser {

	/**
	 * This is basically function 1 of the homework, in which returns a list of
	 * lists, where each inner list represents a sentence and contains the words of
	 * the sentence. Sentences are split based on common punctuation marks such as
	 * period, exclamation mark and question mark.
	 * 
	 * @param text the txt to be parsed
	 * @return a list of lists, where each inner list represents a sentence and
	 *         contains the words of the sentence
	 */
	
	public ArrayList<String[]> getSentenceLists(String text) {
		ArrayList<String[]> sentenceLists = new ArrayList<String[]>();

		String[] sentences = text.split("[.?!]");
		for (String sentence : sentences) {
			sentence = sentence.trim();
			if (!sentence.isEmpty()) {
				List<String> words = getWords(sentence);
				words.replaceAll(String::toLowerCase);
				String[] sentenceArray = new String[words.size()];
				for (int i = 0; i < words.size(); i++) {
					sentenceArray[i] = words.get(i);
				}
				sentenceLists.add(sentenceArray);
			}
		}
		return sentenceLists;

	}

	/**
	 * Returns a list of words, given a sentence as input. Words are split based on
	 * common punctuation marks such as dash, comma, apostrophe, colon, semicolon,
	 * exclamation mark and question mark.
	 * 
	 * @param sentence the sentence to be parsed
	 * @return a list of words contained in the sentence
	 */
	public List<String> getWords(String sentence) {
		List<String> words = new ArrayList<>();
		String[] tokens = sentence.split("[\\--\\-\\,\\'\\:\\;\\!\\?\\.]");
		for (String token : tokens) {
			token = token.toLowerCase();
			if (!token.isEmpty()) {
				words.add(token);
			}
		}
		return words;
	}
}
