package predictive;

/**
 * Command line program that allows word to signature to be run from
 * and arguments entered in the command line.
 * 
 * Any words that consist of any non-alphabetic characters are ignored.
 * 
 * The signature for each word is printed in the command line.
 * @author Joanna Powell
 * @version 02/02/2017
 *
 */
public class Words2SigProto {
	public static void main(String[] args) {
		for (String s : args) {
			if (PredictivePrototype.isValidWord(s)) {
				System.out.print(s + ": ");
				System.out.println(PredictivePrototype.wordToSignature(s));
			}
		}
	}
}
