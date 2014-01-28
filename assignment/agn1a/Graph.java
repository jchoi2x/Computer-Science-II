/*       Name: James Choi
 *     Course: COP3503C
 *    Section: Tues/Thurs 1:30 PM - 2:45 PM
 * Assignment: Programming Assignment #1 (Breadth-first and Depth-first Traversal)
 *       Date: January 28,2014                                                       */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Objective:
 *      - Read a graph representation from an input file into an adjacency matrix or adjacency list, and implement a
 *        Breadth-First Search (BFS) and Depth-First Search (DFS) algorithms for searching graphs.
 *      - Further research the two algorithms.
 *
 *  Problem:
 *      1. Breadth-First Traversal of a given graph:
 *          - Given an undirected, connected graph G and starting vertex s, perform a BFS and print the order in
 *            which the vertices are added to queue
 *      2. Depth-First Traversal of a given graph:
 *          - Given an undirected, connected graph G and starting vertex s, perform a DFS and print the order in
 *            which the vertices are added to stack
 *
 *  Restrictions on Algorithm:
 *      - The vertices adjacent to a vertex are added to the queue or stack in increasing order
 *          - Vertices range from n to n-1 where n is the number of vertices
 *      - When vertices are added to the queue or stack, it is marked as visited.
 *          - A vertex marked as visited is never added to queue or stack again
 *
 *  Source Code Restriction and I/O
 *      - Must compile via Java 7.0
 *      - Header information:
 *          1. Name
 *          2. Course Number
 *          3. Section Number
 *          4. Assignment Title
 *          5. Date
 *      - Must read from input file "graphs.txt"
 *      - Submit "Graph.java"
 *          - No package statement
 *          - Must define "public class Graph"
 *          - Must define "public static void main(String[] args)"
 *  Hints:
 *      - Graph can contain cycles
 *      - Can be recursive or iterative
 */


public class Graph {
    /**
     * Immediately begins BFS and DFS upon instantiation with filename
     * @param fileName
     */
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

                // Declare/Instantiate multidimensional integer array to hold the adjacency matrix values
                Integer[][] matrix = new Integer[n_by_n][n_by_n];

                // for each line in the given adjacency matrix
                for ( int j = 0 ; j < n_by_n && scan.hasNextLine() ; j++ ){
                    // Read line as one long string, and split at whitespace into an array before assigning to aLine
                    String[] aLine = scan.nextLine().split("\\s+");
                    // Convert each character element in aLine into an integer and copy into matrix
                    for ( int k = 0 ; k < n_by_n ; k++ ){
                        matrix[j][k] = Integer.parseInt(aLine[k]);
                    }
                }

                // Breadth first search here
                System.out.printf("BFS("+i+", "+startVertex+"): ");
                breadthFirst(matrix,startVertex);

                //// Depth first search here
                System.out.printf("\nDFS("+i+", "+startVertex+"): ");
                depthFirst(matrix,startVertex);
                if ( i+1 < numGraphs ) System.out.println();
                // Recursive Depth first Search
                // System.out.printf("\nDFSRec("+i+","+startVertex+"): ");
                // dfsRecWrap(matrix,startVertex);
            }

        }catch(IOException ex) {
            System.out.println("ERROR IOException");
        }
        System.out.println();
    }

    /**
     * Finds and returns an array containing the indices of adjacent vertices to vertex v
     * @param matrix - The adjacency matrix
     * @param v      - The vertex to find adjacent vertices around
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




/*******************************************************************************************************************
 * START Iterative Breadth-First Algorithm
 ********************************************************************************************************************/
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
        Arrays.fill(visited,false);

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
/*******************************************************************************************************************
 * END Iterative Breadth-First Algorithm
 ********************************************************************************************************************/







/*******************************************************************************************************************
 * START Iterative Depth-First Algorithm
 ********************************************************************************************************************/
    /**
     * Executes a Depth first search
     * @param matrix Adjacency Matrix
     * @param startVertex Vertex to begin at
     */
    public void depthFirst(Integer[][] matrix, int startVertex){
        /* ----------------------------------------------------------------------------------------------------------------
         * Declaration/Initialization
         * ---------------------------------------------------------------------------------------------------------------- */
        int popped ;                                    // Used to hold popped values
        Stack<Integer> stack = new Stack<Integer>();    // Create a instance of a stack

        // Visited array
        Boolean[] visited = new Boolean[matrix.length] ;                 // Array used to keep track of visited vertexes
        Arrays.fill(visited,false);

        /* ----------------------------------------------------------------------------------------------------------------
         * Iterative Depth First Algorithm Implementation
         * ---------------------------------------------------------------------------------------------------------------- */
        stack.push(startVertex);              // push to stack
        visited[startVertex] = true ;         // mark as visited
        System.out.printf("%d ",startVertex); // print it

        // while stack isn't empty
        while (!stack.isEmpty()){
            // Pop from stack
            popped = stack.pop();
            // For each element in the row of popped (ie matrix[popped])
            for ( int i = 0 ; i < matrix.length ; i++ ){
                // If element is adjacent (ie matrix[popped][i] == 1) and it is unvisited (visited[i] == true)
                if ( (matrix[popped][i].equals(1)) && (!visited[i])){
                    stack.push(i);                  // push to stack
                    visited[i] = true;              // mark as visited
                    System.out.printf("%d ",i);     // print it
                }
            }
        }
    }
/*******************************************************************************************************************
 * END Iterative Depth-First Algorithm
 ********************************************************************************************************************/






/*******************************************************************************************************************
 * START Recursive Depth First Algorithm
 ********************************************************************************************************************/
    /**
     * A Wrapper function that calls the recursive depth first search
     * @param matrix The adjacency matrix
     * @param startVertex The starting vertex specified in the input file
     */
    public void dfsRecWrap( Integer[][] matrix, int startVertex){
        /* ----------------------------------------------------------------------------------------------------------------
         * Declaration/Initialization
         * ---------------------------------------------------------------------------------------------------------------- */
        int count = 0 ;
        Boolean[] visited = new Boolean[matrix.length];
        Arrays.fill(visited,false);

        for ( int i = startVertex ; i < matrix.length ; i++ ){
            if ( !visited[i] ){
                dfsRecursive(matrix,visited,count,i);
            }
        }
    }
    /**
     * Recursive depth-first search implementation
     * @param matrix    - Adjacency matrix
     * @param visited   - Boolean array, where visited[vertex] = true if vertex has been visited
     * @param count     - Count of visited vertices
     * @param v         - startVertex
     */
    public void dfsRecursive(Integer[][] matrix, Boolean[] visited, int count, int v ){
        System.out.printf("%d ",v);
        count+=1 ;
        visited[v] = true ;
        /* ----------------------------------------------------------------------------------------------------------------
         * Depth First Algorithm Recursive Implementation
         * ---------------------------------------------------------------------------------------------------------------- */
        for ( int i : this.getAdjacent(matrix,v)){
            if ( visited[i] == false ){
                dfsRecursive(matrix,visited,count,i);
            }
        }
    }
/*******************************************************************************************************************
 * END Recursive Depth First Algorithm
 ********************************************************************************************************************/







    /* Creates a new instance of Graph, which immediately begins reading the filename specified in constructor arg
     * and runs bfs and dfs on each graph                                                                           */
    public static void main(String[] args){
        Graph g = new Graph("graphs.txt");
    }
}
