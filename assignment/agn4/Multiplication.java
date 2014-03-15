/**
 * Name: James choi
 * Course: Computer-Science II
 * Assignment #4            */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Multiplication{
    private String bin1 ;
    private String bin2 ;
    public Multiplication (String bin1, String bin2) {
        this.bin1 = bin1 ;
        this.bin2 = bin2 ;
    }
    /**
     * Logic functions
     */
    public char xor(char a, char b){
        if ( a != b ) return '1' ;
        else return '0' ;
    }
    public char and(char a, char b){
        if ( a == '1' && b == '1') return '1' ;
        else return '0';
    }
    public char or(char a, char b){
        if ( a == '1' || b == '1') return '1' ;
        else return '0';
    }

    /*
     * Inverts and shifts
     */
    public String invert(String str){
        String ss = new String(str);
        char[] rtn = new char[str.length()];
        for ( int i = 0 ; i < rtn.length ; i++ ){
            if ( str.charAt(i) == '1'){
                rtn[i] = '0'  ;
            }
            else rtn[i] = '1';
        }
        return new String(String.valueOf(rtn));
    }
    public String twosComp( String bin ){
        String s = new String(bin);
        char[] ch = invert(s).toCharArray();
        char[] mask = new char[ch.length];
        Arrays.fill(mask,'0');
        mask[mask.length-1] = '1';
        return addBitStrings(bin,String.valueOf(mask));
    }
    /**
     * Make str equal the length if its length < less than
     */
    public String equalize(String str, int length ){
        if ( str.length() >= length) return str ;
        else{
            int diff = length - str.length();
            for (int i = 0 ; i < diff ; i++ ){
                str = "0"+str ;
            }
        }
        return new String(str) ;
    }
    /**
     * Arithmetic operations
     */
    public String addBitStrings( String first, String second ){
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
    public String subtract (String s1, String s2){
        return new String(addBitStrings(s1,addBitStrings("1",invert(s2))).substring(1));
    }
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
        String z1 = karatsuba(addBitStrings(high1,high2), addBitStrings(low1,low2));
        String z2 = karatsuba(high1, high2);

        return addBitStrings( shiftL(z2,2*m), addBitStrings( shiftL(subtract(z1,addBitStrings(z2,z0)),m),z0));
    }
    public String multiply(String str1, String str2){
        String result = new String();
        int j = 0 ;
        for ( int i = str2.length()-1 ; i > -1 ; i-- ){
            // If encountered a 1, then shift i amount of times and add to sum
            if ( str2.charAt(i) == '1' ){
                result = addBitStrings(shiftL(str1,str2.length()-i-1),result);
            }
        }
        return new String(result) ;
    }


    public String shiftL(String str, int n){
        String add = new String(str);

        for ( int i = 0 ; i < n ; i++ ) add = add.concat("0");
        return new String(add) ;
    }

    public String get(int i){
        if ( i == 0 ) return this.bin1 ;
        else return this.bin2 ;
    }

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
                //System.out.println(b.multiply(b.get(0), b.get(1)));
                //System.out.println(b.karatsuba(b.get(0), b.get(1)));
                System.out.println(b.twosComp(b.get(0)));
                System.out.println(b.twosComp(b.get(1)));
            }
        }catch(FileNotFoundException ex){
            System.out.println("File Not found");
        }
    }
    public static void main(String[] args){
        readIn("infile.txt");
    }

}
