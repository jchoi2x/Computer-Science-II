/* Name:    James Choi
 * Course:  Computer Science II
 * Assignment #3 - Topological Sort  
 * Implement Topological sort via DepthFirst Search and Decrease by one */
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;




public class TopoSort {
	public Stack<Integer> stk;
	public ArrayList<Boolean> visited ;

	public TopoSort() {
		stk = new Stack<Integer>();
		visited = new ArrayList<Boolean>();
		readMatrix("graphs.txt");
	}


	/**
	 * Initializes utilities and calls the recursive dfs then prints the 
	 * results of topological sort when dfs returns.
	 * @param matrix
	 */
	public void dfsCaller(Integer[][] matrix) {
		stk.clear();
		visited.clear();
		
		for ( int i = 0 ; i < matrix.length ; i++ )visited.add(false);
		
		// While unvisited vertices exist
		while ( visited.indexOf(false) != -1 ){
			for ( int i = 0 ; i < visited.size() ; i++ ){
				if ( !visited.get(i) ){
					dfs(matrix,i);
				}
			}
		}
		// Print the contents of the stack containing reversed topological order
		while (!stk.isEmpty()) {
			System.out.printf("%d ", stk.pop());
		}
	}
	/**
	 * Recursive Depthfirst search. Pushes to a stack only so 
	 * dfsCaller can print in reverse order when dfs returns
	 * @param matrix
	 * @param v 				
	 **/
	public void dfs (Integer[][] matrix, Integer v){
		// mark v visited
		visited.set(v, true);

		// for each vertex w pointed to by v  
		for ( Integer w : getOutgoing(matrix, v) ) {
			// if w is unvisited then dfs(w)
			if (!visited.get(w)) {
				dfs(matrix,w);
			}
		}
		
		// push(v) into stack so it can be printed in reverse l8r
		stk.push(v);
	}

	/**
	 * Implements iterative decrease-by-one algorithm to topologically sort
	 * the given matrix.
	 * @param matrix
	 */
	public void DecAndConquer(Integer[][] matrix){
        // L <- Empty set that will contain sorted elements
        ArrayList<Integer> L = new ArrayList<Integer>();
        // S <- Set of all nodes with no incoming edges
        ArrayList<Integer> S = getNoIncoming(matrix);	// Fill S w. vertices w/o incoming edges
       
        // while S is nonempty
        while (!S.isEmpty()){
            // remove a node n from S and insert into L
        	Integer n = S.remove(0);
        	L.add(n);
        	
            // for each node m with an edge from n to m
        	for ( Integer m : getOutgoing(matrix, n)){
        		// remove edge e from the graph
        		matrix[n][m] = 0 ;
        		

                // if m has no other incoming edges
        		if (getNoIncoming(matrix, m)){
        			// then insert m in S
        			S.add(m);
        		}
        	}            
        }
	    // if graph has edges then ERROR
        for ( int i = 0 ; i < matrix.length ; i++ ){
        	if ( getOutgoing(matrix,i).size() != 0 ){
        		System.out.println("\nERROR");
        		return ; 
        	}
        }
        
        // Otherwise print L
    	for ( Integer n : L ){
    		System.out.printf("%d ", n);
    	}
    }
	
	
	

	
	
	
	
	
	/**
	 * From input file, reads the number matrixes and for each matrix read,
	 * performs depth-first search and decrease-by-one search on the matrix
	 * to find topological ordering.
	 * @param filename
	 */
	public void readMatrix(String filename) {
		try {
			Scanner scan = new Scanner(new FileReader(filename)); // Read

			// Get number of graphs in input file
			int numGraphs = Integer.parseInt(scan.nextLine().split("\\s+")[0]);

			// For each graph
			for (int i = 0; i < numGraphs; i++) {
				// read the first two lines containing startVertex and nxn size
				int n_by_n = Integer.parseInt(scan.nextLine().split("\\s+")[0]);

				// Declare/Instantiate multidimensional integer array to hold
				// the adjacency matrix values
				Integer[][] matrix = new Integer[n_by_n][n_by_n];

				// for each next line 
				for (int j = 0; j < n_by_n && scan.hasNextLine(); j++) {
					// Read the line as one long string, and split at whitespace
					// and split into an array before assigning to aLine
					String[] aLine = scan.nextLine().split("\\s+");
					// Convert each character element in aLine into an integer
					// and copy into matrix
					for (int k = 0; k < n_by_n; k++) {
						matrix[j][k] = Integer.parseInt(aLine[k]);
					}
				}
				
				
				
				
				/*
				 * Execute Depth-First Search and Decrease-by-One methods
				 */
				// Check aCyclic or Cyclic and run if acyclic
				if (acyclicTest(matrix)){
					System.out.printf("TS("+i+",DFS): ");
					dfsCaller(matrix);
					System.out.printf("\nTS("+i+",DBO): ");
					DecAndConquer(matrix);
					System.out.println();
				}
				else{
					System.out.printf("TS("+i+",DFS): NO TOPOLOGICAL SORT");
					System.out.printf("\nTS("+i+",DBO): NO TOPOLOGICAL SORT");
					System.out.println();
					
				}
			}
			scan.close();
		} catch (IOException ex) {
			System.out.println("ERROR IOException");
		}
		System.out.println();

	}

