import java.util.*;

/**
 * Created by kizzlebot on 1/18/14.
 */
public class Matrix {

	public TreeMap<Integer,ArrayList<Integer>> matrix ;
	public int[][] adjMatrix ;
	public Queue<Integer> queue ;
	public Stack<Integer> stack ;

	private int currRow;        // Used to keep track of current row in matrix
	private int currCol;        // Used to keep track of current column in matrix
	private int nXn;        // The maximum size of the n-by-n matrix
	private boolean full;

	private Integer[] parent ;
	private Boolean[] visited ;

	/**
	 * Given the n-by-n size of the matrix, initializes a multidimensional array of size n-by-n,
	 * initializes currRow and currCol to zero, and stores the max row/col value (nXn)
	 * @param n The size of the n-by-n matrix to create
	 */
	public Matrix(int n) {

		this.matrix = new TreeMap<Integer, ArrayList<Integer>>();
		this.adjMatrix = new int[n][n];
		this.stack = new Stack<Integer>();
		this.queue = new LinkedList<Integer>();


		this.currRow = 0;
		this.currCol = 0;
		this.nXn = n;
		this.full = false;

		parent  = new Integer[this.nXn];
		visited = new Boolean[this.nXn];
		// Fill visited array with 0's
		for ( int i = 0 ; i < nXn ; i++ ){
			visited[i] = false;
		}
	}

	/**
	 * If matrix not full, adds given arg (insVal) to the next available index (currCol), and increments
	 * currCol.  Then it checks if column is full ( currCol == nXn ), and if it is ( currRow == nXn ),
	 * it resets currCol to zero and increments current row reference (currRow).
	 * If currRow is found to be full, then the 'full' flag is set to true and no more values can be inserted into matrix.
	 *
	 * @param insVal The integer value to insert.
	 * @return True if adding to matrix succeeded. False if failed.
	 */
	public boolean addNext(int insVal) {
		// If not full
		if ( !full ) {
			// If we're on a new row, add a new ArrayList to matrix treemap
			if ( currCol == 0 ) matrix.put(currRow,new ArrayList<Integer>());
			// Insert into next available column location and increment currCol
			this.matrix.get(currRow).add(insVal);

			this.adjMatrix[this.currRow][this.currCol] = insVal;
			this.currCol++;

			// If the column is full then increment currRow value, and reset currCol
			if (this.currCol == this.nXn) {
				this.currCol = 0;
				this.currRow++;
			}
			// If the incremented currRow is full, then make sure to change the full variable to indicate filled matrix
			if (this.currRow == this.nXn ){
				this.full = true;
			}
			// Return true for success
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Prints the matrix (for debugging purposes)
	 */
	public void printMatrix(){
		for ( int i = 0 ; i < nXn ; i++ ){
			for ( int j = 0 ; j < nXn ; j++ ){
				System.out.printf("%d ", this.matrix.get(i).get(j));
			}
			System.out.println();
		}
		System.out.println();
// 		for ( int i = 0 ; i < visited.length ; i++ ){
// 			if (visited[i]) System.out.printf("T ");
// 			else System.out.print("F ");
// 		}
// 		System.out.println();
	}

	public void depthFirst(int vFirst){
		//vFirst = 0, n = 6
		int v,i;
		// st is a stack
		stack.push(vFirst);

		while(!stack.isEmpty()){
			v = stack.pop();
			if(visited[v]==false){
				System.out.print(v);
				visited[v]=true;
			}
			for ( i = 0; i <= 6; i++){
				if((adjMatrix[v][i] == 1) && (visited[i] == false)){
					stack.push(v);
					visited[i]=true;
					System.out.print(" " + i);
					v = i;
				}
			}
		}
	}
	public void depthFirstRecursive(int w) {
		int j;     //w = 0;

		visited[w] = true;
		if (w == 0) {
			System.out.print(w + " ");
		}

		for (j = 0; j < nXn-1 ; j++) {
			if ((adjMatrix[w][j] == 1) && (visited[j] == false)) {
				System.out.print(j + " ");
				depthFirstRecursive(j);
			}
		}
	}

	public void breadthFirst(int first) {
		int rowNum;     // first = 0; p = 6
		int[] nodeVisited = new int[nXn-1];
		// Add the first vertices
		queue.add(first);

		while (!queue.isEmpty()) {

			rowNum = queue.remove();

			// Executes once for the first iteration only.
			if(nodeVisited[rowNum]==0){
				nodeVisited[rowNum]=1;     // Mark it visited
				System.out.print(rowNum);  // Print the row#
			}


			// Traverse the columns of current Row
			for (int colNum = 0; colNum < nXn-1; colNum++){
				// If a 1 is encountered in that row and it is unvisited
				if((adjMatrix[rowNum][colNum] == 1) && (nodeVisited[colNum] == 0)){
					queue.add(rowNum);                  // Add it queue

					nodeVisited[colNum] = 1;            // Mark it visited
					System.out.print(" " + colNum);     // Print the col# which is the node # too

					rowNum = colNum;                    // Make the col# into the row number
				}
			}
		}
	}
}
