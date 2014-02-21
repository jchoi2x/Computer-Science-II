import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Topological {
	public Integer[][] matrix;
	public ArrayList<Integer[][]> aList; 
	public Topological() {
		this.aList = new ArrayList<Integer[][]>();
		readAllMatrix("infile.txt");
		for ( int i = 0 ; i < aList.size(); i++ ){
			top(aList.get(i));
		}
	}

	public void readAllMatrix(String filename) {
		try {
			Scanner scan = new Scanner(new FileReader(filename)); // Read 

			// Get number of graphs in input file
			int numGraphs = Integer.parseInt(scan.nextLine().split("\\s+")[0]);

			// For each graph
			for (int i = 0; i < numGraphs; i++) {
				// read the first two lines containing startVertex and nxn size
				int startVertex = Integer.parseInt(scan.nextLine().split("\\s+")[0]);
				int n_by_n = Integer.parseInt(scan.nextLine().split("\\s+")[0]);

				// Declare/Instantiate multidimensional integer array to hold
				// the adjacency matrix values
				Integer[][] matrix = new Integer[n_by_n][n_by_n];

				// for each line in the given adjacency matrix
				for (int j = 0; j < n_by_n && scan.hasNextLine(); j++) {
					// Read line as one long string, and split at whitespace
					// into an array before assigning to aLine
					String[] aLine = scan.nextLine().split("\\s+");
					// Convert each character element in aLine into an integer
					// and copy into matrix
					for (int k = 0; k < n_by_n; k++) {
						matrix[j][k] = Integer.parseInt(aLine[k]);
					}
				}
				this.aList.add(matrix);
			}
			scan.close();
		} catch (IOException ex) {
			System.out.println("ERROR IOException");
		}
		System.out.println();
	}

	public void top(Integer[][] matrix) {
		Integer[] noVisit = new Integer[matrix.length];
		//Stack<Integer> stack = new Stack<Integer>();
		
		for (int j = 0; j < matrix.length; j++)
			noVisit[j] = 0;

		for (int i = matrix.length-1; i > -1 ; i--) {
			if (noVisit[i] == 0) {
				int j = 0;
				// Go down row 
				for (j = 0 ; j < matrix.length ; j++) {
					// if 1 found in column and isn't and excluded cell
					if ( matrix[j][i] == 1 && noVisit[j] != 1 ){
						// break out 
						break;
					}
				}
				// Only way this is true is if the previous for loop iterated completely 
				if (j == matrix.length) {
					noVisit[i] = 1;
					System.out.printf("%d, ",i);
					i = matrix[i].length;
				}
			}
		}
	}
	

	public static void main(String[] args){
		new Topological();
	}
}
