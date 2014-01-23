import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Graph {
    /**
     * Immediately begins BFS and DFS upon instantiation with filename
     * @param fileName
     */
    public int[] visited ;
    public int count ;

    public Graph( String fileName ){
        this.readIn(fileName);
    }

    /**
     * Reads in the given filename with the assumption that first line contains the number of graphs, and each set of
     * graphs is preceded by the start vertex, and the dimensions of the matrix.
     * @param filename
     */
    public void readIn(String filename){
        try{
            Scanner scan = new Scanner(new FileReader(filename)); // Read from input stream

            int numGraphs = Integer.parseInt(scan.nextLine().split("\\s+")[0]);

            // For each graph
            for ( int i = 0 ; i < numGraphs ; i++ ){
                // read the first two lines containing startVertex and nxn size of adj matrix
                int startVertex = Integer.parseInt(scan.nextLine().split("\\s+")[0]);
                int n_by_n = Integer.parseInt(scan.nextLine().split("\\s+")[0]);

                // Instantiate integer array array to hold the matrix values
                Integer[][] matrix = new Integer[n_by_n][n_by_n];


                // Read graph line by line, n times
                for ( int j = 0 ; j < n_by_n && scan.hasNextLine() ; j++ ){
                    // Read line as one long string, split at whitespace into an array before assigning to aLine
                    String[] aLine = scan.nextLine().split("\\s+");
                    // Convert each String element in aLine into an integer and copy into matrix
                    for ( int k = 0 ; k < n_by_n ; k++ ){
                        matrix[j][k] = Integer.parseInt(aLine[k]);
                    }
                }

                // Breadth first search here
                System.out.printf("\nBFS("+i+","+startVertex+"): ");
                breadthFirst(matrix,startVertex);

                //// Depth first search here
                System.out.printf("\nDFS("+i+","+startVertex+"): ");
                depthFirst(matrix,startVertex);

                // Recursive Depth first Search
                System.out.printf("\nDFSRec("+i+","+startVertex+"): ");
                dfsRecWrap(matrix,startVertex);

                System.out.println();
            }

        }catch(IOException ex) {
            System.out.println("ERROR IOException");
        }
        System.out.println();
    }

    /**
     * A Wrapper function that calls the recursive depth first search
     * @param matrix The adjacency matrix
     * @param startVertex The starting vertex specified in the input file
     */
    public void dfsRecWrap( Integer[][] matrix, int startVertex){
        this.count = 0 ;
        // fill up the visited array with zeros
        this.visited = new int[matrix.length];
        Arrays.fill(this.visited,0);

        for ( int i = startVertex ; i < matrix.length ; i++ ){
            if ( this.visited[i] == 0 ){
                dfsRecursive(matrix,i);
            }
        }
    }
    public void dfsRecursive(Integer[][] matrix, int v ){
        System.out.printf("%d ",v);
        this.count+=1 ;
        this.visited[v] = 1 ;
        for ( int i : this.getAdjacent(matrix,v)){
            if ( this.visited[i] == 0 ){
                this.dfsRecursive(matrix,i);
            }
        }
    }
    /**
     * Finds and returns an array containing the indices of adjacent vertices
     * @param matrix The adjacency matrix
     * @param v The vertex to find adjacent vertices around
     */
    public Integer[] getAdjacent(Integer[][] matrix, int v){
        List<Integer> list = new ArrayList<Integer>();
        Integer[] adj = new Integer[matrix.length];
        for ( int i = 0 ; i < matrix.length ; i++ ){
            if ( matrix[v][i] == 1 ){
                list.add(i);
            }
        }
        return list.toArray(new Integer[list.size()]);
    }




    /**
     * Executes a breadthFirst search
     * @param matrix Adjacency Matrix
     * @param startVertex Vertex to begin at
     */
    public void breadthFirst(Integer[][] matrix, int startVertex) {
        /* ----------------------------------------------------------------------------------------------------------------
         * Declaration/Initialization
         * ---------------------------------------------------------------------------------------------------------------- */
        int dq ;                                          // Used to hold dequeued values
        Queue<Integer> queue = new LinkedList<Integer>(); // Create instance of Queue

        // Visited array
        Boolean[] visited = new Boolean[matrix.length] ;                 // Array used to keep track of visited vertexes
        for ( int i = 0 ; i < matrix.length ; i++ ) visited[i] = false ; // Fill the newly visited array with 0's (for false)

        /* ----------------------------------------------------------------------------------------------------------------
         * Breadth First Algorithm Iterative Implementation
         * ---------------------------------------------------------------------------------------------------------------- */
        queue.add(startVertex);         // add to queue
        visited[startVertex] = true ;   // mark as visited

        // While the queue is not empty
        while (!queue.isEmpty()){
            dq = queue.remove();          // Dequeue from queu
            System.out.printf("%d ",dq);  // print it

            // for every element in row of dequeued value dq
            for ( int j = 0 ; j < matrix.length; j++ ){
                // If marked as adjacent and it is unvisited
                if ((matrix[dq][j] == 1) && (!visited[j]) ){
                    queue.add(j);             // add to queue
                    visited[j] = true ;       // mark as visited
                }
            }
        }
    }

    /**
     * Executes a Breadth first search
     * @param matrix Adjacency Matrix
     * @param startVertex Vertex to begin at
     */
    public void depthFirst(Integer[][] matrix, int startVertex){
        /* ----------------------------------------------------------------------------------------------------------------
         * Declaration/Initialization
         * ---------------------------------------------------------------------------------------------------------------- */
        int popped ;                                    // Used to hold dequeued values
        Stack<Integer> stack = new Stack<Integer>();    // Create a instance of a stack

        // Visited array
        Boolean[] visited = new Boolean[matrix.length] ;                 // Array used to keep track of visited vertexes
        for ( int i = 0 ; i < matrix.length ; i++ ) visited[i] = false ; // Fill the newly visited array with 0's (for false)


        /* ----------------------------------------------------------------------------------------------------------------
         * Depth First Algorithm Iterative Implementation
         * ---------------------------------------------------------------------------------------------------------------- */

        stack.push(startVertex);              // push to stack
        visited[startVertex] = true ;         // mark as visited
        System.out.printf("%d ",startVertex); // print it

        // while stack isn't empty
        while (!stack.isEmpty()){
            popped = stack.pop(); // Pop from stack

            // For each element in the row of popped
            for ( int i = 0 ; i < matrix.length ; i++ ){
                // If marked as adjacent and it is unvisited
                if ( (matrix[popped][i].equals(1)) && (!visited[i])){
                    stack.push(i);                  // push to stack
                    visited[i] = true;              // mark as visited
                    System.out.printf("%d ",i);     // print it
                }
            }
        }
    }



    public static void main(String[] args){
        Graph g = new Graph("infile.txt");
    }
}
