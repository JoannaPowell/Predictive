package predictive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * A more efficient implementation of the PredictivePrototype class
 * which implements the dictionary interface.
 * 
 * Objects of this class consist of a path to the text file containing
 * the words to be stored as the dictionary and the stored list of words
 * as WordSig objects. When a list dictionary object is created, the
 * readDictionary method creates a new WordSig object for each word contained
 * in the text file and adds this to an array list. The WordSigs are able to
 * be sorted by signature as they contain a compareTo method, so that words 
 * with the same signature are grouped together.
 * 
 * Because the dictionary is stored as an instance variable, the signatureToWords
 * method can find matching words more efficiently. Binary search finds any of
 * the matching signatures in log(n) time, then the signatures either side of
 * this are checked to find the rest of the matching signatures.
 * @author Joanna Powell
 * @version 05/02/2017
 *
 */
public class ListDictionary implements Dictionary {
	private String pathToDictionary;
	private ArrayList<WordSig> storedList;
	
	/**
	 * Constructor for a list dictionary object.
	 * @param pathToDictionary the path where the text file containing the words to
	 * be stored in the dictionary can be found
	 */
	public ListDictionary(String pathToDictionary) {
		this.pathToDictionary = pathToDictionary;
		this.storedList = readDictionary(pathToDictionary);
	}
	
	/**
	 * Getter for the path to dictionary.
	 * @return the path to dictionary as a string
	 */
	public String getPathToDictionary() {
		return pathToDictionary;
	}

	/**
	 * Getter for the stored dictionary list.
	 * @return the stores list as an array list of WordSigs
	 */
	public ArrayList<WordSig> getStoredList() {
		return storedList;
	}

	/**
	 * Setter for the path to dictionary.
	 * @param pathToDictionary the new path to dictionary to be set
	 */
	public void setPathToDictionary(String pathToDictionary) {
		this.pathToDictionary = pathToDictionary;
	}

	/**
	 * Setter for the stored list.
	 * @param storedList the new stored list as an array list of WordSigs
	 */
	public void setStoredList(ArrayList<WordSig> storedList) {
		this.storedList = storedList;
	}
	
	/**
	 * toString method converting the path to dictionary and stored list to
	 * a string.
	 */
	public String toString() {
		return this.getPathToDictionary() + ":" + this.getStoredList();
	}

	/**
	 * Uses a buffered reader to read the words contained in the text file located
	 * at path to dictionary. Only words that contain all alphabetic characters are
	 * added to the dictionary. An IOException is thrown if the file cannot be found.
	 * @param pathToDictionary the directory location where the text file containing
	 * words is stored
	 * @return the dictionary as an array list of WordSigs
	 */
	public static ArrayList<WordSig> readDictionary(String pathToDictionary) {
		ArrayList<WordSig> dictionary = new ArrayList<WordSig>();
		String readString;
		try {
			BufferedReader in = new BufferedReader(new FileReader(pathToDictionary));
			while ((readString = in.readLine()) != null) {
				if (PredictivePrototype.isValidWord(readString)) {
					dictionary.add(new WordSig(readString.toLowerCase()));
				}
			}
			in.close();
		} 
		catch (IOException e) {
			System.out.println("File not found");
		}
		Collections.sort(dictionary);
		return dictionary;
	}
	
//	public static ArrayList<WordSig> readDictionary(String pathToDictionary) {
//		ArrayList<WordSig> dictionary = new ArrayList<WordSig>();
//		String readString;		
//		try {
//			Scanner s = new Scanner(new File(pathToDictionary));
//			while (s.hasNextLine()) {
//				readString = s.nextLine();
//				if (PredictivePrototype.isValidWord(readString)) {
//					dictionary.add(new WordSig(readString.toLowerCase()));				
//				}
//			}
//			s.close();
//		} 
//		catch (IOException e) {
//			System.out.println("File not found");
//		}
//		Collections.sort(dictionary);
//		return dictionary;
//	}

	/**
	 * Uses binary search to find a word in the stored list that matches the given
	 * signature. The stored list is then searched in both directions from this point
	 * until a non-matching signature is found. Because the matching words are added
	 * to a TreeSet, they are sorted into alphabetical order.
	 * @return all words with a matching signature to the one given in a set of strings
	 */
	@Override
	public Set<String> signatureToWords(String signature) {
		ArrayList<WordSig> listToSearch = this.storedList;
		Set<String> matchingSignatures = new TreeSet<String>();
		int anElementContainingSignature = Collections.binarySearch(getSignatures(listToSearch), signature);
		if (anElementContainingSignature >= 0) {
			for (int i = anElementContainingSignature; i < listToSearch.size(); i++ ) {			
				if (!(listToSearch.get(i).getSignature().equals(signature))) {
					break;
				}
				else {
					matchingSignatures.add(listToSearch.get(i).getWords().toLowerCase());
				}
				
			}
			for (int i = anElementContainingSignature - 1; i > -1; i--) {
				if (!(listToSearch.get(i).getSignature().equals(signature))) {
					break;
				}
				else {
				matchingSignatures.add(listToSearch.get(i).getWords().toLowerCase());
				}				
			}					
		}		
		return matchingSignatures;
	}
	
	/**
	 * Puts all the signatures from the stored list into a list of strings but keeps
	 * them in the same order so the element numbers will correspond to the correct
	 * stored list element. Converts to signatures into the same type as the signature
	 * so that binary search can be performed 
	 * @param wordSigs the list of wordSigs to get the signatures from
	 * @return the signatures in a list of strings
	 */
	public static List<String> getSignatures(ArrayList<WordSig> wordSigs) {
		List<String> signatures = new ArrayList<String>();
		for (int i = 0; i < wordSigs.size(); i++) {
			signatures.add(wordSigs.get(i).getSignature());
		}
		return signatures;
	}
	
	public static void main(String[] args) {
		ListDictionary d1 = new ListDictionary("words.txt");
	}

}
