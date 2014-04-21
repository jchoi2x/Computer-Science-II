/**
 *       Name: James choi
 *     Course: Computer-Science II Tues/Thurs 1:30PM
 * Assignment: Assignment #6
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;


public class HopScotch {
    private int[] F;                                    // Let F(n) be the minimum score possible starting at n
    public HopScotch(String fileName) {
        this.F = new int[7];                            // Initial size of F array
        for ( int i = 0 ; i < 7 ; i++ ) this.F[i] = i ; // Fill with base cases
        readIn(fileName);                               // Read in the input file
    }

    /**
     * Finds minimum score possible to reach end of row
     * @param n Starting box number
     * @return Minimum score possible to reach box 0
     */
    private int hop(int n) {
        if ( n < F.length ) return F[n] ;     // If n is a value aready calculated, then return it
        int l = F.length;                     // Save length of F before resizing
        int[] rtn = new int[n+1];             // Make new array to resize F to
        Arrays.fill(rtn,0);                   // Initially fill with 0's
        System.arraycopy(F,0,rtn,0,F.length); // Copy all the old values of F into new Array
        this.F = rtn ;                        // Make F refer to the resize Array

        for ( int i = l ; i <= n ; i++ ){
            // Determine if any of special cases apply
            boolean prime = isPrime(i);
            boolean factor11 = (i%11 == 0);
            boolean factor7 = (i%7 == 0);

            if ( prime & factor11 ) F[i] = 9 ;
            else if ( factor11 & factor7 ) F[i] = min(min(F[i-digitSum(i)]+4,F[i-4]+2),F[i-1]+1);
            else if ( prime ) F[i] = min(F[i-i%10]+3,F[i-1]+1);
            else if ( factor7  ) F[i] = min(F[i-4]+2,F[i-1]+1);
            else if ( factor11 ) F[i] = min(F[i-digitSum(i)]+4,F[i-1]+1);
            else F[i] = F[i-1] + 1 ;
        }
        return F[n] ;
    }

    private int recHopWrap(int n){
        if ( n < F.length ){
            return F[n] ;
        }
        int[] rtn = new int[n+1] ;
        Arrays.fill(rtn,0);                   // Initially fill with 0's
        System.arraycopy(F,0,rtn,0,F.length); // Copy all the old values of F into new Array
        this.F = rtn ;                        // Make F refer to the resize Array

        return recHop(n);
    }

    private int recHop(int n){
        //if ( F[n] != 0 ) return F[n] ;
        if ( F[n] != 0 ) return F[n] ;

        // Determine if any of special cases apply
        boolean prime = isPrime(n);
        boolean factor11 = (n%11 == 0);
        boolean factor7 = (n%7 == 0);

        if ( prime & factor11 ) return 9 ;
        else if ( factor11 & factor7 ) F[n] = min(min(recHop(n-digitSum(n))+4,recHop(n-4)+2),recHop(n-1)+1);
        else if ( prime ) F[n] = min(recHop(n-n%10)+3,recHop(n-1)+1);
        else if ( factor7  ) F[n] = min(recHop(n-4)+2,recHop(n-1)+1);
        else if ( factor11 ) F[n] = min(recHop(n-digitSum(n))+4,recHop(n-1)+1);
        else F[n] = recHop(n-1) + 1 ;
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
     * Given a value, will compute the sum of the digits of the given value
     * ie n = 11 , then will return 2
     * @param a
     * @return  Sum of digits of argument 'a'           */
    private int digitSum(int n){
        int sum = 0;
        while (n != 0){
            sum += n%10;
            n /= 10 ;
        }
        return sum ;
    }
    /**
     * Determines if given value is prime
     * @param a
     * @return True if a is prime
     */
    private boolean isPrime(int n) {
        // If n is less than 10 or divisible by two, return false immediately
        if (n < 10 || n % 2 == 0) return false;
        /* Starting from the largest i that can possibly be a factor of n
         * if a factor is found, return false       */
        for ( int i = (int) Math.ceil((Math.sqrt(n))) ; i > 1; i--)
            if (n % i == 0) return false;
        // Must be prime
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
            System.out.println("Game #"+(i+1)+": "+recHopWrap(Integer.parseInt(scan.nextLine())));
            i++ ;
        }
    }

    public static void main(String[] args) {
        new HopScotch("hopscotch.in");
    }
}
