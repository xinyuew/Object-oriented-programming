package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * test dictionary class
 * @author xinyuewu
 *
 */
public class DictionaryTest {
	private Dictionary dictionary;
	private static final String FILE_NAME = "src/main/resources/words.txt";
	
	/**
	 * before each test, execute setUp
	 */
	@Before
	public void setUp(){
		dictionary = new Dictionary(FILE_NAME);
		dictionary.initialize();
	}
	
	/**
	 * test isInDictionary class
	 */
	@Test
	public void testIsInDictionary(){
		String s = "a";
		assertFalse(dictionary.isInDictionary(s));
		s = "dictionary";
		assertTrue(dictionary.isInDictionary(s));
	}
}
