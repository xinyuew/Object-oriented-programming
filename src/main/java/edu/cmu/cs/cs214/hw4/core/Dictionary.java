package edu.cmu.cs.cs214.hw4.core;

import java.io.FileInputStream;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represent the dictionary used in scrabble
 * @author xinyuewu
 *
 */
public class Dictionary {
	private Map<String,HashSet<String>> dictionary;
	private String fileName;

	/**
	 * Constructor 
	 * @param file is the name of the file which contains all words in dictionary
	 */
	public Dictionary(String file) {
		fileName = file;
		dictionary = new HashMap<String,HashSet<String>>();
		initialize();
	}

	/**
	 * initialize dictionary
	 */
	public void initialize() {
		try {
			FileInputStream in = new FileInputStream(fileName);
			Scanner scanner = new Scanner(in);

			while (scanner.hasNextLine()) {
				String word = scanner.nextLine();
				String start = word.substring(0, 0);
				if(dictionary.containsKey(start)){
					dictionary.get(start).add(word);
				} else {
					HashSet<String> words = new HashSet<String>();
					words.add(word);
					dictionary.put(start, words);
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if this word is in dictionary
	 * @param word is word need to be checked
	 * @return true if this word is in dictionary
	 */
	public boolean isInDictionary(String word) {

		String start = word.substring(0, 0);
		String lowerWord = word.toLowerCase();
		return dictionary.get(start).contains(lowerWord);
	}
}
