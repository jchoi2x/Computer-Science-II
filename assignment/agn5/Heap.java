/**
 * Name:   James Choi
 * Course: Computer-Science II
 * Assignment #5 Heaps
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Heap {
    private int[] h;
    /**
     * Initializes private int[] h with a -1 before calling
     * file read method to process input file.
     * @param fileName
     */
    public Heap(String fileName){
        this.h = new int[]{-1};                                 // Make an initial int array
        this.readIn(fileName);                                  // Read in the input file.
    }
    /**
     * Loads given String array, into private int[] h
     * @param list
     */
    public void load(String[] list){
        this.h = new int[list.length];                          // Make an int[] that is the size of the given String[]
        this.h[0] = -1 ;                                        // Insert a sentinal in the first index
        for ( int i = 1 ; i < list.length ; i++ ) this.h[i] = Integer.parseInt(list[i]); // Copy all elements except first of string[] as ints
    }
    /**
     * Sorts private int[] h using bottoms up approach
     * @return Heap sorted bottoms up
     */
    public int[] heapBottomsUp(){
        int n = this.h.length-1 ;
        for ( int i = this.h.length/2 ; i > 0 ; i-- ){
            int k = i ;
            int v = this.h[k] ;
            boolean heap = false ;
            while (!heap && 2*k <= n ){
                int j = 2*k ;
                if ( j < n && this.h[j] < this.h[j+1] ) j++ ;
                if ( v > this.h[j] ) heap = true ;
                else{
                    this.h[k] = this.h[j] ;
                    k = j ;
                }
            }
            this.h[k] = v ;
        }
        return this.h ;
    }
    /**
     * Deletes maximum val of heap by swapping root element with the last and then creating a new
     * array thats a copy of original, excluding the last element
     * @return The value of the deleted element
     */
    public int delete_max(){
        int max = h[1] ;                                        // Max (root) should be sitting at the top ie H[1]
        swap(1,this.h.length-1);                                // Swap root with bottom leftmost
        int[] nn = new int[this.h.length-1];
        System.arraycopy(this.h, 0, nn, 0, this.h.length-1);    // Remove last element by copying all but the last index into a new array
        this.h = nn ;                                           // Make this.heap the newly created array
        heapBottomsUp();                                        // Resort
        return max ;                                            // Return what was deleted, the max
    }
    /**
     * Inserts into heap by placing the value to insert at the end of the array and then
     * comparing with parent node and swapping if inserted element is greater than.
     * @param in Value to insert into heap
     */
    public void insert( int in ){
        int[] t = new int[this.h.length+1];
        System.arraycopy(this.h,0,t,0,this.h.length);
        t[this.h.length] = in ;
        this.h = t ;
        percolate(h.length-1);
    }
    public void percolate(int i){
        if ( i <= 0 ) return ;
        if ( h[i/2] < h[i] ) swap(i,i/2);
        percolate(i/2);
    }
    /**
     * Prints the values of heap in decreasing order by
     * 1. creating a heap, and then
     * 2. For n-1 number of times removing the maximum and printing it, until no elements left
     */
    public void heapSort(){
        heapBottomsUp();
        for ( int i = this.h.length-1 ; i > 0 ; i-- ) System.out.print(delete_max()+" ");
        System.out.println();
    }
    public void readIn(String fileName){
        Scanner scan = null ;
        try{scan = new Scanner(new File(fileName));}
        catch(FileNotFoundException ex){ System.out.println("File Not Found");}
        while (scan.hasNextLine()){
            String[] aLine = scan.nextLine().split("\\s+");
            if ( aLine[0].equals("load"))            load(aLine);
            else if ( aLine[0].equals("print"))      print();
            else if ( aLine[0].equals("build-heap")) heapBottomsUp();
            else if ( aLine[0].equals("delete-max")) delete_max();
            else if ( aLine[0].equals("insert"))     insert(Integer.parseInt(aLine[1]));
            else if ( aLine[0].equals("heapsort"))   heapSort();
            else System.out.println("Error: Input file Malformed");
        }
    }
    /**
     * Prints the current private int[] h from indexes 1 to n
     */
    public void print(){
        if ( this.h.length > 1 ) for ( int i = 1 ; i < this.h.length ; i++ ) System.out.print(h[i]+" ");
        else System.out.print("( Empty )");
        System.out.println();
    }
    /**
     * Swaps values in Index 1 and Index 2 of private int[] h by using
     * h[0] as a placeholder and then restoring it to -1 before returning
     * @param a Index 1
     * @param b Index 2
     */
    public void swap(int a, int b){
        this.h[0] = this.h[a];
        this.h[a] = this.h[b];
        this.h[b] = this.h[0];
        this.h[0] = -1 ;
    }

    public static void main(String[] args){
        Heap heap = new Heap("heapOps.txt");
    }
}
