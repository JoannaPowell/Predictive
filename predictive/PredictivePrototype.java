package predictive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A prototype of a predictive text system that can convert a word to
 * a string of numbers based on the position that each letter of the
 * word appears on a mobile phone keypad. It can also return a list of
 * all words that match a given signature.
 * 
 * The dictionary is not stored in this java program as static methods
 * are used ******************8 
 * @author Joanna Powell
 * @version 02/02/2017
 *
 */
public class PredictivePrototype {
	
	/**
	 * Converts a word to a signature consisting of a string of integers.
	 * The position of each character on the keypad of a mobile phone
	 * defines the identity of the string. Whether a character is upper or
	 * lower-case does not make a difference to the number it is mapped to.
	 * The mappings are as follows: 
	 * a, b and c map to 2.
	 * d, e and f map to 3.
	 * g, h and i map to 4.
	 * j, k and l map to 5.
	 * m, n and o map to 6.
	 * p, q, r and s map to 7.
	 * t, u and v map to 8.
	 * w, x, y and z map to 9.
	 * Any non-alphabetic characters are mapped to a blank space.
	 * 
	 * To build the signature, a string buffer rather than a string is used.
	 * A string is immutable, so once created, it cannot be edited. Because
	 * of this, a new string object has to be created each time the string
	 * is changed. A string buffer deals with the concatenation of strings
	 * much more efficiently.
	 * 
	 * @param word the string to be converted to a signature
	 * @return the signature as a string
	 */
//	public static String wordToSignature(String word) {
//		String input = word;
//		StringBuffer signature = new StringBuffer();
//		for (int i = 0; i < input.length(); i++) {
//			if (Pattern.matches("[aAbBcC]", input.subSequence(i, i+1))) {
//				signature.append('2');
//			}
//			else if (Pattern.matches("[dDeEfF]", input.subSequence(i, i+1))) {
//				signature.append('3');
//			}
//			else if (Pattern.matches("[gGhHiI]", input.subSequence(i, i+1)))  {
//				signature.append('4');
//			}
//			else if (Pattern.matches("[jJkKlL]", input.subSequence(i, i+1))) {
//				signature.append('5');
//			}
//			else if (Pattern.matches("[mMnNoO]", input.subSequence(i, i+1))) {
//				signature.append('6');
//			}
//			else if (Pattern.matches("[pPqQrRsS]", input.subSequence(i, i+1))) {
//				signature.append('7');
//			}
//			else if (Pattern.matches("[tTuUvV]", input.subSequence(i, i+1))) {
//				signature.append('8');
//			}
//			else if (Pattern.matches("[wWxXyYzZ]", input.subSequence(i, i+1))) {
//				signature.append('9');
//			}
//			else {
//				signature.append(" ");
//			}
//		}
//		return signature.toString();
//	}
	
	public static String wordToSignature(String word) {
		StringBuffer signature = new StringBuffer();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == 'a' || word.charAt(i) == 'b' || word.charAt(i) == 'c' || word.charAt(i) == 'A'
					|| word.charAt(i) == 'B' || word.charAt(i) == 'C') {
				signature.append('2');
			}
			else if (word.charAt(i) == 'd' || word.charAt(i) == 'e' || word.charAt(i) == 'f' || word.charAt(i) == 'D'
					|| word.charAt(i) == 'E' || word.charAt(i) == 'F') {
				signature.append('3');
			}
			else if (word.charAt(i) == 'g' || word.charAt(i) == 'h' || word.charAt(i) == 'i' || word.charAt(i) == 'G'
					|| word.charAt(i) == 'H' || word.charAt(i) == 'I') {
				signature.append('4');
			}
			else if (word.charAt(i) == 'j' || word.charAt(i) == 'k' || word.charAt(i) == 'l' || word.charAt(i) == 'J'
					|| word.charAt(i) == 'K' || word.charAt(i) == 'L') {
				signature.append('5');
			}
			else if (word.charAt(i) == 'm' || word.charAt(i) == 'n' || word.charAt(i) == 'o' || word.charAt(i) == 'M'
					|| word.charAt(i) == 'N' || word.charAt(i) == 'O') {
				signature.append('6');
			}
			else if (word.charAt(i) == 'p' || word.charAt(i) == 'q' || word.charAt(i) == 'r' || word.charAt(i) == 's'
					|| word.charAt(i) == 'P' || word.charAt(i) == 'Q' || word.charAt(i) == 'R'
					|| word.charAt(i) == 'S') {
				signature.append('7');
			}
			else if (word.charAt(i) == 't' || word.charAt(i) == 'u' || word.charAt(i) == 'v' || word.charAt(i) == 'T'
					|| word.charAt(i) == 'U' || word.charAt(i) == 'V') {
				signature.append('8');
			}
			else if (word.charAt(i) == 'w' || word.charAt(i) == 'x' || word.charAt(i) == 'y' || word.charAt(i) == 'z'
					|| word.charAt(i) == 'W' || word.charAt(i) == 'X' || word.charAt(i) == 'Y'
					|| word.charAt(i) == 'Z') {
				signature.append('9');
			}
			else {
				signature.append(" ");
			}
		}
		return signature.toString();
	}
	
	/**
	 * Takes a signature consisting of a string of numbers and returns all
	 * the words that have that signature identity in whichever list of words
	 * is linked to the buffered reader.
	 * 
	 * The method calculates the signature of each word in the file and checks
	 * for any signatures that match the given signature. Only words that do
	 * not contain any non-alphabetic characters are considered.
	 * 
	 * @param signature a string of numbers to match to the signatures of the words in the file
	 * @return all words whose signatures match the signature the method is called with as a 
	 * set of lower-case strings with no duplicates
	 */
	public static Set<String> signatureToWords(String signature) {
		Set<String> matchedSignatures = new TreeSet<String>();
		String readString;
		try {
			BufferedReader in = new BufferedReader(
					new FileReader("words.txt"));
			while ((readString = in.readLine()) != null) {
				if (isValidWord(readString)) { 
					if (wordToSignature(readString).equals(signature)) {
						matchedSignatures.add(readString.toLowerCase());
					}
				}
			}
			in.close();
		} 
		catch (IOException e) {
			System.out.println("File not found");
		}
		return matchedSignatures;
	}
	
//	public static Set<String> signatureToWords(String signature) {
//		Set<String> matchedSignatures = new TreeSet<String>();
//		String readString;
//		
//		try {
//			Scanner s = new Scanner(new File("words.txt"));
//			while (s.hasNextLine()) {
//				readString = s.nextLine();
//				if (isValidWord(readString)) { 
//					if (wordToSignature(readString).equals(signature)) {
//						matchedSignatures.add(readString.toLowerCase());
//					}
//				}
//			}
//			s.close();
//		} 
//		catch (IOException e) {
//			System.out.println("File not found");
//		}
//		return matchedSignatures;
//	}
//	
	/**
	 * Checks if a word contains any non-alphabetic characters. A blank space is accepted
	 * as a normal character so that any words with spaces after them will be considered in
	 * signatureToWords.
	 * @param word the string to be checked
	 * @return true if the word contains all alphabetic characters and false if it
	 * contains any non-alphabetic characters
	 */
	public static boolean isValidWord(String word) {
		Pattern p = Pattern.compile("[a-zA-Z ]+");
		Matcher m = p.matcher(word);
		return m.matches();
	}
	
	public static void main(String[] args) {
//		String test = "hello";
//		System.out.println(wordToSignature(test));
		System.out.println(signatureToWords("5376"));			
	}

}
