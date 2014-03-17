/**
 * Name: James choi
 * Course: Computer-Science II
 * Assignment #4            */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Multiplication{
    private String bin1 ;
    private String bin2 ;
    public Multiplication (String bin1, String bin2) {
        this.bin1 = bin1 ;
        this.bin2 = bin2 ;
    }

    /**
     * Returns the XOR between arguments.
     * @param a A bit
     * @param b A bit
     * @return XOR between parameter a and b
     */
    public char xor(char a, char b){
        if ( a != b ) return '1' ;
        else return '0' ;
    }

    /**
     * Returns the AND between arguments
     * @param a A bit
     * @param b A bit
     * @return AND between parameter a and b
     */
    public char and(char a, char b){
        if ( a == '1' && b == '1') return '1' ;
        else return '0';
    }

    /**
     * Returns the OR between arguments
     * @param a A bit
     * @param b A bit
     * @return OR between parameter a and b
     */
    public char or(char a, char b){
        if ( a == '1' || b == '1') return '1' ;
        else return '0';
    }

    /**
     * Inverts given binary number and returns its string.
     * @param binStr Binary number in String form
     * @return Inverted binary number of binStr
     */
    public String invert(String binStr){
        char[] rtn = new char[binStr.length()];
        for ( int i = 0 ; i < rtn.length ; i++ ) rtn[i] = binStr.charAt(i) == '1' ? '0' : '1';
        return new String(String.valueOf(rtn));
    }

    /**
     * Given a binary number and a length, if length is greater than binary string then this
     * method pads the given string with zeros and returns it. Else it returns given binStr
     * @param binStr Binary number in string form
     * @param length Length to pad given binary number up to.
     * @return Binary number as string padded with zero         */
    public String equalize(String binStr, int length ){
        if ( binStr.length() >= length) return binStr;
        else{
            int diff = length - binStr.length();
            for (int i = 0 ; i < diff ; i++ ){
                binStr = "0"+binStr;
            }
        }
        return new String(binStr) ;
    }

    /**
     * Returns the binary addition between first and second
     * @param first A binary number A
     * @param second A binary number B
     * @return The sum of A and B
     */
    public String addBinary( String first, String second ){
        String result = new String();  // To store the sum bits

        first = equalize(first, second.length());
        second = equalize(second,first.length());

        int length = first.length() > second.length() ? first.length(): second.length() ;
        char carry = '0';  // Initialize carry
        for (int i = length-1 ; i >= 0 ; i--){
            char sum = xor(xor(first.charAt(i),second.charAt(i)),carry);
            result = String.valueOf(sum+result);
            carry = or(or(and(first.charAt(i),second.charAt(i)),and(second.charAt(i),carry)), and(first.charAt(i),carry));
        }
        // if overflow, then add a leading 1
        if (carry == '1')  result = new String('1' + result);
        return new String(result);
    }

    /**
     * Implements binary subtraction between s1 and s2 by returning s1 + (~s2+1)
     * @param s1 A binary Number
     * @param s2 A binary Number
     * @return s1 minus s2
     */
    public String subtract (String s1, String s2){
        return new String(addBinary(s1,addBinary("1",invert(s2))).substring(1));
    }

    /**
     * Multiplies A and B
     * @param str1
     * @param str2
     * @return
     */
    public String multiply(String str1, String str2){
        String result = new String();
        int j = 0 ;
        for ( int i = str2.length()-1 ; i > -1 ; i-- ){
            // If encountered a 1, then shift i amount of times and add to sum
            if ( str2.charAt(i) == '1' ){
                result = addBinary(shiftL(str1,str2.length()-i-1),result);
            }
        }
        return new String(result) ;
    }

    /**
     * Implementation of the Karatsuba multiplication algorithm
     * @param a Binary number a
     * @param b Binary number b
     * @return a*b
     */
    public String karatsuba(String a, String b){
        int max = a.length() > b.length() ? a.length() : b.length() ;
        if ( a.length() <= max || b.length() <= max ){
            return multiply(a,b);
        }
        int m = a.length() > b.length() ? a.length() : b.length() ;
        m = m/2;

        String high1 = a.substring(0,m);
        String low1 = a.substring(m,a.length());

        String high2 = b.substring(0,m);
        String low2 = b.substring(m,b.length());

        String z0 = karatsuba(low1,low2);
        String z1 = karatsuba(addBinary(high1,high2), addBinary(low1,low2));
        String z2 = karatsuba(high1, high2);

        return addBinary( shiftL(z2,2*m), addBinary( shiftL(subtract(z1,addBinary(z2,z0)),m),z0));
    }

    /**
     * Returns str shifted n number of times to the left
     * @param str Binary number to shift left
     * @param n Number of times to shift left
     * @return Binary number str shifted left n number of times.
     */
    public String shiftL(String str, int n){
        String add = new String(str);

        for ( int i = 0 ; i < n ; i++ ) add = add.concat("0");
        return new String(add) ;
    }

    /**
     * Returns this classes fields bin1 and bin2
     * @param i 0 for bin1 and any other number for bin2
     * @return bin1 or bin2
     */
    public String get(int i){
        if ( i == 0 ) return this.bin1 ;
        else return this.bin2 ;
    }

    /**
     * Processes the input file
     * @param filename
     */
    public static void readIn(String filename){
        int numMult;
        File textFile = new File(filename);
        try{
            Scanner in = new Scanner(textFile);
            numMult = Integer.parseInt(in.nextLine().split("\\s+")[0]);
            int curProb = 0 ;
            while (in.hasNextLine() & curProb < numMult){
                String str1 = in.nextLine().split("\\s+")[1];
                String str2 = in.nextLine().split("\\s+")[1];

                Multiplication b = new Multiplication(str1,str2);
                System.out.println(b.multiply(b.get(0), b.get(1)));
                System.out.println(b.karatsuba(b.get(0), b.get(1)));
            }
        }catch(FileNotFoundException ex){
            System.out.println("File Not found");
        }
    }

    public static void main(String[] args){
        readIn("mult.txt");
    }
}
