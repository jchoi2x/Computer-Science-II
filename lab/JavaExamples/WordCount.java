import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class WordCount {
	public static void main(String [] args) throws IOException {
		HashMap<String,Integer> myHash = new HashMap<String,Integer>();
		
		Scanner in = new Scanner( new File("corpus.txt") );

		while (in.hasNext()) {
			String s = in.next();
			
			if (myHash.containsKey(s))
				myHash.put(s, myHash.get(s) + 1);
			else
				myHash.put(s, 1);
		}
		
		String myWord = "spindle";

		if (myHash.containsKey(myWord)) System.out.println(myWord + " occurs " + myHash.get(myWord) + " times.");
		else System.out.println(myWord + " does not occur in the input file.");
		
		Set<String> keys = myHash.keySet();
		
		for (String s : keys) System.out.println(s + " (" + myHash.get(s) + ")");
	}
}
