package hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * The SemanticDescriptorBuilder class is responsible for building semantic
 * descriptors from a list of sentences. A semantic descriptor is a mapping from
 * a word to a mapping from other words to the number of times they appear in
 * the same sentence.
 * 
 * @author Maria Hadjichristoforou & Maria Chrysanthou
 * @version 21/04/2023
 */
public class SemanticDescriptorBuilder {
	/**
	 * This is function 3 of hw4 and builds a semantic descriptor from a list of
	 * sentences.
	 *
	 * @param sentences a list of lists of strings, where each inner list contains
	 *                  the words of a sentence
	 * @return a hash map mapping each word to a mapping from other words to the
	 *         number of times they appear in the same sentence
	 */
	public static HashMap<String, HashMap<String, Integer>> build_semantic_descriptors(ArrayList<String[]> sentences) {
		HashMap<String, HashMap<String, Integer>> descriptor = new HashMap<String, HashMap<String, Integer>>();

		for (String[] sentence : sentences) {
			HashSet<String> seenWords = new HashSet<>();

			for (String word : sentence) {
				if (!descriptor.containsKey(word)) {
					descriptor.put(word, new HashMap<String, Integer>());
				}
				if (!seenWords.contains(word)) {
					seenWords.add(word);
					HashMap<String, Integer> wordDescriptor = descriptor.get(word);

					for (String otherWord : sentence) {
						if (!otherWord.equals(word)) {
							int count = wordDescriptor.getOrDefault(otherWord, 0);
							wordDescriptor.put(otherWord, count + 1);
						}
					}
				}
			}
		}

		return descriptor;
	}


}
