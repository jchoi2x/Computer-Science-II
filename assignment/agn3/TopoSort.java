/* Name:    James Choi
 * Course:  Computer Science II
 * Assignment #3				*/
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
<<<<<<< HEAD




=======
				
				
				
				
>>>>>>> agn3a
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

<<<<<<< HEAD

=======
	
>>>>>>> agn3a
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
	public Integer[][] removeVertex(Integer[][] matrix, int target) {
		Integer[][] ret = new Integer[matrix.length - 1][matrix.length - 1];

		if (matrix.length == 1 && matrix[0][0] == 0) {
			return new Integer[0][0];
		}
		int row = 0, col = 0;
		for (int i = 0; i < matrix.length; i++) {
<<<<<<< HEAD
			if (i == target) continue;
=======
			if (i == target) continue;	
>>>>>>> agn3a
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
	public int findLeaf(Integer[][] matrix) {
		for ( int i = 0 ; i < matrix.length ; i++ ){
			if ( Arrays.asList(matrix[i]).indexOf(1) == -1 ){
				return i ;
			}
		}
		return -1 ;
	}


	public void dfsCaller(Integer[][] matrix) {
		stk.clear();
		visited.clear();
		for ( int i = 0 ; i < matrix.length ; i++ )visited.add(false);
<<<<<<< HEAD

=======
		
>>>>>>> agn3a
		// While unvisited vertices exist
		while ( visited.indexOf(false) != -1 ){
			for ( int i = 0 ; i < visited.size() ; i++ ){
				if ( !visited.get(i) ){
					dfs(matrix,i);
				}
			}
		}
<<<<<<< HEAD


=======
		 
		
>>>>>>> agn3a
		while (!stk.isEmpty()) {
			System.out.printf("%d ", stk.pop());
		}
	}
	public void dfs (Integer[][] matrix, Integer v){
		// mark v visited
		visited.set(v, true);

<<<<<<< HEAD
		// for each vertex w pointed to by v
=======
		// for each vertex w pointed to by v  
>>>>>>> agn3a
		for ( Integer w : getOutgoing(matrix, v) ) {
			// if w is unvisited then dfs(w)
			if (!visited.get(w)) {
				dfs(matrix,w);
			}
		}
<<<<<<< HEAD

=======
		
>>>>>>> agn3a
		// push(v) into stack so it can be printed in reverse l8r
		stk.push(v);
	}


<<<<<<< HEAD


	/**
	 *
=======
	
	
	/**
	 * 
>>>>>>> agn3a
	 * @param matrix
	 */
	public void DecAndConquer(Integer[][] matrix){
        // L <- Empty set that will contain sorted elements
        ArrayList<Integer> L = new ArrayList<Integer>();
        // S <- Set of all nodes with no incoming edges
        ArrayList<Integer> S = getNoIncoming(matrix);	// Fill S w. vertices w/o incoming edges
<<<<<<< HEAD

=======
       
>>>>>>> agn3a
        // while S is nonempty
        while (!S.isEmpty()){
            // remove a node n from S and insert into L
        	Integer n = S.remove(0);
        	L.add(n);
<<<<<<< HEAD

=======
        	
>>>>>>> agn3a
            // for each node m with an edge from n to m
        	for ( Integer m : getOutgoing(matrix, n)){
        		// remove edge e from the graph
        		matrix[n][m] = 0 ;
<<<<<<< HEAD

=======
        		
>>>>>>> agn3a

                // if m has no other incoming edges
        		if (getNoIncoming(matrix, m)){
        			// then insert m in S
        			S.add(m);
        		}
<<<<<<< HEAD
        	}
=======
        	}            
>>>>>>> agn3a
        }
	    // if graph has edges then ERROR
        for ( int i = 0 ; i < matrix.length ; i++ ){
        	if ( getOutgoing(matrix,i).size() != 0 ){
        		System.out.println("\nERROR");
<<<<<<< HEAD
        		return ;
        	}
        }

=======
        		return ; 
        	}
        }
        
>>>>>>> agn3a
        // Otherwise print L
    	for ( Integer n : L ){
    		System.out.printf("%d ", n);
    	}
    }
<<<<<<< HEAD





	/**
	 * Returns arraylist containing vertices with no incoming edges
	 * of the entire matrix. Vertex v has no incoming edges if
=======
	
	
	
	
	
	/**
	 * Returns arraylist containing vertices with no incoming edges 
	 * of the entire matrix. Vertex v has no incoming edges if 
>>>>>>> agn3a
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
<<<<<<< HEAD

                if ( matrix[j][i] == 1 )
                	break;

=======
            	
                if ( matrix[j][i] == 1 )
                	break; 
                
>>>>>>> agn3a
                // If at final row j of column i, and the matrix contains a 0, entire column is zero
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
<<<<<<< HEAD
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

=======
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
		
>>>>>>> agn3a
		for ( int i = index ; i < matrix.length ; i++ ){
            if ( matrix[m][i] == 1 ){
            	rtn.add(i);
            }
        }
<<<<<<< HEAD
		return rtn ;
=======
		return rtn ; 
>>>>>>> agn3a
	}

	public static void main(String[] args) {
		new TopoSort();
	}
}
//45*67  = (4+5)*(6+7)-4*6-5*7
//       = 9*13-24-35
<<<<<<< HEAD
//		 = 117-24-35
=======
//		 = 117-24-35
>>>>>>> agn3a
