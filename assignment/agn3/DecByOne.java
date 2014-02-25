import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Topological {
	public Stack<Vertex> stk;

	public Topological() {
		stk = new Stack<Vertex>();
		readMatrix("infile.txt");
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
				// Check aCyclic or Cyclic
				if (acyclicTest(matrix)){
					toVertex(matrix);
				}
				else{
					System.out.println("\nTS(j, DFS): NO TOPOLOGICAL SORT");
					System.out.println("TS(i, DBO): NO TOPOLOGICAL SORT");
				}
			}
			scan.close();
		} catch (IOException ex) {
			System.out.println("ERROR IOException");
		}
		System.out.println();

	}

	// True if acylic
	public Boolean acyclicTest(Integer[][] matrix) {
		if (matrix.length == 0) {
			//System.out.println("ACyclic");
			return true;
		} else if (findLeaf(matrix) == -1) {
			//System.out.println("!Cyclic");
			return false;
		} else {
			return acyclicTest(removeNode(matrix, findLeaf(matrix) ));
		}
	}

	public Integer[][] removeNode(Integer[][] matrix, int target) {
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

	public int findLeaf(Integer[][] matrix) {
		for ( int i = 0 ; i < matrix.length ; i++ ){
			if ( Arrays.asList(matrix[i]).indexOf(1) == -1 ){
				return i ;
			}
		}
		return -1 ;
	}

	public void toVertex(Integer[][] matrix) {

		Vertex[] G = new Vertex[matrix.length];

		// Fill G with all vertices
		for (int i = 0; i < matrix.length; i++) {
			G[i] = new Vertex(i);
		}
		// For each vertex, add its adjacents
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					G[i].addAdjacent(G[j]);
				}
			}
		}
		dfsCaller(G);
        DecAndConquer(matrix);
        System.out.println();
	}

	public void dfs(Vertex v) {
		//System.out.println("Visiting " + v.getName());
		// mark v visited
		v.setVisited();

		// copies v.getOuts(i), the out vertices of v, to a temporary array
		Vertex[] outs = v.getAdjacents();

		// for each w in OUT(v) do
		for (int i = 0; i < v.getNumAdjs(); i++) {
			Vertex w = outs[i];
			// System.out.println(v.getName()+" now looks at "+w.getName());

			// if w is unvisited then dfs(w)
			if (!w.isVisited()) {
				dfs(w);
			}
		}
		// push(v) into STACK
		stk.push(v);
		// System.out.println("STACK: " + v.getName() + " pushed");
	}

	public void dfsCaller(Vertex[] V) {
		stk.clear();

		int i = 0;
		while (i < V.length) {
			for (i = 0; i < V.length; i++) {
				if (!V[i].isVisited()) {
					dfs(V[i]);
				}
			}
		}
		i = 0;
		System.out.printf("\nTS(j,DFS): ");
		while (i < V.length) {
			Vertex v = getStack().pop();
			i++;
			System.out.printf("%d, ", v.getName());
		}
	}










	public void DecAndConquer(Integer[][] matrix){
        // L <- Empty set that will contain sorted elements
        ArrayList<Integer> L = new ArrayList<Integer>();
        // S <- Set of all nodes with no incoming edges
        ArrayList<Integer> S = getNoIns(matrix);        // Fill S w. vertices w/o incoming edges

        // while S is nonempty
        while (!S.isEmpty()){
            // remove a node n from S and insert into L
        	Integer n = S.remove(0);
        	L.add(n);
            // for each node m with an edge from n to m

        	for ( Integer m : getOuts(matrix, n)){
        		// remove edge e from the graph

        		matrix[n][m] = 0 ;

        		// printMatrix(matrix);
                // if m has no other incoming edges
        		if (hasNoIns(matrix, m) ){
        			// then insert m in S
        			S.add(m);
        		}
        	}


        }
	    // if graph has edges then ERROR
        for ( int i = 0 ; i < matrix.length ; i++ ){
        	if ( getOuts(matrix,i).size() != 0 ){
        		System.out.println("\nERROR");
        		return ;
        	}
        }
        System.out.println();
        System.out.printf("DecAndConquer: ");
        // Otherwise print L
    	for ( Integer n : L ){
    		System.out.printf("%d, ", n);
    	}
    	System.out.println();

    }
	public ArrayList<Integer> getOuts(Integer[][] matrix, Integer m){
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		for ( int i = 0 ; i < matrix.length ; i++ ){
            if ( matrix[m][i] == 1 ){
            	rtn.add(i);
            }
        }
		return rtn ;
	}
	public Boolean hasNoIns(Integer[][] matrix, Integer m){
		for ( int i = 0 ; i < matrix.length ; i++){
			if (matrix[i][m] == 1){
				return false ;
			}
		}
		return true ;
	}
    public ArrayList<Integer> getNoIns(Integer[][] matrix){
        ArrayList<Integer> rtn = new ArrayList<Integer>();
        for ( int i = 0 ; i < matrix.length ; i++ ){
            for ( int j = 0 ; j < matrix[i].length ; j++){
                if ( matrix[j][i] == 1 ){
                	break;
                }
                if ( j+1 >= matrix[i].length && matrix[j][i] == 0){
                    rtn.add(i);
                }
            }
        }
        return rtn ;
    }
    public void printMatrix(Integer[][] matrix){
    	for ( int i = 0 ; i < matrix.length ; i++ ){
    		int j = 0 ;
    		for ( j = 0 ; j < matrix.length-1 ; j++ ){
    			System.out.printf("%d, ", matrix[i][j]);
    		}
    		System.out.printf("%d", matrix[i][j]);
    		System.out.println();

    	}
    	System.out.println();
		System.out.println();
    }








    public void decreaseConquer(Integer[][] matrix, Vertex[] V){
    	//Let Q be a queue.
    	Queue<Vertex> Q = new LinkedList<Vertex>();
		//For each vertex (in increasing order), add it to Q if it has no incoming edges.
    	for (Vertex v : V){
    		int deg = inDegree(matrix, v.getName());
    		if ( deg == 0 ){
    			Q.offer(v);
    		}
    	}
		//Let T be an empty array that will contain the vertices of the topological ordering.
    	Integer[] T = new Integer[matrix.length];
    	int i = 0 ;
		//While Q is not empty:
    	while ( !Q.isEmpty() ){
			//v  Dequeue vertex from Q.
    		Vertex v = Q.poll();
    		v.setDequeued();
			//Add V to the topological ordering T.
    		T[i] = v.getName();
    		i++ ;
			//Delete the outgoing edges of v.
    		//v.deleteAdjacents();
    		Arrays.fill(matrix[v.getName()],0);

			//Enqueue any adjacent vertices of v (in increasing order) without incoming edges.

    		for ( Integer vv : getIns(matrix, v.getName()) ){
				Q.offer(V[vv]);
    		}
    	}
    	// If every vertex of the graph was not dequeued, then stop, since the graph contains a cycle.

    	System.out.printf("\nTS(j,DAC): ");
		// Otherwise, print the topological ordering T.
    	for ( Integer toptop : T ){
    		System.out.printf("%d, ",toptop);
    	}
    }
    public ArrayList<Integer> getIns(Integer[][] matrix, int v ){

        ArrayList<Integer> rtn = new ArrayList<Integer>();

        for ( int j = 0 ; j < matrix[v].length ; j++){
            if ( matrix[j][v] == 1 ){
            	rtn.add(j);
            }
        }

        return rtn;
    }
    public int inDegree( Integer[][] matrix, int v ){
        int inCnt = 0 ;
        for ( int j = 0 ; j < matrix[v].length ; j++){
            if ( matrix[j][v] == 1 ){
                inCnt++ ;
            }
        }
        return inCnt ;
    }



	public Stack<Vertex> getStack() {
		return this.stk;
	}

	public static void main(String[] args) {
		Topological ts = new Topological();

	}
}