	/**
	 * Returns arraylist containing vertices with no incoming edges 
	 * of the entire matrix. Vertex v has no incoming edges if 
	 * column v of adj. matrix is all 0's
	 * @param matrix
	 * @return ArrayList containing vertices with no incoming edges
	 */
    public ArrayList<Integer> getNoIncoming(Integer[][] matrix){
    	ArrayList<Integer> rtn = new ArrayList<Integer>();
        // For each column
        for ( int i = 0 ; i < matrix.length ; i++ ){
        	// for each row in column
            for ( int j = 0 ; j < matrix[i].length ; j++){
            	
                if ( matrix[j][i] == 1 )
                	break; 
                
                // If at final row j of column i, and matrix contains a 0 add it
                if ( j+1 >= matrix[i].length && matrix[j][i] == 0){
                    rtn.add(i);
                }
            }
        }
        return rtn ;
    }
    
    /**
     * (Overloaded method) Returns true if vertex m has no incoming edges. Otherwise false
     * @param matrix
     * @param m
     * @return True if no incoming edges. False otherwise
     */
	public Boolean getNoIncoming(Integer[][] matrix, Integer m){
		for ( int i = 0 ; i < matrix.length ; i++){
			if (matrix[i][m] == 1){
				return false ; 
			}
		}
		return true ; 
	}
	
    /**
     * Finds and returns Arraylist containing all the vertices that m points. 
     * @param matrix
     * @param m
     * @return Arraylist containing vertices that m points to 
     */
	public ArrayList<Integer> getOutgoing(Integer[][] matrix, Integer m){
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		
		// Get the first instance of a 1 in the row of m 
		int index = Arrays.asList(matrix[m]).indexOf(1);
		// If no 1 then return rtn
		if ( index == -1) return rtn ; 
		
		for ( int i = index ; i < matrix.length ; i++ ){
            if ( matrix[m][i] == 1 ){
            	rtn.add(i);
            }
        }
		return rtn ; 
	}
	
	/**
	 * Tests for acyclicity of matrix by removing a leaf node 
	 * from given matrix and recursing until either the matrix is complely 
	 * empty (acyclic) or there are no leaves in the matrix (cyclic) 
	 * @param matrix
	 * @return True if matrix is acyclic and false otherwise 
	 */
	public Boolean acyclicTest(Integer[][] matrix) {
		if (matrix.length == 0) {
			// Acyclic
			return true;
		} else if (findLeaf(matrix) == -1) {
			// Cyclic
			return false;
		} else {
			// Recurse
			return acyclicTest(removeVertex(matrix, findLeaf(matrix) ));
		}
	}
	
	/**
	 * Removes the target row and target column from the given matrix and returns it 
	 * @param matrix
	 * @param target
	 * @return Modified version of argument matrix, with row target and column target removed
	 */
	public Integer[][] removeVertex(Integer[][] matrix, int target) {
		Integer[][] ret = new Integer[matrix.length - 1][matrix.length - 1];

		if (matrix.length == 1 && matrix[0][0] == 0) {
			return new Integer[0][0];
		}
		int row = 0, col = 0;
		for (int i = 0; i < matrix.length; i++) {
			if (i == target) continue;	
			else{
				for ( int j = 0 ; j < matrix[i].length ; j++ ){
					if ( j == target ) continue ;
					else{
						ret[row][col] = matrix[i][j];
						col++ ;
					}
				}
				col = 0 ;
				row++;
			}
		}
		return ret;
	}

	/**
	 * Finds the first instance of a leaf in the matrix and returns it
	 * @param matrix
	 * @return Index of a leaf in the given argument
	 */
	public int findLeaf(Integer[][] matrix) {
		for ( int i = 0 ; i < matrix.length ; i++ ){
			if ( Arrays.asList(matrix[i]).indexOf(1) == -1 ){
				return i ;
			}
		}
		return -1 ;
	}


	public static void main(String[] args) {
		new TopoSort();
	}
}
