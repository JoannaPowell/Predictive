package predictive;

/**
 * Creates objects consisting of a word and a signature that corresponds
 * to the position the characters of that string appear on a mobile phone
 * keypad.
 * @author Joanna Powell
 * @version 02/02/2017
 *
 */
public class WordSig implements Comparable<WordSig> {
	private String words;
	private String signature;
	
	/**
	 * Constructor for WordSig objects. The signature for each word is
	 * calculated using the wordToSignature method from PredictivePrototype.
	 * @param words the string of the WordSig object to be created
	 */
	public WordSig(String words) {
		this.words = words;
		this.signature = PredictivePrototype.wordToSignature(words);
	}
	
	/**
	 * Getter for the word.
	 * @return the words as a string
	 */
	public String getWords() {
		return words;
	}

	/**
	 * Getter for the signature.
	 * @return the signature as a string
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Setter for the word.
	 * @param words the new word to be set as a string
	 */
	public void setWords(String words) {
		this.words = words;
	}

	/**
	 * Setter for the signature.
	 * @param signature the new signature to be set as a string
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	/**
	 * toString method to convert WordSig objects to a string.
	 */
	public String toString() {
		return this.getWords() + this.getSignature();
	}

	/**
	 * Defines how WordSig objects will be compared when they are called
	 * with the sort method. WordSig objects are sorted by their signature
	 * character by character i.e. 1112 would be sorted before 112.
	 * @param ws the specified WordSig object to be compared
	 * @return a negative integer, zero, or a positive integer as this WordSig
	 * signature is less than, equal to, or greater than the specified WordSig
	 * signature.  
	 */
	public int compareTo(WordSig ws) {
		return this.signature.compareTo(ws.signature);
	}

}
