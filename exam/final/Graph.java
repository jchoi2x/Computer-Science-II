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
                // System.out.printf("BFS("+i+", "+startVertex+"): ");
                // breadthFirst(matrix,startVertex);

                //// Depth first search here
                // System.out.printf("\nDFS("+i+", "+startVertex+"): ");
                // depthFirst(matrix,startVertex);
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

    // Max flow problem
        // find a path p that leads from s - to - t in G
        // find min cap in path p
        // assign each f(i,j) = minCap for all e in p

        // for each e in G, generate a e' in Gf
            // if f(i,j) > 0, draw e(j,i) with ce' = f(e)
            // if f(e) < ce, draw e(i,j)  with ce' = ce - f(e)


        // find a path p' from s - to - t in Gf
        // Identify the min residual cap b' in path p'
        // for each edge e in p',
            // if e is a forward edge
                // f'(e) = f(e) + b'
                // f(e) = f'(e)
            // if e is a reverse edge
                // f'(e) = f(e) + b'
                // f'(e) = f(e) - b'
    public static void maxFlow(int[] matrix){

    }

    /* Creates a new instance of Graph, which immediately begins reading the filename specified in constructor arg
     * and runs bfs and dfs on each graph                                                                           */
    public static void main(String[] args){
        Graph g = new Graph("graphs.txt");
    }
}
