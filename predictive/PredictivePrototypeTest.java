package predictive;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Junit tests for the class PredictivePrototype class
 * @author Joanna Powell
 * @version 05/02/2017
 */

public class PredictivePrototypeTest {

	/**
	 * Tests the wordToSignature method.
	 */
	@Test
	public void wordToSignatureTest() {
		// input words
		String word1 = "green";
		String word2 = "HeLlo";
		String word3 = "H£llo";
		String word4 = " ";
		
		// output signatures
		String sig1 = "47336";
		String sig2 = "43556";
		String sig3 = "4 556";
		String sig4 = " ";
		
		// tests WordToSignature with a lower-case word with all alphabetic characters
		assertEquals(sig1, PredictivePrototype.wordToSignature(word1));
		// tests WordToSignature with a mixed lower and upper-case word with all alphabetic characters
		assertEquals(sig2, PredictivePrototype.wordToSignature(word2));
		// tests WordToSignature with a mixed lower and upper-case word with 1 non-alphabetic characters
		assertEquals(sig3, PredictivePrototype.wordToSignature(word3));
		// tests WordToSignature with a blank space
		assertEquals(sig4, PredictivePrototype.wordToSignature(word4));
	}
	
	/**
	 * Tests the signatureToWords method.
	 */
	@Test
	public void signatureToWordsTest() {
		// input signatures
		String sig1 = "25463";
		String sig2 = "843";
		String sig3 = "7765";
		
		// output words
		Set<String> words1 = new TreeSet<String>();
		words1.add("blind");
		Set<String> words2 = new TreeSet<String>();
		words2.add("the");
		words2.add("tif");
		words2.add("vid");
		Set<String> words3 = new TreeSet<String>();
		
		// tests signatureToWords with a signature that matches 1 word in the test file
		assertEquals(words1, PredictivePrototype.signatureToWords(sig1));
		// tests signatureToWords with a signature that matches 3 words in the test file
		assertEquals(words2, PredictivePrototype.signatureToWords(sig2));
		// tests signatureToWords with a signature that does not match any words in the test file
		assertEquals(words3, PredictivePrototype.signatureToWords(sig3));
	}
	
	/**
	 * Tests the isValidWord method.
	 */
	@Test
	public void isValidWordTest() {
		// words to test
		String word1 = "frog ";
		String word2 = "FroG";
		String word3 = "fr0g";
		String word4 = " ";
		
		// checks isValidWord with a lower-case word and a space is true
		assertTrue(PredictivePrototype.isValidWord(word1));
		// checks isValidWord with a lower and upper-case word is true
		assertTrue(PredictivePrototype.isValidWord(word2));
		// checks isValidWord with a lower-case word with a non-alphabetic character is false
		assertFalse(PredictivePrototype.isValidWord(word3));
		// checks isValidWord with a blank space is true
		assertTrue(PredictivePrototype.isValidWord(word4));
	}

}
