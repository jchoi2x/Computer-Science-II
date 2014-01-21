import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by kizzlebot on 1/20/14.
 */
public class Graph {
	public Integer[][] matrix ;
	public Queue<Integer> queue ;
	public Stack<Integer> stack ;

	public Graph( String fileName ){
		// Call read method to process all the graphs
		this.queue = new LinkedList<Integer>();
		this.stack = new Stack<Integer>();

		this.readIn(fileName);
	}

	public void readIn(String filename){
		try{
			Scanner scan = new Scanner(new FileReader(filename)); // Read from input stream

			String[] aLine ;

			int numGraphs = Integer.parseInt(scan.nextLine().split("\\s+")[0]);
			Integer[][] matrix ;

			// For each graph
			for ( int i = 0 ; i < numGraphs ; i++ ){
				// read the first two lines containing startVertex and nxn size of adj matrix
				int startVertex = Integer.parseInt(scan.nextLine().split("\\s+")[0]);
				int n_by_n = Integer.parseInt(scan.nextLine().split("\\s+")[0]);

				// Instantiate integer array array
				matrix = new Integer[n_by_n][n_by_n];

				// Read graph line by line, n times
				int j = 0 ;
				while ( scan.hasNextLine() && j < n_by_n ){
					aLine = scan.nextLine().split("\\s+"); // Read whole line and split

					// Copy string line as integers
					for ( int k = 0 ; k < n_by_n ; k++ ){
						matrix[j][k] = Integer.parseInt(aLine[k]);
					}
					j++ ;
				}

				// Breadth first search here
				System.out.printf("\nBFS("+i+","+startVertex+"): ");
				breadthFirst(matrix,startVertex);
				System.out.printf("\nDFS("+i+","+startVertex+"): ");
				depthFirst(matrix,startVertex);

				// Depth first search here
			}
		}catch(IOException ex) {
			System.out.println("ERROR IOException");
		}
		System.out.println();
	}

	public void breadthFirst(Integer[][] matrix, int startVertex) {
		// Initialize variables required
		int dq ; // Used to hold dequeued values
		Integer[] visited = new Integer[matrix.length] ; // Array used to keep track of visited vertexes
		for ( int i = 0 ; i < matrix.length ; i++ ) visited[i] = 0 ; // Fill the newly visited array with 0's (for false)

		// Enqueue the first vertex and mark as visited
		queue.add(startVertex);
		visited[startVertex] = 1 ;

		// While the queue is not empty
		while (!queue.isEmpty()){
			// Dequeue and print dequeued value
			dq = queue.remove();
			System.out.printf("%d ",dq);


			/*
			 * Move to row # dq, and for each unvisited 1 in that row, add the column # to the queue and
			 * mark as visited.
			 */

			for ( int j = 0 ; j < matrix.length; j++ ){
				// if there is a 1 in row # dq and column # j that is unvisited, add to queue and mark as visited
				if ( matrix[dq][j] == 1 && visited[j] == 0 ){
					queue.add(j);
					visited[j] = 1 ;
				}
			}
		}
	}

	public void depthFirst(Integer[][] matrix, int startVertex){
		int popped ; // Used to hold dequeued values
		Boolean[] visited = new Boolean[matrix.length] ; // Array used to keep track of visited vertexes
		for ( int i = 0 ; i < matrix.length ; i++ ) visited[i] = false ; // Fill the newly visited array with 0's (for false)

		stack.push(startVertex);
		visited[startVertex] = true ;

		while (!stack.isEmpty()){
			popped = stack.pop();

			if ( popped == startVertex ) System.out.printf("%d ",popped);

			for ( int i = 0 ; i < matrix.length ; i++ ){
				if ( (matrix[popped][i].equals(1)) && (!visited[i])){
					stack.push(i);
					visited[i] = true;
					System.out.printf("%d ",i);
				}
			}
		}
	}



	public static void main(String[] args){
		Graph g = new Graph("infile.txt");
	}
}
