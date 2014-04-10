/**
 * Assignment #6 Hopscotch
 *
 * Name:     James Choi
 * Course:   Computer-Science II
 * Section:  Tues/Thurs 1:30PM
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class HopScotch {
    private int[] C;                    // Gameboard
    public HopScotch(String fileName) {
        readIn(fileName);
    }

    /**
     * Wrapper for recursive method. Reinitializes C (gameboard) and calls
     * recursive method.
     * @param n Order of row
     * @return Minimum score possible
     */
    public int hop(int n) {
        this.C = new int[n + 1];
        for (int i = 0; i < n + 1; i++) this.C[i] = i;
        return hops(C.length-1,0);
    }
    /**
     * Recursive method determines minimal score possible starting at cIndex
     * @param cIndex Current index of the recursive call
     * @param score Recursively accumulating value
     * @return Minimal score possible on the hopscotch board
     */
    private int hops(int cIndex, int score) {
        // Anything less than a seven can only single step
        if ( cIndex < 7 ) return score+cIndex;

        // If prime, there is only two paths that can be taken. Case 1 and 2
        if ( isPrime(C[cIndex])) return min(hops(cIndex-C[cIndex]%10,score+3),hops(cIndex-1,score+1 ));

        // If both multiple of seven AND a multiple of 11, Case 1, 3 and 4
        int s = C[cIndex]%7 ;   // Cache this computation value
        int e = C[cIndex]%11 ;  // Cache this computation value
        if ( s == 0 && e == 0 ) return min(min(hops(cIndex-digitSum(C[cIndex]),score+4),hops(cIndex-4,score+2)),hops(cIndex-1,score+1));
        // If only multiple of seven, return minimum b/t Case 4 and 1
        else if ( s == 0 ) return min(hops(cIndex-4,score+2),hops(cIndex-1,score+1));
        // If only multiple of seven, return minimum b/t Case 3 and 1
        else if ( e == 0 ) return min(hops(cIndex-digitSum(C[cIndex]),score+4),hops(cIndex-1,score+1));
        // Otherwise single step Case 1
        return hops(cIndex-1,score+1);
    }

    /**
     * Given two ints, will return the smaller value of the two
     * @param a
     * @param b
     * @return Smaller number b/t 'a' and 'b'
     */
    private int min(int a, int b) {
        return (a < b) ? a : b;
    }

    /**
     * Given a value, will compute the sum of the digits that compose the value
     * @param a
     * @return  Sum of digits of argument 'a'
     */
    private int digitSum(int a){
        int sum = 0;
        while (a != 0){
            sum += a%10;
            a /= 10 ;
        }
        return sum ;
    }
    /**
     * Determines if given value is prime
     * @param a
     * @return True if a is prime
     */
    private boolean isPrime(int a) {
        if (a < 10 || a % 2 == 0) return false;
        for ( int i = (int) Math.ceil((Math.sqrt(a))) ; i > 2; i--)
            if (a % i == 0) return false;
        return true;
    }

    /**
     * Reads in the input file and processes it
     * @param fileName
     */
    public void readIn(String fileName){
        Scanner scan = null ;
        try{scan = new Scanner(new File(fileName));}
        catch(FileNotFoundException ex){ System.out.println("File Not Found");}

        int numCases = Integer.parseInt(scan.nextLine());
        int i = 0 ;
        while (scan.hasNextLine() && i < numCases ){
            System.out.println("Game #"+(i+1)+": "+hop(Integer.parseInt(scan.nextLine())));
            i++ ;
        }
    }

    public static void main(String[] args) {
        new HopScotch("infile.txt");
    }
}
