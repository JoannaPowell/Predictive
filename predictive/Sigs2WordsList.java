package predictive;

import java.util.Iterator;
import java.util.Set;

/**
 * Command line program that allows the more efficient signature to words
 * method from ListDictionary to be run from and arguments entered in the 
 * command line.
 * 
 * A new ListDictionary object is created on which signatureToWords is called on.
 * The words that match with the given signature are printed in the command
 * line (one signature with its matching words per line). An iterator is used
 * to iterate through the words and print them out.
 * @author Joanna Powell
 * @version 05/02/2017
 */
public class Sigs2WordsList {
	
	public static void main(String[] args) {
		ListDictionary d1 = new ListDictionary("words.txt");
		for (String s : args) {
			System.out.print(s + ": ");
			Set<String> words = d1.signatureToWords(s);
			Iterator<String> wordsIterator = words.iterator();
			while (wordsIterator.hasNext()) {
				System.out.print(wordsIterator.next() + " ");
			}
			System.out.println();
		}
	}
}
