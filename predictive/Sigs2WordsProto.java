package predictive;

import java.util.Iterator;
import java.util.Set;

/**
 * Command line program that allows signature to words to be run from
 * and arguments entered in the command line.
 * 
 * The words that match with the given signature are printed in the command
 * line (one signature with its matching words per line). An iterator is used
 * to iterate through the words and print them out.
 * @author Joanna Powell
 * @version 02/02/2017
 *
 */
public class Sigs2WordsProto {
	public static void main(String[] args) {
		for (String s: args) {
			System.out.print(s + ": ");
			Set<String> words = PredictivePrototype.signatureToWords(s);
			Iterator<String> wordsIterator = words.iterator();
			while (wordsIterator.hasNext()) {
				System.out.print(wordsIterator.next() + " ");
			}
			System.out.println();
		}
	}

}
