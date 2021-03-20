package Com.BCG.Assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountWords {

	public static void main(String[] args) {
		int totalUniqueWords = 0;
		// provide your input string array here
		// comment below 2 lines in case you want to read input from text file
		// with comma separated inputs
		String[] input = { "eat", "ate", "tea", "Java", "avja", "apple" };
		totalUniqueWords = sortWOrds(input);

		// uncomment below line in case .txt file with comma separated words is
		// used as the input
		// totalUniqueWords = sortWOrds(readInputFromFile("testInput.txt"));

		System.out.println("Total count of number of strings which are similar but jumbled is : " + totalUniqueWords);
	}

	/**
	 * @param input:
	 *            Array of String
	 * @return int : Total count of number of strings which are similar but
	 *         jumbled
	 * 
	 *         This function takes Array of String as input and returns the
	 *         total count of number of strings which are similar but jumbled
	 */
	/**
	 * @param input
	 *            Map with alphabets of word as key and count of words built
	 *            with the key as value
	 * @return
	 */
	public static int sortWOrds(String[] input) {
		// Create a map to store the words made of same letters and its count
		HashMap<String, Integer> sortedInputCounts = new HashMap<>();
		// Create a map to store the words made of same letters and the actual
		// word , this might not be needed if just count of jumbled words are
		// needed .This is just used to provide details of actual words and its
		// group.
		HashMap<String, String> sortedInputAndWOrds = new HashMap<>();
		// iterate through each word in the input array
		for (String word : input) {
			// convert the word (String) to character array and sort
			char[] wordArray = word.trim().toLowerCase().toCharArray();
			Arrays.sort(wordArray);
			String sortedWord = String.valueOf(wordArray);

			// Add the sorted word as a key to a map ,when more than one word
			// has same letters update the count in the value
			sortedInputCounts.put(sortedWord, sortedInputCounts.getOrDefault(sortedWord, 0) + 1);
			// Add the actual word as key and its alphabets which is a sorted
			// word as value
			// this would be used to provide more info on which words are
			// similar
			sortedInputAndWOrds.put(word, sortedWord);

		}

		// Call print method to output more info
		printWordsAndCounts(sortedInputCounts, sortedInputAndWOrds);
		return sortedInputCounts.size();
	}

	/**
	 * @param mapWIthCount
	 * @param mapWithWordComb
	 */
	public static void printWordsAndCounts(HashMap<String, Integer> mapWIthCount,
			HashMap<String, String> mapWithWordComb) {
		for (Map.Entry<String, Integer> wordCounts : mapWIthCount.entrySet()) {
			System.out.println(String.format("Number of words made up of letters : %s is : %s ", wordCounts.getKey(),
					wordCounts.getValue().toString()));
			for (Map.Entry<String, String> groupedWords : mapWithWordComb.entrySet()) {
				if (wordCounts.getKey().equals(groupedWords.getValue())) {
					System.out.println(String.format("group of words with letters %s is %s", wordCounts.getKey(),
							groupedWords.getKey()));
				}
			}
			System.out.println("");

		}
		System.out.println("################################################################");
	}

	/**
	 * @param file
	 * @return Array of words from text file as input file
	 */
	public static String[] readInputFromFile(String file) {
		StringBuilder input = new StringBuilder("");
		String st;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(file)));

			while (null != (st = reader.readLine())) {
				input.append(st);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// split the input read from file with "," and generate an array of
		// string input of words
		return input.toString().split(",");
	}

}
