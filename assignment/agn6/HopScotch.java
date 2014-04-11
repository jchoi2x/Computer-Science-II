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
import java.util.Arrays;


public class HopScotch {
    private int[] F;                    // Gameboard
    public HopScotch(String fileName) {
        int[] F = new int[7];
        for ( int i = 0 ; i < F.length ; i++ ) F[i] = i ;
        readIn(fileName);
    }

    /**
     * Wrapper for recursive method. Reinitializes C (gameboard) and calls
     * recursive method.
     * @param n Order of row
     * @return Minimum score possible
     */
    public int hop(int n) {
        if ( n >= F.length ) {
            int[] rtn = new int[n+1];
            Arrays.fill(rtn,0);
            System.arraycopy(F,0,rtn,0,F.length);
            this.F = rtn ;
            return hops(n);
        }
        else{
            return F[n] ;
        }
    }
    /**
     * Recursive method determines minimal score possible starting at cIndex
     * @param cIndex Current index of the recursive call
     * @param score Recursively accumulating value
     * @return Minimal score possible on the hopscotch board
     */
    private int hops(int n) {
        for ( int i = 7 ; i < n ; i++ ){
            byte bin = 0 ;
            if (isPrime(n)) bin+= 0b001 ;
            if( i%11 == 0) bin+=  0b010 ;
            if ( i%7 == 0 ) bin+= 0b100 ;

            switch(bin){
                case 0b000:
                    F[i] = F[i-1] + 1 ;
                    break;
                case 0b001:
                    F[i] = min(F[i-i%10]+3,F[i-1]+1);
                    break;
                case 0b010:
                    F[i] = min(F[i-digitSum(i)]+4,F[i-1]+1);
                    break;
                case 0b011:
                    F[i] = min(min(F[i-i%10]+3,F[i-digitSum(i)]+4),F[n-1]+1);
                    break;
                case 0b100:
                    F[i] = min(F[i-4]+2,F[i-1]+1);
                    break;
                case 0b110:
                    F[i] = min(min(F[i-digitSum(i)]+4,F[i-4]+2),F[n-1]+1);
                    break;
                default:
                    F[i] = F[i-1] + 1 ;
                    break;
            }
        }
        return F[n] ;
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
