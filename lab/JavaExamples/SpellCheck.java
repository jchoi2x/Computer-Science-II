import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;

public class SpellCheck {

	public static void main(String [] args) throws IOException {
		HashSet<String> myHash = new HashSet<String>();
		
		Scanner in = new Scanner( new File("dictionary.txt") );

		while (in.hasNext()) myHash.add(in.next());
		
		String questionableWord = "irregardless";

		if (myHash.contains(questionableWord)) System.out.println(questionableWord + " is in the dictionary.");
		else System.out.println(questionableWord + " is not in the dictionary.");
		
		Iterator<String> i = myHash.iterator();
		
		while (i.hasNext()) System.out.println(i.next());
		
		Object [] dictWords = myHash.toArray();
		int j = 0;
		for (Object s : dictWords) System.out.println(++j + ": " + s);
		
	}
	
}
