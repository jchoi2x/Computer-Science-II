/*
Emmanuel Martinez (PID: e2904661)
January 28, 2014
Computer Science II
Programming Assignment 1: DFS & BFS Graph Traversal
*/

import java.util.*;
import java.io.*;

public class raph{

    private Scanner scan;  // The scanner variable

    // Method to open the file
    public void openFile(){
        // Try to open the file
        try{
            scan = new Scanner(new File("graphs.txt"));
        }
        // if file not there
        catch(Exception e){
            System.out.println("Could not find file!");
        }
    }

    // Method to read the data from the file
    public void readFile(){
        while(scan.hasNext()){
            // k = number of graphs
            int k = Integer.parseInt(scan.next());

            for(int i = 0; i < k; i++){

                // Grab the values for the starting vertex and matrix size
                // where matrixSize = N x N
                String startingVertex = scan.next();
                int matrixSize = Integer.parseInt(scan.next());

                // 2D array that will hold the matrix values
                int[][] matrix = new int[matrixSize][matrixSize];

                // Vertical Loop
                for(int y = 0; y < matrixSize; y++){
                    // Horizontal Loop
                    for(int x = 0; x < matrixSize; x++){
                        //Store the values into the matrix
                        matrix[y][x] = Integer.parseInt(scan.next());
                    }
                }

                /*------------------------------------
                Beginning of DFS algorithm
                --------------------------------------*/
                //Creates the stack
                Stack<Integer> stack = new Stack<Integer>();

                int topOfStack;
                int initialVertex;
                int currentVertex;

                //Creates array that stores the visited vertices
                int[] visited = new int[matrixSize];
                //Stores -1 into each index of the visited array to signal blank array
                for(int x = 0; x < matrixSize; x++){
                    visited[x] = -1;
                }
                stack.push(Integer.parseInt(startingVertex)); // Pushes the starting vertex
                System.out.printf("DFS(%d, %s): ", i, startingVertex);
                System.out.printf(startingVertex + " ");
                initialVertex = Integer.parseInt(startingVertex);

                // While the stack is not empty
                while(!(stack.isEmpty())){
                    currentVertex = stack.peek();
                    stack.pop();
                    // If-else statement used to determine if we are just starting or continuing the search
                    if(stack.empty()){
                        topOfStack = -1;
                        currentVertex = initialVertex;
                    }else{
                        topOfStack = stack.peek();
                    }
                    //If the current vertex has not been visited
                    if(currentVertex != visited[currentVertex]){
                        // Visit it
                        visited[currentVertex] = currentVertex;
                        for(int x = 0; x < matrixSize; x++){
                            //If the current value in the matrix is a 1, and it has not been visited, and is not in the stack
                            if(matrix[currentVertex][x] == 1 && x != visited[x] && x != topOfStack){
                                stack.push(x);
                                System.out.printf(x + " ");
                                topOfStack = stack.peek();
                            }
                        }
                    }
                }
                System.out.println();

                /*--------------------------------------------------
                Beginning of BFS Algorithm
                --------------------------------------------------*/

                //Create the priority queue
                PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

                //Stores -1 into each index of the visited array to signal blank array
                for(int x = 0; x < matrixSize; x++){
                    visited[x] = -1;
                }

                queue.offer(Integer.parseInt(startingVertex));    // Enqueue the starting vertex right away
                System.out.printf("BFS(%d, %s): ", i, startingVertex);
                System.out.printf(startingVertex + " ");

                //While the Queue is not empty
                while(!(queue.isEmpty())){
                    currentVertex = queue.peek();
                    queue.poll();

                    if(queue.isEmpty()){
                        currentVertex = initialVertex;
                    }

                    //If the current vertex has not been visited
                    if(currentVertex != visited[currentVertex]){
                        // Visit it
                        visited[currentVertex] = currentVertex;
                        for(int x = 0; x < matrixSize; x++){
                            //If the current value in the matrix is a 1, and it has not been visited, and is not in the queue
                            if(matrix[currentVertex][x] == 1 && x != visited[x] && !(queue.contains(x))){
                                queue.offer(x);
                                System.out.printf(x + " ");
                            }
                        }
                    }
                }
                System.out.println();
                System.out.println();
            }
        }
    }
    //Close the .txt file
    public void closeFile(){
        scan.close();
    }

    //Beginning of main
    public static void main(String[] args){
        raph reader = new raph();
        reader.openFile();
        reader.readFile();
        reader.closeFile();
    }
}
