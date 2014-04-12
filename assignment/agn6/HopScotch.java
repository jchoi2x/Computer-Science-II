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
    private int[] F;
    public HopScotch(String fileName) {
        this.F = new int[7];
        for ( int i = 0 ; i < 7 ; i++ ) this.F[i] = i ;
        readIn(fileName);
    }

    /**
     * Finds minimum score possible to reach end of row
     * @param n Starting box number
     * @return
     */
    private int hop(int n) {

        if ( n < F.length ) return F[n] ;

        int l = 0;
        int[] rtn = new int[n+1];
        l = F.length-1 ;
        Arrays.fill(rtn,0);
        System.arraycopy(F,0,rtn,0,F.length);
        this.F = rtn ;

        for ( int i = l+1 ; i <= n ; i++ ){
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
        for ( int i = (int) Math.ceil((Math.sqrt(a))) ; i > 1; i--)
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
