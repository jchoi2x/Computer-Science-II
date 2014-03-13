import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays ;
import java.util.Scanner;

public class Binary{
    private char[] bin1;
    private char[] bin2;
    public Binary(String s1, String s2){
        this.bin1 = s1.toCharArray();
        this.bin2 = s2.toCharArray();
        if ( bin1.length != bin2.length ){
            if (bin1.length > bin2.length ){
                bin2 = extend(bin2, bin1.length);
            }
            else{
                bin1 = extend(bin1, bin2.length );
            }
        }
    }
    public Binary(char[] s1, char[] s2){
        this.bin1 = s1;
        this.bin2 = s2;
        if ( bin1.length != bin2.length ){
            if (bin1.length > bin2.length ){
                bin2 = extend(bin2, bin1.length-bin2.length);
            }
            else{
                bin1 = extend(bin1, bin2.length-bin1.length );
            }
        }
    }

    public char[] multiply(char[] a, char[] b){
        char[] prod = new char[a.length+b.length];
        char[] sum = new char[a.length+b.length];
        Arrays.fill(prod,'0');
        Arrays.fill(sum,'0');
        for ( int i = 0 ; i < b.length; i++ ){
            if ( b[i] == '1' ){
                sum = add(shiftL(a,i),extend(sum,sum.length+i));
            }
        }
        return prod ;
    }
    public char[] multiply(){
        char[] prod = new char[bin1.length+bin2.length];
        char[] sum = new char[bin1.length+bin2.length];
        Arrays.fill(prod,'0');
        Arrays.fill(sum,'0');
        for ( int i = 0 ; i < bin2.length; i++ ){
            char[] nAdd = shiftL(bin1,i);
            //print(nAdd,bin2[i]+" n2Add: ");

            if ( bin2[i] == '1' ){
                print(nAdd,"\nAdd: ");
                print(sum,"TO: ");
                sum = add(nAdd,sum);
                print(sum,"=: ");
            }
        }
        print(sum, "sum: ");
        return prod ;
    }

    public char[] extend(char[] ch, int n){
        if ( n <= 0 ){
            return ch ;
        }
        else{
            char[] nn = new char[ch.length+n];
            Arrays.fill(nn,'0');
            System.arraycopy(ch, 0, nn, 0, ch.length);

            return nn ;
        }
    }
    public char[] shiftL(char[] ch, int n){
        if ( n <= 0 ){
            return ch ;
        }
        else{
            char[] nn= new char[n+ch.length];
            Arrays.fill(nn,'0');
            System.arraycopy(ch, 0, nn, n, ch.length);
            return nn ;
        }
    }

    public char[] add(){
        char[] sol = new char[bin1.length];
        char carry = '0';
        for ( int i = 0 ; i < bin1.length; i++ ){
            sol[i] = xor(xor(bin1[i],bin2[i]),carry);
            carry = or(and(bin1[i],bin2[i]), and(carry,xor(bin1[i],bin2[i])));
        }
        if ( carry == '1' ){
            char[] ch = new char[bin1.length+1];
            Arrays.fill(ch, '0');
            System.arraycopy(sol, 0, ch, 0, sol.length);
            ch[ch.length-1] = carry ;
            sol = ch ;
        }

        return sol ;
    }

    public char[] add(char[] a, char[] b){
        if ( a.length > b.length ){
            b = extend(b,a.length-b.length);
        }
        else{
            a = extend(a,b.length-a.length);
        }
        char[] sol = new char[a.length];
        char carry = '0';
        for ( int i = 0 ; i < a.length; i++ ){
            sol[i] = xor(xor(a[i],b[i]),carry);
            carry = or(and(a[i],b[i]), and(carry,xor(a[i],b[i])));
        }
        if ( carry == '1' ){
            char[] ch = new char[a.length+1];
            Arrays.fill(ch, '0');
            System.arraycopy(sol, 0, ch, 0, sol.length);
            ch[ch.length-1] = carry ;
            sol = ch ;
        }

        return sol ;
    }


    public char and(char a, char b){
        if ( a == '1' && b == '1') return '1';
        else return '0';
    }
    public char or(char a, char b){
        if ( a == '1' || b =='1') return '1';
        else return '0';
    }
    public char xor(char a, char b){
        if ( a != b ) return '1';
        else return '0';
    }
    public void print(char[] str, String msg){
        System.out.print("\n"+msg);
        for ( int i = str.length-1 ; i > -1 ; i-- ){
            System.out.printf("%c",str[i]);
        }
    }
    public void print(int in){
        if ( in == 0 ){
            System.out.print("\nBinary 1: ");
            for ( int i = bin1.length-1 ; i > -1 ; i-- ){
                System.out.printf("%c",bin1[i]);
            }
            System.out.println();
        }
        else{
            System.out.print("\nBinary 2: ");
            for ( int i = bin2.length-1 ; i > -1 ; i-- ){
                System.out.printf("%c",bin2[i]);
            }

        }
    }
    public static char[] reverse(char[] ch){
        for ( int i = ch.length-1 ; i > -1 ; i-- ){
            char tmp = ch[ch.length-i-1];
            ch[ch.length-i-1] = ch[i];
            ch[i] = tmp ;
        }
        return ch ;
    }




    public static void readIn(String filename){
        int i, j;
        int numMult, numLength1, numLength2;
        String num1, num2;

        File textFile = new File(filename);

        try{
            Scanner in = new Scanner(textFile);
            numMult = Integer.parseInt(in.nextLine().split("\\s+")[0]);
            int curProb = 0 ;
            while (in.hasNextLine() & curProb < numMult){

                for (i = 0; i < numMult; i++){
                    String str1 = in.nextLine().split("\\s+")[1];
                    String str2 = in.nextLine().split("\\s+")[1];

                    Binary b = new Binary(str1.toCharArray(),str2.toCharArray());
                    b.print(0);
                    b.print(1);
                    b.print(b.add(),"Sol: ");
                    //b.print(b.multiply(),"Sol:");
                    b.multiply();

                    curProb++ ;
                }
            }
        }catch(FileNotFoundException ex){
            System.out.println("File Not found");
        }

    }
    public static void main(String[] args){

        readIn("infile.txt");

    }
}
